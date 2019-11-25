package model;

import database.*;

import java.util.List;

public class Kassa {
    private Database products;

    public Kassa() {
        products = DatabaseFactory.getDatabase("Artikels excelbestand");
    }

    public List<Product> getProducts(){
        return products.load();
    }
}
