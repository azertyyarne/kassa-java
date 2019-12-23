package database;

import model.Product;

import java.util.*;

public class InMemoryDB implements ProductDBstrategy {
    private LoadSaveStrategy loadSave;
    private Map<String,Product> products = new HashMap<>();

    public void setLoadSave(LoadSaveStrategy loadSave) {
        this.loadSave = loadSave;
        addProducts();
    }

    public void addProducts(){
        List<Product> list = loadSave.load();
        for (Product product : list){
            products.put(product.getCode(),product);
        }
    }

    public void save(){
        loadSave.save(new ArrayList<Product>(products.values()));
    }

    @Override
    public Collection<Product> getProducts() {
        return products.values();
    }

    @Override
    public Product getProduct(String code) {
        if (!products.containsKey(code)){
            throw new DbException("Er is geen product met code " + code);
        }
        return products.get(code);
    }
}
