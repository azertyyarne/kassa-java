package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;

public class Company {
    private ObservableList<Product> data;

    public Company() throws Exception {
        data = FXCollections.observableArrayList(new ArrayList<Product>());
        data.add(new Product(125871559, "Chicken nuggets", "vlees", 5.95, 10));
    }

    public void addDummyProduct() {
        int id = (int) (Math.random()*10000);
        data.add(new Product(id, "dummy prod"+id, "groep"+((int) (Math.random()*10)), Math.random()*6, 10));
    }

    public int getAantalProducts() {
        return data.size();
    }

    public ObservableList<Product> loadData() {
        return data;
    }
}
