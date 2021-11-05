package record;


import facade.DataEngineInterface;
import mgr.Factory;
import mgr.Manageable;
import mgr.Manager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class RecordMgr implements DataEngineInterface {
    // 테이블 헤더 데이터 제공 부분
    private static final String[] labels = {"ID", "URL", "Title", "Visit Time", "Visit Count"};

    @Override
    public int getColumnCount(){
        return labels.length;
    }

    @Override
    public String[] getColumnNames(){
        return labels;
    }

    Scanner scan = new Scanner(System.in);
    private Manager mgr = new Manager();
    @Override
    public void readAll(String filename){
        /*
        try {
            FileInputStream fis = new FileInputStream(System.getenv("USERPROFILE") + "\\AppData\\Local\\google\\chrome\\user data\\default\\history");
            FileOutputStream fos = new FileOutputStream("history");

            int data = 0;
            while((data = fis.read()) != -1){
                fos.write(data);
            }
            fis.close();
            fos.close();
        }catch (IOException e){
            System.out.println(e);
        }
        */

        mgr.readAll(filename, new Factory() {
            public Manageable create() {
                return new Record();
            }
        });
    }

    @Override
    public List<Manageable> search(String kwd){
        if(kwd == null)
            return mgr.mList;
        return mgr.findAll(kwd);
    }
    @Override
    public void addNewItem(String[] editTexts){
        Record s = new Record();
        s.set(editTexts);
        mgr.mList.add(s);
    }
    @Override
    public void update(String[] editTexts){
        Record s = (Record)mgr.find(editTexts[0]);
        s.set(editTexts);
    }
    @Override
    public void remove(String kwd){
        Record s = (Record)mgr.find(kwd);
        mgr.mList.remove(s);
    }

}
