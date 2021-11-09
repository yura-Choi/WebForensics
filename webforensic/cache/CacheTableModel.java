package cache;

import javax.swing.table.AbstractTableModel;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CacheTableModel extends AbstractTableModel {
    private String[] columnNames = {"id", "url", "creation_time"};
    private ArrayList<CacheDTO> records;


    public CacheTableModel(int period) throws ClassNotFoundException, SQLException, IOException {
        CacheDAO dao = new CacheDAO();
        records = dao.searchCache(period);
    }

    public String getColumnName(int column){
        return columnNames[column];
    }

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
                result = to.getCreate_time();
                break;
        }
        return result;
    }
}