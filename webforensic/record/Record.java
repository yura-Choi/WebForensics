package webforensic.record;

import webforensic.facade.UIData;
import webforensic.mgr.Manageable;

import java.util.Scanner;

public class Record implements Manageable, UIData {
    int id;
    String url;
    String title;
    String visit_time;
    int visit_count;

    @Override
    public void set(Object[] row){
        id = Integer.parseInt((String)row[0]);
        url = (String)row[1];
        title = (String)row[2];
        visit_time = (String)row[3];
        visit_count = Integer.parseInt((String)row[4]);
    }

    @Override
    public void read(Scanner scan){
        id = scan.nextInt();
        url = scan.next();
        title = scan.next();
        visit_time = scan.next();
        visit_count = scan.nextInt();
    }

    @Override
    public void print(){
        System.out.printf("%d %s %s %s %d\n", id, url, title, visit_time, visit_count);
    }

    @Override
    public boolean matches(String n){
        if(Integer.toString(id).equals(n))
            return true;
        if(title.contains(n))
            return true;
        return false;
    }

    @Override
    public String[] getUiTexts(){
        return new String[] {""+id, url, title, visit_time, ""+visit_count};
    }
}
