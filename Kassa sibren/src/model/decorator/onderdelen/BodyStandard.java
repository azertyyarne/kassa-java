package model.decorator.onderdelen;

import model.Product;
import model.Verkoop;
import model.decorator.KassabonOnderdeel;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BodyStandard extends KassabonOnderdeel {

    @Override
    public String print(Verkoop verkoop) {
        return onderdeel.print(verkoop) + getString(verkoop);
    }

    private String getString(Verkoop verkoop){
        List<Product> products = verkoop.getProducts();
        products.sort(Comparator.comparing(Product::getName));// = products.sort((Product p1,Product p2) -> p1.getName().compareTo(p2.getName()));

        StringBuilder sb = new StringBuilder();
        for (Product product : products){
            sb.append(String.format("%-20s %9d %21.2f\n",product.getName(),verkoop.getQuantity(product),product.getPrice()));
        }
        String line = String.join("",Collections.nCopies(52,"-")) + "\n";
        return line + sb.toString() + line;
    }
}
