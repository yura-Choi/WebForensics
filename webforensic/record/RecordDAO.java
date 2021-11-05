package record;

import java.sql.*;
import java.util.ArrayList;

public class RecordDAO {
    private ArrayList<RecordDTO> records = new ArrayList<RecordDTO>();
    private Connection conn = null;
    Statement stmt;

    public ArrayList<RecordDTO> searchRecord(int period) throws ClassNotFoundException, SQLException{
        //db 연결 정보

        String url = "jdbc:sqlite:" + System.getenv("USERPROFILE") + "\\AppData\\Local\\google\\chrome\\user data\\default\\history";
//      String url = "jdbc:sqlite:" + "history";


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
            String sql = "SELECT id, url, title, visit_count, typed_count, last_visit_time, hidden FROM urls where last_visit_time >= 13271850451150224";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                RecordDTO record = new RecordDTO();
                record.setId(rs.getString(1));
                record.setUrl(rs.getString(2));
                record.setTitle(rs.getString(3));
                record.setVisit_count(rs.getString(4));
                record.setTyped_count(rs.getString(5));
                record.setLast_visit_time(rs.getString(6));
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

    public static String subDays(int day) throws SQLException {
        long before = 11644473600L;
        long now = nowDateLong();
        now = (now+before)*1000000L;
        long sub = day*24*60*60*1000000L;
        long sum = now-(sub);

        return String.valueOf(sum);
    }

//    public static void main(String[] args ) throws SQLException {
//        System.out.println(datetoDefault(String.valueOf(chromeToUNIX(13315125879249347L))));
//    }

    //현재 시간을
    public static String nowDateLong() throws SQLException {
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

    public static String chromeToUNIX(String chrome) {
        long c = Long.valueOf(chrome);
        String ret = String.valueOf(c/1000000L - 11644473600L);
        return ret;
    }
}
