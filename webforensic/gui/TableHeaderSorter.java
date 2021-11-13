package gui;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TableHeaderSorter extends MouseAdapter {

    private TableSorter sorter;

    private JTable table;

    private TableHeaderSorter() {
    }

    public static void install(TableSorter sorter, JTable table) {
        TableHeaderSorter tableHeaderSorter = new TableHeaderSorter();
        tableHeaderSorter.sorter = sorter;
        tableHeaderSorter.table = table;
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.addMouseListener(tableHeaderSorter);
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
