package webforensic.record;


import webforensic.facade.DataEngineInterface;
import webforensic.mgr.Factory;
import webforensic.mgr.Manageable;
import webforensic.mgr.Manager;

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
