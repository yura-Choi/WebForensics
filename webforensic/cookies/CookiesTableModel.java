package cookies;

import urls.UrlsDAO;
import urls.UrlsDTO;

import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.ArrayList;

public class CookiesTableModel extends AbstractTableModel {
    private static CookiesTableModel instance = new CookiesTableModel();
    private CookiesTableModel() { }

    public static CookiesTableModel getInstance() { return instance ;}

    private static String[] columnNames = {"creation_utc", " top_frame_site_key", " host_key", " name", " value", " encrypted_value", " path", " expires_utc", " is_secure", " is_httponly", " last_access_utc", " has_expires", " is_persistent", " priority", " samesite", " source_scheme", " source_port", " is_same_party"};
    private ArrayList<CookiesDTO> records;


    public String getColumnName(int column){
        return columnNames[column];
    }

    public void setRecords(ArrayList<CookiesDTO> records) { this.records = records; }

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
        CookiesDTO to = records.get(rowIndex);
        switch (columnIndex) {
            case 0:
                result = to.getCreation_utc();
                break;
            case 1:
                result = to.getTop_frame_site_key();
                break;
            case 2:
                result = to.getHost_key();
                break;
            case 3:
                result = to.getName();
                break;
            case 4:
                result = to.getValue();
                break;
            case 5:
                result = to.getEncrypted_value();
                break;
            case 6:
                result = to.getPath();
                break;
            case 7:
                result = to.getExpires_utc();
                break;
            case 8:
                result = to.getIs_secure();
                break;
            case 9:
                result = to.getIs_httponly();
                break;
            case 10:
                result = to.getLast_access_utc();
                break;
            case 11:
                result = to.getHas_expires();
                break;
            case 12:
                result = to.getIs_persistent();
                break;
            case 13:
                result = to.getPriority();
                break;
            case 14:
                result = to.getSamesite();
                break;
            case 15:
                result = to.getSource_scheme();
                break;
            case 16:
                result = to.getSource_port();
                break;
            case 17:
                result = to.getIs_same_party();
                break;
        }
        return result;
    }
}


