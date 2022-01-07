package gui;


import javax.swing.*;
import java.awt.*;

@SuppressWarnings("Serial")
public class BottomPane extends JPanel {

    private static BottomPane instance = new BottomPane();
    private BottomPane() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        itemCnt = new JTextField("0 item(s)", 20);
        itemCnt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        add(itemCnt);
    }
    public static BottomPane getInstance(){ return instance; }

    JTextField itemCnt;
    int columnCount, selectedCnt;

    void updateItemsCount(int columnCount){
        this.columnCount = columnCount;
        String s = columnCount + " item(s)";
        itemCnt.setText(s);
        selectedCnt = 0;
    }

}