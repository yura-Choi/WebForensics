import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import static util.Time.*;

public class App {
    public static void main(String[] args) throws SQLException {
        System.out.println(chromeToUNIX("608610727026000000"));
        System.out.println(datetoDefault(chromeToUNIX("608610727026000000")));
//        System.out.println(isDateZero(datetoDefault(chromeToUNIX("608610727026000000"))));

        long time = 596966253426000L;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSS");
        Date date = new Date();
        date.setTime(time);

        String dateString = simpleDateFormat.format(date);
        System.out.println(dateString);
    }
}
