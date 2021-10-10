import java.sql.*;

public class App {
    public static void main(String[] args) throws SQLException {
        System.out.printf("asdf");

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ResultSetMetaData meta = null;

        try {
            Class.forName("org.sqlite.JDBC");
        }
        catch(ClassNotFoundException e)  {
            System.out.println("org.sqlite.JDBC를 찾지못했습니다.");
        }

        try {
            con = DriverManager.getConnection("jdbc:sqlite:test");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        stmt = con.createStatement();

        rs = stmt.executeQuery("SELECT * FROM sqlite_master WHERE type = 'table'");
//        rs = stmt.executeQuery("SELECT * FROM sqlite_master");
        meta = rs.getMetaData();

        String columnHead = "";
        for(int i=1; i <= meta.getColumnCount(); i++){
            columnHead += "\t" + meta.getColumnName(i);
        }
        System.out.printf("\n");
        System.out.printf(columnHead);
        while(rs.next()) {
            String tuple = "";
            for(int i=1; i <= meta.getColumnCount(); i++){
                tuple += "\t" + rs.getString(i);
            }
            System.out.printf("\n");
            System.out.printf(tuple);
        }

        rs.close();
        con.close();

    }
}
