package database.factory;

import database.DbException;

import java.util.ArrayList;
import java.util.List;

public enum ProductDBEnum {
    INMEMORY("In Memory","inMemory","database.InMemoryDB"),
    RELATIONAL("Relational Database","relational","");

    private final String description,name,className;

    ProductDBEnum(String description,String name, String className){
        this.description = description;
        this.name = name;
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

    public static String getName(String description){
        for (ProductDBEnum productDBEnum : ProductDBEnum.values()){
            if (description.equals(productDBEnum.description)){
                return productDBEnum.name;
            }
        }
        throw new DbException("Er is geen database met omschrijving \"" + description + "\"");
    }

    public static String getDescription(String name){
        for (ProductDBEnum productDBEnum : ProductDBEnum.values()){
            if (name.equals(productDBEnum.name)){
                return productDBEnum.description;
            }
        }
        throw new DbException("Er is geen database met naam \"" + name + "\"");
    }

    public static ProductDBEnum getProductDBeEnum(String name){
        for (ProductDBEnum productDBEnum : ProductDBEnum.values()){
            if (name.equals(productDBEnum.name)){
                return productDBEnum;
            }
        }
        throw new DbException("Er is geen database met naam \"" + name + "\"");
    }
}
