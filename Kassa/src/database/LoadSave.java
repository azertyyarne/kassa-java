package database;

import model.Product;

import java.util.ArrayList;

public interface LoadSave {
    ArrayList<Product> load();
    void save(ArrayList<Product> products);
}
