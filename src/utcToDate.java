import java.sql.*;
import java.util.ArrayList;

public class utcToDate {
    private String filePath;
    private String[] tableList;
    private String[] attributeList;
    private String[] tupleList;
    private Connection connect;
    private Statement stmt;
    private String order;
    private String base_time1;
    private String base_time2;
    private String base;

    public utcToDate() throws SQLException {
        this.filePath = "jdbc:sqlite::memory:";
        this.base_time1 = "SELECT strftime('";
        this.base_time2 = "',(datetime(13252054032565471 / 1000000 + (strftime('%s', '1601-01-01')), 'unixepoch', 'localtime')))";
        StartConnection();
    }

    private void StartConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        }
        catch(ClassNotFoundException e)  {
            System.out.println("org.sqlite.JDBC를 찾지못했습니다.");
        }

        this.connect = DriverManager.getConnection(this.filePath);
        this.stmt = this.connect.createStatement();
    }

    public ArrayList<String[]> makeOrder(String format) throws SQLException {
        this.order = base_time1 + format + base_time2;

        ResultSet rs = this.stmt.executeQuery(this.order);
        ResultSetMetaData meta = rs.getMetaData();
        int attributesNumber = meta.getColumnCount();

        ArrayList<String[]> result = new ArrayList<String[]>();

        String[] columnHead = new String[attributesNumber];
        for(int i=1; i <= attributesNumber; i++){
            columnHead[i-1] = meta.getColumnName(i);
        }
        result.add(columnHead);

        while(rs.next()) {
            String[] tuple = new String[attributesNumber];
            for(int i=1; i <= meta.getColumnCount(); i++){
                tuple[i-1] = rs.getString(i);
            }
            result.add(tuple);
        }

        rs.close();

        return result;
    }

}
