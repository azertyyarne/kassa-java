package model;

import database.*;
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

    public Product getProduct(int id) {
        if (!hasId(id)) {
            //kweet da dit niet mag ma kweet nog ni hoe ge da moet fixen, kheb geprobeerd om in de main Domainexceeption te catch ma da werkt ni
            JOptionPane.showMessageDialog(null, "Het product met code: " + id + " is niet aanwezig in de database", "Verkeerde code", JOptionPane.ERROR_MESSAGE);
            throw new ModelException("Het product met code: " + id + " is niet aanwezig in de database");
        }
        for (Product product : getProducts()) {
            if (product.getCode() == id)
                return product;
        }
        throw new ModelException("Het product met code: " + id + " is niet aanwezig in de winkelkar");
    }

    public void addProduct(Product product) {
        if (!tickets.containsKey("klant"))
            tickets.put("klant", new ArrayList<>());
        /*if (product.getStock() > 0)
            throw new ModelException("Stock is leeg");*/
        if (!tickets.get("klant").contains(product))
            tickets.get("klant").add(product);
        product.setQuantity(product.getQuantity()+1);
        /*product.setStock(product.getStock()-1);*/
    }

    private boolean hasId(int id) {
        for (Product product: getProducts())
            if (product.getCode() == id)
                return true;
        return false;
    }

    public void removeProduct(Product product) {
        if (!tickets.containsKey("klant"))
            throw new ModelException("De gegeven klant bestaat niet");
        if (!tickets.get("klant").contains(product) /*|| (product.getQuantity()-1) < 0*/)
            throw new ModelException("Niet aanwezig in winkelmand");
        if (product.getQuantity() == 1)
            tickets.get("klant").remove(product);
        product.setQuantity(product.getQuantity()-1);
        /*product.setStock(product.getStock()+1);*/
    }

    public Collection<Product> getTicketProducts(String klant)  /*misschien nog exception toevoegen*/
    {
        if(tickets.containsKey(klant)){
        return tickets.get(klant);
        }
        else
            return null;
    }
}
