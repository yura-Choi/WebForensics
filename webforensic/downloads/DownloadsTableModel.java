package downloads;

import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.ArrayList;

public class DownloadsTableModel extends AbstractTableModel {
    private static DownloadsTableModel instance = new DownloadsTableModel();
    private DownloadsTableModel(){

    }

    public static DownloadsTableModel getInstance(){ return instance; }

    private static String[] columnNames = {"id", " guid", " current_path", " target_path", " start_time", " received_bytes", " total_bytes", " state", " danger_type", " interrupt_reason", " hash", " end_time", " opened", " last_access_time", " transient", " referrer", " site_url", " tab_url", " tab_referrer_url", " http_method", " by_ext_id", " by_ext_name", " etag", " last_modified", " mime_type", " original_mime_type"};
    private ArrayList<DownloadsDTO> records;



//    public DownloadsTableModel(int period) throws ClassNotFoundException, SQLException{
//        DownloadsDAO dao = new DownloadsDAO();
//        records = dao.searchRecord(period);
//
//        fireTableDataChanged();
//    }

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
        DownloadsDTO to = records.get(rowIndex);
        switch (columnIndex) {
            case 0:
                result = to.getId();
                break;
            case 1:
                result = to.getGuid();
                break;
            case 2:
                result = to.getCurrent_path();
                break;
            case 3:
                result = to.getTarget_path();
                break;
            case 4:
                result = to.getStart_time();
                break;
            case 5:
                result = to.getReceived_bytes();
                break;
            case 6:
                result = to.getTotal_bytes();
                break;
            case 7:
                result = to.getState();
                break;
            case 8:
                result = to.getDanger_type();
                break;
            case 9:
                result = to.getInterrupt_reason();
                break;
            case 10:
                result = to.getHash();
                break;
            case 11:
                result = to.getEnd_time();
                break;
            case 12:
                result = to.getOpened();
                break;
            case 13:
                result = to.getLast_access_time();
                break;
            case 14:
                result = to.getDowload_transient();
                break;
            case 15:
                result = to.getReferrer();
                break;
            case 16:
                result = to.getSite_url();
                break;
            case 17:
                result = to.getTab_url();
                break;
            case 18:
                result = to.getTab_referrer_url();
                break;
            case 19:
                result = to.getHttp_method();
                break;
            case 20:
                result = to.getBy_ext_id();
                break;
            case 21:
                result = to.getBy_ext_name();
                break;
            case 22:
                result = to.getEtag();
                break;
            case 23:
                result = to.getLast_modified();
                break;
            case 24:
                result = to.getMime_type();
                break;
            case 25:
                result = to.getOriginal_mime_type();
                break;

        }
        return result;
    }
}


