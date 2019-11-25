package database.template;

import database.DbException;
import model.Product;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class ArtikelTekstLoadSave extends TekstLoadSaveTemplate {
    private String filepath = "src/bestanden/artikel.txt";

    public ArtikelTekstLoadSave() {
        readFromFile(filepath);
    }

    @Override
    protected void addObject(List<String> parameters) {
        int code = Integer.parseInt(parameters.get(0));
        String name = parameters.get(1);
        String group = parameters.get(2);
        double price = Double.parseDouble(parameters.get(3));
        int stock = Integer.parseInt(parameters.get(4));
        Product product = new Product(code,name,group,price,stock);
        addProduct(product);
    }

    @Override
    public ArrayList<Product> load(){
        return new ArrayList<>(products.values());
    }

    @Override
    public void save(ArrayList<Product> products){
        try{
            File file = new File(filepath);
            FileWriter fw = new FileWriter(file);
            for (Product product : products){
                fw.write(String.format("%d,%s,%s,%f,%d\n",product.getCode(),product.getName(),product.getGroup(),product.getPrice(),product.getStock()));
            }
            fw.close();
        }
        catch (Exception e){
            throw new DbException(e.getMessage());
        }
    }

    private void addProduct(Product product){
        if (products.containsKey(product.getCode())){
            throw new DbException("Product met code " + product.getCode() + " zit al in de database");
        }
        products.put(product.getCode(),product);
    }
}