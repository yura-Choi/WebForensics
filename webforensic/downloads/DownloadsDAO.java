package downloads;

import java.sql.*;
import java.util.ArrayList;

public class DownloadsDAO {
    private ArrayList<DownloadsDTO> records = new ArrayList<DownloadsDTO>();
    private Connection conn = null;
    Statement stmt;

    public ArrayList<DownloadsDTO> searchRecord(int period) throws ClassNotFoundException, SQLException{
        //db 연결 정보

        //String url = "jdbc:sqlite:" + System.getenv("USERPROFILE") + "\\AppData\\Local\\google\\chrome\\user data\\default\\cookies";
        String url = "jdbc:sqlite:" + System.getenv("USERPROFILE") + "\\files\\history";


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
            String sql = "SELECT id, guid, current_path, target_path, start_time, received_bytes, total_bytes, state, danger_type, interrupt_reason, hash, end_time, opened, last_access_time, transient, referrer, site_url, tab_url, tab_referrer_url, http_method, by_ext_id, by_ext_name, etag, last_modified, mime_type, original_mime_type FROM downloads where start_time >= " + subDays(period);
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                DownloadsDTO record = new DownloadsDTO();
                int i=1;
                //datetoDefault(chromeToUNIX(rs.getString(i++)))
                record.setId(rs.getInt(i++));
                record.setGuid(rs.getString(i++));
                record.setCurrent_path(rs.getString(i++));
                record.setTarget_path(rs.getString(i++));
                record.setStart_time(datetoDefault(chromeToUNIX(rs.getString(i++))));
                record.setReceived_bytes(rs.getLong(i++));
                record.setTotal_bytes(rs.getLong(i++));
                record.setState(rs.getInt(i++));
                record.setDanger_type(rs.getInt(i++));
                record.setInterrupt_reason(rs.getInt(i++));
                record.setHash(rs.getString(i++));
                //end time 도 0인게 있다 어케 처리할지 고민해야 할듯!
                record.setEnd_time(datetoDefault(chromeToUNIX(rs.getString(i++))));
                record.setOpened(rs.getInt(i++));
                //last_access_time 이 0일 경우 예외처리 해야할듯?
                String access_check = rs.getString(i++);
                if(access_check.equals("0")){
                    record.setLast_access_time("Never Accessed");
                }
                else {
                    record.setLast_access_time(datetoDefault(chromeToUNIX(access_check)));
                }
//                record.setLast_access_time(datetoDefault(chromeToUNIX(rs.getString(i++))));

                record.setDowload_transient(rs.getString(i++));
                record.setReferrer(rs.getString(i++));
                record.setSite_url(rs.getString(i++));
                record.setTab_url(rs.getString(i++));
                record.setTab_referrer_url(rs.getString(i++));
                record.setHttp_method(rs.getString(i++));
                record.setBy_ext_id(rs.getString(i++));
                record.setBy_ext_name(rs.getString(i++));
                record.setEtag(rs.getString(i++));
                record.setLast_modified(rs.getString(i++));
                record.setMime_type(rs.getString(i++));
                record.setOriginal_mime_type(rs.getString(i++));


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
