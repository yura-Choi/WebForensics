package downloads;

import cookies.CookiesDTO;
import gui.TableSelectionDemo;

import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.ArrayList;

public class DownloadsTableModel extends AbstractTableModel {
    private static DownloadsTableModel instance = new DownloadsTableModel();
    private DownloadsTableModel(){

    }

    public static DownloadsTableModel getInstance(){ return instance; }

    public static String[] getColumnNames() {
        return columnNames;
    }

    public static void setColumnNames(String[] columnNames) {
        DownloadsTableModel.columnNames = columnNames;
    }

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

    public void setRecords(ArrayList<DownloadsDTO> records) { this.records = records; }

    @Override
    public int getRowCount(){
        return records.size();
    }

    @Override
    public int getColumnCount(){
        int column_n=0;
        for(int i=0; i < columnNames.length; i++){
            if(TableSelectionDemo.getInstance().filter_On.get(1)[i] == true){
                column_n++;
            }
        }

        return column_n;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        int filter = 0;
        int max = columnIndex;
        for(int i=0; i < max + 1; i++){
            if(TableSelectionDemo.getInstance().filter_On.get(1)[i] == false){
                filter++;
                max++;
            }
        }

        Object result = null;
        DownloadsDTO to = records.get(rowIndex);
        switch (columnIndex + filter) {
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

    public String[] detail(String url, String date) {
        int i;
        for (i = 0; i < records.size(); i++) {
            if (url.equals(records.get(i).getTab_url()) && date.equals(records.get(i).getLast_access_time())) {
                break;
            }
        }
        int column = columnNames.length;
        String[] ret = new String[column];
        for (int j = 0; j < column; j++) {
            String result = null;
            DownloadsDTO to = records.get(i);
            switch (j) {
                case 0:
                    result = String.valueOf(to.getId());
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
                    result = String.valueOf(to.getReceived_bytes());
                    break;
                case 6:
                    result = String.valueOf(to.getTotal_bytes());
                    break;
                case 7:
                    result = String.valueOf(to.getState());
                    break;
                case 8:
                    result = String.valueOf(to.getDanger_type());
                    break;
                case 9:
                    result = String.valueOf(to.getInterrupt_reason());
                    break;
                case 10:
                    result = to.getHash();
                    break;
                case 11:
                    result = to.getEnd_time();
                    break;
                case 12:
                    result = String.valueOf(to.getOpened());
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

            ret[j] = result;
        }

        return ret;
    }
}


