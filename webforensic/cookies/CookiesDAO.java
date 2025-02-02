package cookies;

import cipher.AES_GCM;
import util.Time;
import com.sun.jna.platform.win32.Crypt32Util;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import util.CopyFile;

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

public class CookiesDAO {
    private static CookiesDAO instance = new CookiesDAO();
    private CookiesDAO(){}

    public static CookiesDAO getInstance(){
        return instance;
    }

    public ArrayList<CookiesDTO> records = new ArrayList<CookiesDTO>();
    private Connection conn = null;
    Statement stmt;
    Time time = Time.getInstance();
    AES_GCM aes_gcm = new AES_GCM();

    public ArrayList<CookiesDTO> searchRecord(int period) throws ClassNotFoundException, SQLException, IOException, ParseException {
        String pathLocalState = System.getProperty("user.home") + "/AppData/Local/Google/Chrome/User Data/Local State";
        Reader reader = new FileReader(pathLocalState);
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject)parser.parse(reader);

        String localState_user_experience_metrics = jsonObject.get("user_experience_metrics").toString();
        String localState_stability = ((JSONObject)parser.parse(localState_user_experience_metrics)).get("stability").toString();
        String localState_stats_version = ((JSONObject)parser.parse(localState_stability)).get("stats_version").toString();
        String version = localState_stats_version.substring(0, localState_stats_version.indexOf("."));

        File file, Nfile;
        if(Integer.parseInt(version) >= 96){
            file = new File(System.getenv("USERPROFILE")+"\\AppData\\Local\\google\\chrome\\user data\\default\\network\\cookies");
            Nfile = new File(System.getenv("USERPROFILE")+"\\AppData\\Local\\google\\chrome\\user data\\default\\network\\new_cookies");
        } else {
            file = new File(System.getenv("USERPROFILE")+"\\AppData\\Local\\google\\chrome\\user data\\default\\cookies");
            Nfile = new File(System.getenv("USERPROFILE")+"\\AppData\\Local\\google\\chrome\\user data\\default\\new_cookies");
        }

        try {
            Files.copy(file.toPath(), Nfile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //db 연결 정보
        String url = "jdbc:sqlite:" + System.getenv("USERPROFILE") + "\\AppData\\Local\\google\\chrome\\user data\\default\\network\\new_cookies";

        //db 연결 정보
        CopyFile copy = CopyFile.getInstance();
        copy.makeNewFile("cookies");


        try{
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();
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
                record.setUrl(record.getHost_key()+record.getPath());

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
        byte[] ciphertextTag = Arrays.copyOfRange(encryptedValue, 3 + 12, encryptedValue.length);
        byte[] decryptedBytes = new byte[encryptedValue.length - 3 - 12 - 16];

        byte[] windowsMasterKey;
        String pathLocalState = System.getProperty("user.home") + "/AppData/Local/Google/Chrome/User Data/Local State";

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
        System.out.println(encryptedMasterKey.length);

        // Decrypt and store the master key for use later
        windowsMasterKey = Crypt32Util.cryptUnprotectData(encryptedMasterKey);
        System.out.println(windowsMasterKey.length);

        aes_gcm.AES_GCM_Decryption(decryptedBytes, ciphertextTag, ciphertextTag.length, 16, nonce, nonce.length, null, 0, windowsMasterKey);


        return new String(decryptedBytes);
    }

    public String getUrl(int idx) {
        return records.get(idx).getUrl();
    }

    public String getLast_access_time(int idx) {
        return records.get(idx).getLast_access_utc();
    }

    public int getRecordCnt(){
        return records.size();
    }
}