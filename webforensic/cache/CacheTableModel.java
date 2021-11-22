package cache;

import cookies.CookiesDTO;
import urls.UrlsDTO;

import javax.swing.table.AbstractTableModel;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CacheTableModel extends AbstractTableModel {
    private static CacheTableModel instance = new CacheTableModel();
    private CacheTableModel(){}

    public static CacheTableModel getInstance() { return instance;}

    public String[] getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }

    private String[] columnNames = {"id", "url", "data type", "creation time", "file name", "file size(byte)"};
    private ArrayList<CacheDTO> records;

    public String getColumnName(int column){
        return columnNames[column];
    }

    public void setRecords(ArrayList<CacheDTO> records) { this.records = records; }

    @Override
    public int getRowCount(){
        return records.size();
    }

    @Override
    public int getColumnCount(){
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        String result = "";
        CacheDTO to = records.get(rowIndex);
        switch (columnIndex) {
            case 0:
                result = to.getId();
                break;
            case 1:
                result = to.getUrl();
                break;
            case 2:
                result = to.getData_type();
                break;
            case 3:
                result = to.getCreate_time();
                break;
            case 4:
                result = to.getData_name();
                break;
            case 5:
                result = to.getData_size();
                break;
        }
        return result;
    }

    public String[] detail(String url, String date){
        int i;
        for(i=0;i<records.size();i++){
            if(url.equals(records.get(i).getUrl()) && date.equals(records.get(i).getCreate_time())){
                break;
            }
        }
        int column = columnNames.length;
        String[] ret = new String[column];
        for(int j=0; j<column;j++){
            String result = null;
            CacheDTO to = records.get(i);
            switch (j) {
                case 0:
                    result = to.getId();
                    break;
                case 1:
                    result = to.getUrl();
                    break;
                case 2:
                    result = to.getData_type();
                    break;
                case 3:
                    result = to.getCreate_time();
                    break;
                case 4:
                    result = to.getData_name();
                    break;
                case 5:
                    result = to.getData_size();
                    break;
            }

            ret[j] = result;
        }

        return ret;
    }
}
