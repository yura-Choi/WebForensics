package record;

import gui.GUIMain;

public class MyMain {
    void mymain() {
        GUIMain.startGUI();
    }

    public static void main(String args[]) {
        MyMain a = new MyMain();
        a.mymain();

        //db 드라이버 로딩
        try {
            Class.forName("org.sqlite.JDBC");
        }
        catch(ClassNotFoundException e)  {
            System.out.println("org.sqlite.JDBC를 찾지못했습니다.");
        }
    }
}