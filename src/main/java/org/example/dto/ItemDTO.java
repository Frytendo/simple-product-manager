package org.example.dto;


import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ItemDTO {
    private int id;
    private String code;
    private String name;
    private double price;
    private int quantity;


    @Override
    public String toString() {
        return String.format("ID: %d, Kod: %s, Nazwa: %s, Cena: %.2f, Ilość: %d", id, code, name, price, quantity);
    }
}
