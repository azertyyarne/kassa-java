package database;

import model.Product;
import java.util.ArrayList;

public abstract class InMemoryDatabase implements Database {

    /*@Override
    public Product getProduct(int id) {
        if (load().get(id) == null)
            throw new DbException("Het product met code: " + id + " bestaat niet in de database");
        return products.get(id);
    }*/

    @Override
    public abstract ArrayList<Product> load();

    @Override
    public abstract void save(ArrayList<Product> products);
}
