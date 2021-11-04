import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws SQLException {
//        SqliteDB db = new SqliteDB("C:\\Users\\na\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\History");
//        SqliteDB db = new SqliteDB("D:\\univercity\\ssg\\History");
//
//        for(int i=0; i< db.getTableList().length; i++){
//            System.out.printf(db.getTableList()[i]+"\n");
//        }

//        ArrayList<String[]> table = db.DefaultTable("urls");
        ArrayList<String[]> table = new utcToDate().makeOrder("%Y %m %d %H %M");
        for(int i=0; i< table.size(); i++){
            System.out.println(Arrays.toString(table.get(i)) +"\n");
        }

        MakeJavaDate test = new MakeJavaDate("13280513191900639");
        System.out.println(test.getjData().toString());

        System.out.println(subDays(10));
    }

    public static String subDays(int day) throws SQLException {
        long before = 11644473600L;
        long now = new utcToDate().nowDateLong();
        now = (now+before)*1000000L;
        long sub = day*24*60*60*1000000L;
        long sum = now-(sub);

        return String.valueOf(sum);
    }
}
