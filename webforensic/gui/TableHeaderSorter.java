package gui;


import timeline.TimelineTableModel;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TableHeaderSorter extends MouseAdapter {
    private static TableHeaderSorter instance = new TableHeaderSorter();
    private TableHeaderSorter(){ascending = true;}

    public static TableHeaderSorter getInstance() { return instance; }

    public TimelineTableModel timelineTable_model = TimelineTableModel.getInstance();

    private TableSorter sorter;
    private JTable table;
    private boolean ascending;

    public static void install(TableSorter sorter, JTable table) {
        instance.sorter = sorter;
        instance.table = table;
        JTableHeader tableHeader = instance.table.getTableHeader();
        tableHeader.addMouseListener(instance);
        table.addMouseListener(instance);
    }

    public void mouseClicked(MouseEvent mouseEvent) {
        TableColumnModel columnModel = table.getColumnModel();
        int viewColumn = columnModel.getColumnIndexAtX(mouseEvent.getX());
        int column = table.convertColumnIndexToModel(viewColumn);

        int clickCount = mouseEvent.getClickCount();
        if(clickCount == 2) {

            if(mouseEvent.getComponent() == table){
                if(instance.sorter.getModel() == timelineTable_model) {
                    TableSelectionDemo.getInstance().setDetailDialog(mouseEvent);
                }
            }

            else {
                if (column != -1 && clickCount >= 2) {
                    ascending = !ascending;
                    sorter.sortByColumn(column, ascending);
                }
            }
        }

    }

}
