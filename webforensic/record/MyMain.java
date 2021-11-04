package webforensic.record;

import webforensic.gui.GUIMain;

public class MyMain {
    void mymain(){
        RecordMgr engine = new RecordMgr();
        engine.readAll("record.txt");
        GUIMain.startGUI(engine);
    }

    public static void main(String args[]){
        MyMain a = new MyMain();
        a.mymain();
    }
}