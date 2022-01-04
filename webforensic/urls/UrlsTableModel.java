package urls;

import gui.TableSelectionDemo;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;

public class UrlsTableModel extends AbstractTableModel {

    private static UrlsTableModel instance = new UrlsTableModel();
    private UrlsTableModel(){
    }

    public static UrlsTableModel getInstance(){
        return instance;
    }

    public static String[] getColumnNames() { return columnNames;}

    public static void setColumnNames(String[] columnNames) {
        UrlsTableModel.columnNames = columnNames;
    }

    private static String[] columnNames = {"id", "url", "title", "visit_count", "typed_count", "last_visit_time", "hidden"};
    private ArrayList<UrlsDTO> records = new ArrayList<>();

    public String getColumnName(int column){
        return columnNames[column];
    }

    public void setRecords(ArrayList<UrlsDTO> records){
        this.records = records;
    }

    @Override
    public int getRowCount(){ return records.size(); }

    @Override
    public int getColumnCount(){
        int column_n=0;
        for(int i=0; i < columnNames.length; i++){
            if(TableSelectionDemo.getInstance().filter_On.get(0)[i] == true){
                column_n++;
            }
        }

        return column_n;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
//        for_filter = TableSelectionDemo.getInstance();
//        if(for_filter.filter_On.get(0)[columnIndex] == false){
//            return null;
//        }

        int filter = 0;
        int max = columnIndex;
        for(int i=0; i < max + 1; i++){
            if(TableSelectionDemo.getInstance().filter_On.get(0)[i] == false){
                filter++;
                max++;
            }
        }

        Object result = null;
        UrlsDTO to = records.get(rowIndex);
        switch (columnIndex + filter) {
            case 0:
                result = to.getId();
                break;
            case 1:
                result = to.getUrl();
                break;
            case 2:
                result = to.getTitle();
                break;
            case 3:
                result = to.getVisit_count();
                break;
            case 4:
                result = to.getTyped_count();
                break;
            case 5:
                result = to.getLast_visit_time();
                break;
            case 6:
                result = to.getHidden();
                break;
        }
        return result;
    }

    public String[] detail(String url, String date){
        int i;
        for(i=0;i<records.size();i++){
            if(url.equals(records.get(i).getUrl()) && date.equals(records.get(i).getLast_visit_time())){
                break;
            }
        }
        int column = columnNames.length;
        String[] ret = new String[column];
        for(int j=0; j<column;j++){
            String result = null;
            UrlsDTO to = records.get(i);
            switch (j) {
                case 0:
                    result = to.getId();
                    break;
                case 1:
                    result = to.getUrl();
                    break;
                case 2:
                    result = to.getTitle();
                    break;
                case 3:
                    result = to.getVisit_count();
                    break;
                case 4:
                    result = to.getTyped_count();
                    break;
                case 5:
                    result = to.getLast_visit_time();
                    break;
                case 6:
                    result = to.getHidden();
                    break;
            }

            ret[j] = result;
        }

        return ret;
    }
}


