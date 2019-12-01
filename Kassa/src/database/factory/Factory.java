package database.factory;

import database.DbException;
import database.LoadSaveStrategy;
import database.ProductDBstrategy;

public class Factory {
    private static Factory factory = new Factory();

    private Factory(){

    }

    public static Factory getInstance(){
        return factory;
    }

    public LoadSaveStrategy getLoadSave(String description){
        LoadSaveEnum loadSaveEnum = LoadSaveEnum.getLoadSaveEnum(description);
        String className = loadSaveEnum.getClassName();
        LoadSaveStrategy loadSave = null;
        try{
            Class dbClass = Class.forName(className);
            Object dbObject = dbClass.newInstance();
            loadSave = (LoadSaveStrategy) dbObject;
        }
        catch (Exception e){
            throw new DbException(e.getMessage());
        }
        return loadSave;
    }

    public ProductDBstrategy getProductDB(String description){
        ProductDBEnum productDBEnum = ProductDBEnum.getProductDBeEnum(description);
        String className = productDBEnum.getClassName();
        ProductDBstrategy productDB = null;
        try{
            Class dbClass = Class.forName(className);
            Object dbObject = dbClass.newInstance();
            productDB = (ProductDBstrategy) dbObject;
        }
        catch (Exception e){
            throw new DbException(e.getMessage());
        }
        return productDB;
    }
}
