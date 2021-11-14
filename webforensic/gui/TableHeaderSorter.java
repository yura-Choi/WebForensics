package gui;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TableHeaderSorter extends MouseAdapter {
    private static TableHeaderSorter instance = new TableHeaderSorter();
    private TableHeaderSorter(){}

    public static TableHeaderSorter getInstance() { return instance; }
    private TableSorter sorter;
    private JTable table;



    public static void install(TableSorter sorter, JTable table) {
        instance.sorter = sorter;
        instance.table = table;
        JTableHeader tableHeader = instance.table.getTableHeader();
        tableHeader.addMouseListener(instance);
    }

    public static void changeTable(TableModel tableModel, TableColumnModel columnModel) {
        instance.table.setColumnModel(columnModel);
        instance.sorter.setModel(tableModel);
        // JTableHeader tableHeader = instance.table.getTableHeader();
    }

    public void mouseClicked(MouseEvent mouseEvent) {
        TableColumnModel columnModel = table.getColumnModel();
        int viewColumn = columnModel.getColumnIndexAtX(mouseEvent.getX());
        int column = table.convertColumnIndexToModel(viewColumn);
        int clickCount = mouseEvent.getClickCount();
        if (clickCount >= 1 && column != -1) {
            // System.out.println("Sorting ...");
            //int shiftPressed = (mouseEvent.getModifiers() & InputEvent.SHIFT_MASK);
            boolean ascending = (clickCount == 1);
            sorter.sortByColumn(column, ascending);
        }
    }
}
