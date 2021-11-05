package record;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class RecordDAO {
    private ArrayList<RecordDTO> records = new ArrayList<RecordDTO>();
    private Connection conn = null;
    Statement stmt;

    public ArrayList<RecordDTO> searchRecord(int period) throws ClassNotFoundException, SQLException{
        //db 연결 정보
//        try {
//            FileInputStream fis = new FileInputStream(System.getenv("USERPROFILE") + "\\AppData\\Local\\google\\chrome\\user data\\default\\history");
//            FileOutputStream fos = new FileOutputStream("history");
//
//            int data = 0;
//            while((data = fis.read()) != -1){
//                fos.write(data);
//            }
//            fis.close();
//            fos.close();
//        }catch (IOException e){
//            System.out.println(e);
//        }
        String url = "jdbc:sqlite:" + System.getenv("USERPROFILE") + "\\AppData\\Local\\google\\chrome\\user data\\default\\history";
//        String url = "jdbc:sqlite:" + "history";
//sadfsadf

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
}
