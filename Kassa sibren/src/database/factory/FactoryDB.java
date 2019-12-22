package database.factory;

import database.DbException;
import database.LoadSaveStrategy;
import database.ProductDBstrategy;

public class FactoryDB {
    private static FactoryDB factory = new FactoryDB();

    private FactoryDB(){

    }

    public static FactoryDB getInstance(){
        return factory;
    }

    public LoadSaveStrategy getLoadSave(String name){
        LoadSaveEnum loadSaveEnum = LoadSaveEnum.getLoadSaveEnum(name);
        String className = loadSaveEnum.getClassName();
        LoadSaveStrategy loadSave = null;
        try{
            Class loadsaveClass = Class.forName(className);
            Object loadsaveObject = loadsaveClass.newInstance();
            loadSave = (LoadSaveStrategy) loadsaveObject;
        }
        catch (Exception e){
            throw new DbException(e.getMessage());
        }
        return loadSave;
    }

    public ProductDBstrategy getProductDB(String name){
        ProductDBEnum productDBEnum = ProductDBEnum.getProductDBeEnum(name);
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
