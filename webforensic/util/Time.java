package util;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {

    private static Time instance = new Time();
    private Time(){}

    public static Time getInstance() { return instance; }

    //현재 시간 - day 한 크롬 날짜를 반환합니다
    public static String subDays(int day) throws SQLException {
        long before = 11644473600L;
        long now = Long.valueOf(nowDateString());
        now = (now+before)*1000000L;
        long sub = day*24*60*60*1000000L;
        long sum = now-(sub);

        return String.valueOf(sum);
    }

    //현재 시간을 유닉스 시간으로 반환힙니다
    public static String nowDateString() throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:sqlite::memory:");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT strftime('%s','now', 'localtime');");
        rs.next();
        String ret = rs.getString(1);

        rs.close();
        stmt.close();
        conn.close();

        return ret;
    }

    //날짜를 국룰 시간으로 반환합니다
    public static String datetoDefault(String date) throws SQLException {
//        sqlite는 숫자 크면 오류터지는듯 20000년 넘는건 null 나옴;;
//        Connection conn = DriverManager.getConnection("jdbc:sqlite::memory:");
//        Statement stmt = conn.createStatement();
//        ResultSet rs = stmt.executeQuery("SELECT datetime("+ date +", 'unixepoch')");
//        rs.next();
//        String ret = rs.getString(1);
//
//        rs.close();
//        stmt.close();
//        conn.close();

        long time = Long.valueOf(date+"000");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        java.util.Date date2 = new Date();
        date2.setTime(time);

        String dateString = simpleDateFormat.format(date2);

        return dateString;
    }

    //크롬시간을 유닉스 시간으로 바꿔줍니다
    public static String chromeToUNIX(String chrome) {
        long c = Long.valueOf(chrome);
        String ret = String.valueOf(c/1000000L - 11644473600L);
        return ret;
    }

    //날짜가 0이면 논 출력
    public static String isDateZero(String date){
        if(date.equals("1601-01-01 00:00:00") ||date.equals("1601-01-01 09:00:00") || date == null){
            return "NONE";
        }
        else{
            return date;
        }
    }

    public String printDate(String date) throws SQLException {
        return isDateZero(datetoDefault(chromeToUNIX(date)));
    }
}

