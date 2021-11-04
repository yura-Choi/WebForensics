package webforensic.gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableSelectionDemo extends JPanel {
    private static final long serialVersionUID = 1L;
    static TableController tableController;
    static BottomPane bottom;
    public TableSelectionDemo(){
        super(new BorderLayout());
    }

    void addComponentToPane(){
        tableController = new TableController();
        tableController.init();
        JScrollPane center = new JScrollPane(tableController.table);
        add(center, BorderLayout.CENTER);

        bottom = new BottomPane();
        //bottom.init(GUIMain.engine.getColumnCount());
        add(bottom, BorderLayout.PAGE_END);

        setupTopPane();
    }

    void setupTopPane(){

        // JPanel topPane = new JPanel();
        // JTextField kwdTextField = new JTextField("", 20);
        // topPane.add(kwdTextField, BorderLayout.LINE_START);

        // Toolbar
        JToolBar mainToolBar = new JToolBar();
        JButton optionButton = new JButton(new ImageIcon("./src/webforensic/resources/option.png"));
        JButton saveButton = new JButton(new ImageIcon("./src/webforensic/resources/save.png"));
        JButton deleteButton = new JButton(new ImageIcon("./src/webforensic/resources/delete.png"));
        JButton refreshButton = new JButton(new ImageIcon("./src/webforensic/resources/refresh.png"));
        JButton copyButton = new JButton(new ImageIcon("./src/webforensic/resources/copy.png"));
        JButton propertiesButton = new JButton(new ImageIcon("./src/webforensic/resources/properties.png"));
        JButton findButton = new JButton(new ImageIcon("./src/webforensic/resources/find.png"));
        JButton qrcodeButton = new JButton(new ImageIcon("./src/webforensic/resources/qrcode.png"));
        JButton exitButton = new JButton(new ImageIcon("./src/webforensic/resources/exit.png"));

        mainToolBar.setFloatable(false);
        mainToolBar.setBackground(Color.lightGray);

        Dimension bSize = new Dimension(24, 24);
        sizeButton(optionButton, bSize);
        optionButton.setToolTipText("Advanced Options");
        optionButton.setHorizontalTextPosition(SwingConstants.CENTER);
        optionButton.setVerticalTextPosition(SwingConstants.CENTER);
        mainToolBar.add(optionButton);

        sizeButton(saveButton, bSize);
        saveButton.setToolTipText("Save Selected Items");
        saveButton.setHorizontalTextPosition(SwingConstants.CENTER);
        saveButton.setVerticalTextPosition(SwingConstants.CENTER);
        mainToolBar.add(saveButton);

        sizeButton(deleteButton, bSize);
        deleteButton.setToolTipText("Delete Selected History Records");
        deleteButton.setHorizontalTextPosition(SwingConstants.CENTER);
        deleteButton.setVerticalTextPosition(SwingConstants.CENTER);
        mainToolBar.add(deleteButton);

        sizeButton(refreshButton, bSize);
        refreshButton.setToolTipText("Refresh");
        refreshButton.setHorizontalTextPosition(SwingConstants.CENTER);
        refreshButton.setVerticalTextPosition(SwingConstants.CENTER);
        mainToolBar.add(refreshButton);

        sizeButton(copyButton, bSize);
        copyButton.setToolTipText("Copy Selected Items");
        copyButton.setHorizontalTextPosition(SwingConstants.CENTER);
        copyButton.setVerticalTextPosition(SwingConstants.CENTER);
        mainToolBar.add(copyButton);

        sizeButton(propertiesButton, bSize);
        propertiesButton.setToolTipText("Properties");
        propertiesButton.setHorizontalTextPosition(SwingConstants.CENTER);
        propertiesButton.setVerticalTextPosition(SwingConstants.CENTER);
        mainToolBar.add(propertiesButton);

        sizeButton(findButton, bSize);
        findButton.setToolTipText("Find");
        findButton.setHorizontalTextPosition(SwingConstants.CENTER);
        findButton.setVerticalTextPosition(SwingConstants.CENTER);
        mainToolBar.add(findButton);

        sizeButton(qrcodeButton, bSize);
        qrcodeButton.setToolTipText("Display QR Code");
        qrcodeButton.setHorizontalTextPosition(SwingConstants.CENTER);
        qrcodeButton.setVerticalTextPosition(SwingConstants.CENTER);
        mainToolBar.add(qrcodeButton);

        sizeButton(exitButton, bSize);
        exitButton.setToolTipText("Exit");
        exitButton.setHorizontalTextPosition(SwingConstants.CENTER);
        exitButton.setVerticalTextPosition(SwingConstants.CENTER);
        mainToolBar.add(exitButton);

        // topPane.add(mainToolBar, BorderLayout.PAGE_START);



        /*
        JButton search = new JButton("검색");
        topPane.add(search, BorderLayout.LINE_END);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("검색")){
                    tableController.loadData(kwdTextField.getText());
                }
            }
        });

        */
        add(mainToolBar, BorderLayout.PAGE_START);
    }

    private void sizeButton(JButton b, Dimension d) {
        b.setPreferredSize(d);
        b.setMinimumSize(d);
        b.setMaximumSize(d);
    }

}
