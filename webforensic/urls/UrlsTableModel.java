package urls;

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
    private static String[] columnNames = {"id", "url", "title", "visit_count", "typed_count", "last_visit_time", "hidden"};
    private ArrayList<UrlsDTO> records;



    public UrlsTableModel(int period) throws ClassNotFoundException, SQLException{
        UrlsDAO dao = new UrlsDAO();
        records = dao.searchRecord(period);

        fireTableDataChanged();
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
        Object result = null;
        UrlsDTO to = records.get(rowIndex);
        switch (columnIndex) {
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
}


