package database;

import model.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class InMemoryDatabase implements Database {

    protected Map<Integer,Product> products = new HashMap<>();

    @Override
    public Product getProduct(int id) {
        if (products.get(id) == null)
            throw new DbException("Dit product bestaat niet in de database");
        return products.get(id);
    }

    @Override
    public abstract ArrayList<Product> load();

    @Override
    public abstract void save(ArrayList<Product> products);
}
