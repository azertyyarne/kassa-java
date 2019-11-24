package database;

import excel.ExcelPlugin;
import model.Product;

import java.io.File;
import java.util.ArrayList;

public class ExcelLoadSaveStrategy implements Database {
    private String filepath;
    private ExcelPlugin excelPlugin = new ExcelPlugin();

    public ExcelLoadSaveStrategy(String filename){
        filepath = "src/bestanden/" + filename;
    }

    @Override
    public ArrayList<Product> load() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            for (ArrayList<String> parameters : excelPlugin.read(new File(filepath))){
                int code = Integer.parseInt(parameters.get(0));
                String name = parameters.get(1);
                String group = parameters.get(2);
                double price = Double.parseDouble(parameters.get(3));
                int stock = Integer.parseInt(parameters.get(4));
                Product product = new Product(code,name,group,price,stock);
                products.add(product);
            }
        }
        catch (Exception e){
            throw new DbException(e.getMessage());
        }
        return products;
    }

    @Override
    public void save(ArrayList<Product> products) {

    }
}
