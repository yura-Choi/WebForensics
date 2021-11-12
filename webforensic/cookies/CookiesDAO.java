package cookies;

import com.sun.jna.platform.win32.Crypt32Util;
import netscape.javascript.JSObject;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import urls.UrlsDAO;
import urls.UrlsDTO;
import util.CopyFile;
import util.Time;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

import static util.Time.chromeToUNIX;

public class CookiesDAO {
    private static CookiesDAO instance = new CookiesDAO();
    private CookiesDAO(){

    }

    public static CookiesDAO getInstance(){
        return instance;
    }

    private ArrayList<CookiesDTO> records = new ArrayList<CookiesDTO>();
    private Connection conn = null;
    Statement stmt;

    public void addRecord(CookiesDTO record){
        records.add(record);
    }

    public int getRecordCnt(){
        return records.size();
    }

    public String getUrl(int idx){
        return records.get(idx).getUrl();
    }

    public String getLast_visit_time(int idx){
        return records.get(idx).getLast_access_utc();
    }

    public ArrayList<CookiesDTO> searchRecord(int period) throws ClassNotFoundException, SQLException{
        CopyFile copy = CopyFile.getInstance();
        copy.makeNewFile("cookies");

        //db 연결 정보
        String url = "jdbc:sqlite:" + System.getenv("USERPROFILE") + "\\AppData\\Local\\google\\chrome\\user data\\default\\new_cookies";

        util.Time time = Time.getInstance();
        try{
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();
//            String sql = "SELECT creation_utc, top_frame_site_key, host_key, name, value, encrypted_value, path, expires_utc, is_secure, is_httponly, last_access_utc, has_expires, is_persistent, priority, samesite, source_scheme, source_port, is_same_party FROM cookies WHERE name = 'SSID'";
            String sql = "SELECT creation_utc, top_frame_site_key, host_key, name, value, encrypted_value, path, expires_utc, is_secure, is_httponly, last_access_utc, has_expires, is_persistent, priority, samesite, source_scheme, source_port, is_same_party FROM cookies where last_access_utc >= " + time.subDays(period);

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                CookiesDTO record = new CookiesDTO();
                record.setCreation_utc(time.printDate(rs.getString(1)));
                record.setTop_frame_site_key(rs.getString(2));
                record.setHost_key(rs.getString(3));
                record.setName(rs.getString(4));
                record.setValue(rs.getString(5));
                record.setEncrypted_value(decrypted(rs.getBytes(6)));
                record.setPath(rs.getString(7));
                record.setExpires_utc(time.printDate(rs.getString(8)));
                record.setIs_secure(rs.getInt(9));
                record.setIs_httponly(rs.getInt(10));
                record.setLast_access_utc(time.printDate(rs.getString(11)));
                record.setHas_expires(rs.getInt(12));
                record.setIs_persistent(rs.getInt(13));
                record.setPriority(rs.getInt(14));
                record.setSamesite(rs.getInt(15));
                record.setSource_scheme(rs.getInt(16));
                record.setSource_port(rs.getInt(17));
                record.setIs_same_party(rs.getInt(18));

                records.add(record);
            }

            if(rs != null) rs.close();
            if(stmt != null) stmt.close();
            if(conn != null) conn.close();

        } catch(SQLException e){
            System.out.println("Connection failed");
        }

        return records;
    }

    public String decrypted (byte[] encryptedValue){
        byte[] nonce = Arrays.copyOfRange(encryptedValue, 3, 3 + 12);
        byte[] ciphertextTag = Arrays.copyOfRange(encryptedValue, 3 + 12,
                encryptedValue.length);
        byte[] decryptedBytes = null;

        byte[] windowsMasterKey;
        String pathLocalState = System.getProperty("user.home") + "/AppData/Local/Google/Chrome/User Data/Local State";
        File localStateFile = new File(pathLocalState);

        // json 데이터 추출
        JSONParser parser = new JSONParser();
        Reader reader = null;
        try {
             reader = new FileReader(pathLocalState);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        JSONObject j = null;
        try {
            JSONObject json = (JSONObject) parser.parse(reader);
            j = ((JSONObject) json.get("os_crypt"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String encryptedMasterKeyWithPrefixB64 = (String) j.get("encrypted_key");

        // Remove prefix (DPAPI)
        byte[] encryptedMasterKeyWithPrefix = Base64.getDecoder().decode(encryptedMasterKeyWithPrefixB64);
        byte[] encryptedMasterKey = Arrays.copyOfRange(encryptedMasterKeyWithPrefix, 5, encryptedMasterKeyWithPrefix.length);

        // Decrypt and store the master key for use later
        windowsMasterKey = Crypt32Util.cryptUnprotectData(encryptedMasterKey);

        // Decrypt
        try {
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

            GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, nonce);
            SecretKeySpec keySpec = new SecretKeySpec(windowsMasterKey, "AES");

            cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmParameterSpec);
            decryptedBytes = cipher.doFinal(ciphertextTag);
        }
        catch (Exception e) {
            throw new IllegalStateException("Error decrypting", e);
        }

        return new String(decryptedBytes);
    }
}
