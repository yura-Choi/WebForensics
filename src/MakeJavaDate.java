import java.util.Date;

public class MakeJavaDate {
    private static String data;
    private static Date jData;

    public MakeJavaDate(String data){
        this.data = data;
        MakeDATE();
    }

    public void MakeDATE(){
        long forChrome = 11644473600L;
        long sub = Long.parseLong(this.data)/1000000 - forChrome;
        this.jData = new Date(sub);
    }

    public static Date getjData() {
        return jData;
    }
}
