package model.korting;

import model.Product;
import model.Verkoop;

public class Groepkorting extends KortingStrategy{
    private String group;

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public double getTotalKorting(Verkoop verkoop) {
        double result = 0;
        for (Product product : verkoop.getAllProducts()){
            if (product.getGroup().equals(group)){
                result  += product.getPrice() * (procent / 100);
            }
        }
        return result;
    }
}
