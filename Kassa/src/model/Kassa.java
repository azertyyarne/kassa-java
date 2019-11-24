package model;

import database.*;

import java.util.List;

public class Kassa {
    private Database products;

    public Kassa() {
        products = new ExcelLoadSaveStrategy("artikel.xls");
    }

    public List<Product> getProducts(){
        return products.load();
    }
}
