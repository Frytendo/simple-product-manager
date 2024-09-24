package org.example.service;
import org.example.dto.ItemDTO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ItemService {

    private static final String FILENAME = "items.txt";

    public void saveToFile(List<ItemDTO> products) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME))) {
            for (ItemDTO product : products) {
                writer.write(STR."\{product.getId()},\{product.getCode()},\{product.getName()},\{product.getPrice()},\{product.getQuantity()}");
                writer.newLine();
            }
        }
    }

    public List<ItemDTO> readFromFile() throws IOException {
        List<ItemDTO> products = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                int id = Integer.parseInt(fields[0]);
                String code = fields[1];
                String name = fields[2];
                double price = Double.parseDouble(fields[3]);
                int quantity = Integer.parseInt(fields[4]);
                products.add(new ItemDTO(id, code, name, price, quantity));
            }
        }
        return products;
    }
}