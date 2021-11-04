import java.sql.*;
import java.util.ArrayList;

public class SqliteDB {
    private String filePath;
    private String[] tableList;
    private String[] attributeList;
    private String[] tupleList;
    private Connection connect;
    private Statement stmt;


    public SqliteDB(String filePath) throws SQLException {
        this.filePath = "jdbc:sqlite:" + filePath;
        StartConnection();
        SetTables();
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

    private void SetTables() throws SQLException {
        ArrayList<String[]> result = SQLResult("SELECT tbl_name FROM sqlite_master WHERE type = 'table'");

        int tableNumber = result.size()-1;
        String[] tables = new String[tableNumber];
        for(int i = 1; i < tableNumber + 1; i++){
            // 어차피 테이블이라 값 하나씩만 있을 거임
            tables[i - 1] = result.get(i)[0];
        }

        this.tableList = tables;
    }

    private ArrayList<String[]> SQLResult(String sql) throws SQLException {
        ResultSet rs = this.stmt.executeQuery(sql);
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

    public ArrayList<String[]> DefaultTable(String table_name) throws SQLException {
        String sql = "SELECT * FROM " + table_name;
//        String sql = "SELECT * FROM " + table_name + " LIMIT 10;";

        return SQLResult(sql);
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String[] getTableList() {
        return tableList;
    }

    public void setTableList(String[] tableList) {
        this.tableList = tableList;
    }

    public String[] getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(String[] attributeList) {
        this.attributeList = attributeList;
    }

    public String[] getTupleList() {
        return tupleList;
    }

    public void setTupleList(String[] tupleList) {
        this.tupleList = tupleList;
    }

    public Connection getConnect() {
        return connect;
    }

    public void setConnect(Connection connect) {
        this.connect = connect;
    }

    public Statement getStmt() {
        return stmt;
    }

    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }
}
