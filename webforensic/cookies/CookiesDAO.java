package cookies;

import urls.UrlsDTO;

import java.sql.*;
import java.util.ArrayList;

public class CookiesDAO {
    private ArrayList<CookiesDTO> records = new ArrayList<CookiesDTO>();
    private Connection conn = null;
    Statement stmt;

    public ArrayList<CookiesDTO> searchRecord(int period) throws ClassNotFoundException, SQLException{
        //db 연결 정보

        //String url = "jdbc:sqlite:" + System.getenv("USERPROFILE") + "\\AppData\\Local\\google\\chrome\\user data\\default\\cookies";
        String url = "jdbc:sqlite:" + System.getenv("USERPROFILE") + "\\files\\cookies";


        //db 드라이버 로딩
        try {
            Class.forName("org.sqlite.JDBC");
        }
        catch(ClassNotFoundException e)  {
            System.out.println("org.sqlite.JDBC를 찾지못했습니다.");
        }

        try{
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();
            String sql = "SELECT creation_utc, top_frame_site_key, host_key, name, value, encrypted_value, path, expires_utc, is_secure, is_httponly, last_access_utc, has_expires, is_persistent, priority, samesite, source_scheme, source_port, is_same_party FROM cookies where last_access_utc >= " + subDays(period);
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                CookiesDTO record = new CookiesDTO();
                record.setCreation_utc(datetoDefault(chromeToUNIX(rs.getString(1))));
                record.setTop_frame_site_key(rs.getString(2));
                record.setHost_key(rs.getString(3));
                record.setName(rs.getString(4));
                record.setValue(rs.getString(5));
                record.setEncrypted_value(rs.getString(6));
                record.setPath(rs.getString(7));
                record.setExpires_utc(datetoDefault(chromeToUNIX(rs.getString(8))));
                record.setIs_secure(rs.getInt(9));
                record.setIs_httponly(rs.getInt(10));
                record.setLast_access_utc(datetoDefault(chromeToUNIX(rs.getString(11))));
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

    //현재 시간 - day 한 크롬 날짜를 반환합니다
    public static String subDays(int day) throws SQLException {
        long before = 11644473600L;
        long now = Long.valueOf(nowDateString());
        now = (now+before)*1000000L;
        long sub = day*24*60*60*1000000L;
        long sum = now-(sub);

        return String.valueOf(sum);
    }

//    public static void main(String[] args ) throws SQLException {
//        System.out.println(datetoDefault(String.valueOf(chromeToUNIX(13315125879249347L))));
//    }

    //현재 시간을 유닉스 시간으로 반환힙니다
    public static String nowDateString() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        }
        catch(ClassNotFoundException e)  {
            System.out.println("org.sqlite.JDBC를 찾지못했습니다.");
        }
        Connection con = DriverManager.getConnection("jdbc:sqlite::memory:");
        Statement stmte = con.createStatement();
        ResultSet rs = stmte.executeQuery("SELECT strftime('%s','now', 'localtime');");
        rs.next();
        String ret = rs.getString(1);

        rs.close();
        stmte.close();
        con.close();

        return ret;
    }

    //날짜를 국룰 시간으로 반환합니다
    public static String datetoDefault(String date) throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        }
        catch(ClassNotFoundException e)  {
            System.out.println("org.sqlite.JDBC를 찾지못했습니다.");
        }
        Connection con = DriverManager.getConnection("jdbc:sqlite::memory:");
        Statement stmte = con.createStatement();
        ResultSet rs = stmte.executeQuery("SELECT datetime("+ date +", 'unixepoch')");
        rs.next();
        String ret = rs.getString(1);

        rs.close();
        stmte.close();
        con.close();

        return ret;
    }

    //크롬시간을 유닉스 시간으로 바꿔줍니다
    public static String chromeToUNIX(String chrome) {
        long c = Long.valueOf(chrome);
        String ret = String.valueOf(c/1000000L - 11644473600L);
        return ret;
    }
}
