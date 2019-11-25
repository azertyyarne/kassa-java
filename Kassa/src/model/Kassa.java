package model;

import database.*;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.util.*;

public class Kassa {
    private List<Product> products;
    private Map<String, Collection<Product>> tickets;

    public Kassa() {
        products = DatabaseFactory.getDatabase("Artikels excelbestand").load();
        tickets = new HashMap<>();
    }

    public List<Product> getProducts(){
        return products;
    }

    public Map<String, Collection<Product>> getTickets() {
        return tickets;
    }

    public void addProduct(int id) {
        if (!tickets.containsKey("klant"))
            tickets.put("klant", new ArrayList<>());
        tickets.get("klant").add(getProduct(id));
    }

    public Product getProduct(int id) {
        for (Product product : getProducts()) {
            if (product.getCode() == id)
                return product;
        }
        //kweet da dit niet mag ma kweet nog ni hoe ge da moet fixen, kheb geprobeerd om in de main Domainexceeption te catch ma da werkt ni
        JOptionPane.showMessageDialog(null,"Het product met code: "+id+" bestaat niet in de database", "Verkeerde code", JOptionPane.ERROR_MESSAGE);
        throw new ModelException("Het product met code: "+id+" bestaat niet in de database");
    }
}
