package org.example.frame;

import org.example.dto.ItemDTO;
import org.example.service.ItemService;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;

public class MainFrameGUI {
    private final JFrame jFrame;
    private final JTextField fieldCode, fieldName, fieldPrice, fieldQuantity;

    public MainFrameGUI(JFrame jFrame) {
        this.jFrame = jFrame;
        fieldCode = new JTextField();
        fieldName = new JTextField();
        fieldPrice = new JTextField();
        fieldQuantity = new JTextField();
    }

    public JPanel createInputPanel() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Kod:"));
        inputPanel.add(fieldCode);
        inputPanel.add(new JLabel("Nazwa:"));
        inputPanel.add(fieldName);
        inputPanel.add(new JLabel("Cena:"));
        inputPanel.add(fieldPrice);
        inputPanel.add(new JLabel("Ilość:"));
        inputPanel.add(fieldQuantity);
        return inputPanel;
    }

    public JScrollPane createItemList(DefaultListModel<String> itemListModel) {
        JList<String> productList = new JList<>(itemListModel);
        return new JScrollPane(productList);
    }

    public JPanel createButtonPanel(DefaultListModel<String> itemListModel, List<ItemDTO> items, ItemService itemService) {
        JPanel buttonPanel = new JPanel();

        JButton buttonAdd = new JButton("Dodaj");
        buttonPanel.add(buttonAdd);
        buttonAdd.addActionListener((ActionEvent e) -> {
            try {
                List<ItemDTO> savedProducts = itemService.readFromFile();
                int id = (!savedProducts.isEmpty()) ? savedProducts.getLast().getId() + 1 : 1;
                String code = fieldCode.getText();
                String name = fieldName.getText();
                double price = Double.parseDouble(fieldPrice.getText());
                int quantity = Integer.parseInt(fieldQuantity.getText());
                ItemDTO itemDTO = new ItemDTO(id, code, name, price, quantity);
                items.add(itemDTO);
                itemListModel.addElement(itemDTO.toString());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(jFrame, "Błąd podczas dodawania przedmiotu", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton buttonSave = new JButton("Dodaj do listy");
        buttonPanel.add(buttonSave);
        buttonSave.addActionListener((ActionEvent e) -> {
            try {
                itemService.saveToFile(items);
                JOptionPane.showMessageDialog(jFrame, "Przedmiot dodany do listy!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(jFrame, "Błąd podczas dodawania do listy!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton buttonLoad = new JButton("Załaduj listę");
        buttonPanel.add(buttonLoad);
        buttonLoad.addActionListener((ActionEvent e) -> {
            try {
                List<ItemDTO> savedProducts = itemService.readFromFile();
                itemListModel.clear();
                items.clear();
                items.addAll(savedProducts);
                savedProducts.forEach(product -> itemListModel.addElement(product.toString()));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(jFrame, "Błąd podczas ładowania listy!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return buttonPanel;
    }
}
