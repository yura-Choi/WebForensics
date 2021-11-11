package urls;

import util.Time;

import java.sql.*;
import java.util.ArrayList;

public class UrlsDAO {
    private static UrlsDAO instance = new UrlsDAO();
    private UrlsDAO(){

    }

    public static UrlsDAO getInstance(){
        return instance;
    }

    private ArrayList<UrlsDTO> records = new ArrayList<UrlsDTO>();
    private Connection conn = null;
    Statement stmt;

    public void addRecord(UrlsDTO record){
        records.add(record);
    }

    public ArrayList<UrlsDTO> searchRecord(int days) {

        String url = "jdbc:sqlite:" + System.getenv("USERPROFILE") + "\\files\\history";

        Time time = Time.getInstance();


        try{
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();
            String sql = "SELECT id, url, title, visit_count, typed_count, last_visit_time, hidden FROM urls where last_visit_time >= " + time.subDays(days);
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                UrlsDTO record = new UrlsDTO();
                record.setId(rs.getString(1));
                record.setUrl(rs.getString(2));
                record.setTitle(rs.getString(3));
                record.setVisit_count(rs.getString(4));
                record.setTyped_count(rs.getString(5));
                record.setLast_visit_time(time.datetoDefault(time.chromeToUNIX(rs.getString(6))));
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
}
