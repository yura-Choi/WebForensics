package urls;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.util.ArrayList;

public class UrlsDAO {
    private ArrayList<UrlsDTO> records = new ArrayList<UrlsDTO>();
    private Connection conn = null;
    Statement stmt;

    public ArrayList<UrlsDTO> searchRecord(int period) throws ClassNotFoundException, SQLException{
        File file = new File(System.getenv("USERPROFILE")+"\\AppData\\Local\\google\\chrome\\user data\\default\\history");
        File Nfile = new File(System.getenv("USERPROFILE")+"\\AppData\\Local\\google\\chrome\\user data\\default\\new_history");

        try {
            Files.copy(file.toPath(), Nfile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //db 연결 정보
        String url = "jdbc:sqlite:" + System.getenv("USERPROFILE") + "\\AppData\\Local\\google\\chrome\\user data\\default\\new_history";

        //db 연결 정보

        //String url = "jdbc:sqlite:" + System.getenv("USERPROFILE") + "\\AppData\\Local\\google\\chrome\\user data\\default\\history";
//        String url = "jdbc:sqlite:" + System.getenv("USERPROFILE") + "\\files\\history";


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
            String sql = "SELECT id, url, title, visit_count, typed_count, last_visit_time, hidden FROM urls where last_visit_time >= " + subDays(period);
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                UrlsDTO record = new UrlsDTO();
                record.setId(rs.getString(1));
                record.setUrl(rs.getString(2));
                record.setTitle(rs.getString(3));
                record.setVisit_count(rs.getString(4));
                record.setTyped_count(rs.getString(5));
                record.setLast_visit_time(datetoDefault(chromeToUNIX(rs.getString(6))));
                record.setHidden(rs.getString(7));

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
