import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws SQLException {
//        SqliteDB db = new SqliteDB("History");
//
//        for(int i=0; i< db.getTableList().length; i++){
//            System.out.printf(db.getTableList()[i]+"\n");
//        }

//        ArrayList<String[]> table = db.DefaultTable("urls");
        ArrayList<String[]> table = new utcToDate().makeOrder("%Y %m %d");
        for(int i=0; i< table.size(); i++){
            //printf 는 %D 같은거 변수로 생각하는데 println은 그냥 출력하네
            System.out.println(Arrays.toString(table.get(i)) +"\n");
        }

    }
}
