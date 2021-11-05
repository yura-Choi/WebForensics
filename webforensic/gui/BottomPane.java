package gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("Serial")
public class BottomPane extends JPanel implements ActionListener {

    JTextField itemCnt;
    int columnCount, selectedCnt;

    void init(int columnCount){
        setLayout(new FlowLayout(FlowLayout.LEFT));

        this.columnCount = columnCount;
        itemCnt = new JTextField(columnCount+ "item(s)");
        itemCnt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        add(itemCnt);

        selectedCnt = 0;
    }
    /*
    JTextField edits[];
    int columnCount;

    void init(int columnCount){
        this.columnCount = columnCount;
        edits = new JTextField[columnCount];
        setLayout(new FlowLayout());
        for(int i=0; i<columnCount; i++){
            edits[i] = new JTextField("", 10);
            add(edits[i]);
        }
        JButton editBtn = new JButton("수정");
        editBtn.setActionCommand("Done");
        editBtn.addActionListener(this);
        add(editBtn);

        setLayout(new FlowLayout());
        JButton buttons[] = new JButton[2];
        String btnTexts[] = {"추가", "삭제"};
        for(int i=0; i<2; i++){
            buttons[i] = new JButton(btnTexts[i]);
            buttons[i].addActionListener(this);
            add(buttons[i]);
        }
    }

    void clearEdits(){
        for(JTextField edit : edits){
            edit.setText("");
        }
    }

    // 편집창의 내용을 배열에 넣어 돌려줌 항목의 추가나 수정시 사용할 데이터
    String[] getEditTexts(){
        String[] editTexts = new String[columnCount];
        for(int i=0; i<columnCount; i++)
            editTexts[i] = edits[i].getText();
        return editTexts;
    }

    // 현재 선택된 행의 데이터를 편집창에 하나씩 넣어둠
    void moveSelectedToEdits(String[] rowTexts){
        for(int i=0; i<rowTexts.length; i++){
            edits[i].setText(rowTexts[i]);
        }
    }
    */
    public void actionPerformed(ActionEvent e){
        /*
        String[] editTexts = getEditTexts();
        clearEdits();
        TableController tableController = TableSelectionDemo.tableController;
        switch (e.getActionCommand()){
            case "추가":
                tableController.addRow(editTexts);
                break;
            case "Done":
                tableController.updateRow(editTexts);
                break;
            case "삭제":
                tableController.removeRow();
                break;
            default: break;
        }
        tableController.table.clearSelection();*/
    }
}