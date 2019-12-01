package database.factory;

import database.DbException;

import java.util.ArrayList;
import java.util.List;

public enum ProductDBEnum {
    INMEMORY("InMemory","database.InMemoryDB"),
    RELATIONAL("Relational","");

    private final String description,className;

    ProductDBEnum(String description, String className){
        this.description = description;
        this.className = className;
    }

    public static List<String> getDescriptions(){
        List<String> descriptions = new ArrayList<>();
        for (ProductDBEnum productDBEnum : ProductDBEnum.values()){
            descriptions.add(productDBEnum.description);
        }
        return descriptions;
    }

    public String getClassName() {
        return className;
    }

    public static ProductDBEnum getProductDBeEnum(String description){
        for (ProductDBEnum productDBEnum : ProductDBEnum.values()){
            if (description.equals(productDBEnum.description)){
                return productDBEnum;
            }
        }
        throw new DbException("Er is geen database met omschrijving \"" + description + "\"");
    }
}
