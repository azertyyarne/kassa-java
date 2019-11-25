package model;

import database.*;
import javafx.scene.control.TextField;

import java.util.*;

public class Kassa {
    private Database products;
    private Map<String, Collection<Product>> tickets;

    public Kassa() {
        products = DatabaseFactory.getDatabase("Artikels excelbestand");
        tickets = new HashMap<>();
    }

    public List<Product> getProducts(){
        return products.load();
    }

    public Map<String, Collection<Product>> getTickets() {
        return tickets;
    }

    public void addProduct(int fieldProductCode) {
        tickets.get("klant").add(products.getProduct(fieldProductCode));
    }
}
