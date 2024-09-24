package org.example.frame;


import org.example.dto.ItemDTO;
import org.example.service.ItemService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {

    private final DefaultListModel<String> itemListModel;
    private final List<ItemDTO> items;
    private final ItemService itemService;
    private final MainFrameGUI mainFrameGUI;

    public MainFrame() {
        itemListModel = new DefaultListModel<>();
        items = new ArrayList<>();
        itemService = new ItemService();
        mainFrameGUI = new MainFrameGUI(this);

        setTitle("Menadżer Produktów Lite");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = mainFrameGUI.createInputPanel();
        add(inputPanel, BorderLayout.NORTH);
        // Product List
        JScrollPane productListPanel = mainFrameGUI.createItemList(itemListModel);
        add(productListPanel, BorderLayout.CENTER);
        // Button Panel
        JPanel buttonPanel = mainFrameGUI.createButtonPanel(itemListModel, items, itemService);
        add(buttonPanel, BorderLayout.SOUTH);
    }

}
