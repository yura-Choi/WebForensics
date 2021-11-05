package record;

import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecordTableModel extends AbstractTableModel {
    private String[] columnNames = {"id", "url", "title", "visit_count", "typed_count", "last_visit_time", "hidden"};
    private ArrayList<RecordDTO> records;


    public RecordTableModel(int period) throws ClassNotFoundException, SQLException{
        RecordDAO dao = new RecordDAO();
        records = dao.searchRecord(period);
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
        RecordDTO to = records.get(rowIndex);
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
