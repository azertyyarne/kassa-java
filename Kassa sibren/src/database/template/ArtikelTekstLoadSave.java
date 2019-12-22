package database.template;

import model.Product;
import java.util.*;

public class ArtikelTekstLoadSave extends TekstLoadSaveTemplate {
    @Override
    public String getFilepath() {
        return "src/bestanden/artikel.txt";
    }

    @Override
    protected Product getObject(List<String> parameters) {
        String code = parameters.get(0);
        String name = parameters.get(1);
        String group = parameters.get(2);
        double price = Double.parseDouble(parameters.get(3));
        int stock = Integer.parseInt(parameters.get(4));
        return new Product(code,name,group,price,stock);
    }

    @Override
    protected String getString(Object o) {
        Product product = (Product) o;
        return String.format("%s,%s,%s,%f,%d",product.getCode(),product.getName(),product.getGroup(),product.getPrice(),product.getStock());
    }
}