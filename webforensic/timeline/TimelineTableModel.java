package timeline;


import cache.CacheDAO;
import cache.CacheTableModel;
import cookies.CookiesDAO;
import cookies.CookiesTableModel;
import downloads.DownloadsDAO;
import downloads.DownloadsTableModel;
import urls.UrlsDAO;
import urls.UrlsTableModel;

import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.ArrayList;

public class TimelineTableModel extends AbstractTableModel{

    private static TimelineTableModel instance = new TimelineTableModel();
    private TimelineTableModel(){

    }

    public static TimelineTableModel getInstance() { return instance; }

    private static String[] columnNames = {"no.", "Type", "URL", "Access Time"};
    private ArrayList<TimelineDTO> records = new ArrayList<>();

    public void setRecords(ArrayList<TimelineDTO> records) { this.records = records; }

    public String getColumnName(int column){
        return columnNames[column];
    }

    public void searchRecord(int days) throws ClassNotFoundException, SQLException {
        TimelineDAO dao = TimelineDAO.getInstance();
        records = dao.searchRecord(days);
        UrlsTableModel.getInstance().setRecords(UrlsDAO.getInstance().records);
        DownloadsTableModel.getInstance().setRecords(DownloadsDAO.getInstance().records);
        CookiesTableModel.getInstance().setRecords(CookiesDAO.getInstance().records);
        CacheTableModel.getInstance().setRecords(CacheDAO.getInstance().records);

        fireTableDataChanged();
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
        TimelineDTO to = records.get(rowIndex);
        switch (columnIndex) {
            case 0:
                result = to.getId();
                break;
            case 1:
                result = to.getTable_type();
                break;
            case 2:
                result = to.getUrl();
                break;
            case 3:
                result = to.getAccess_time();
                break;
        }
        return result;
    }
}