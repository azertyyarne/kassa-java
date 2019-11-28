package database.factory;

import database.Database;
import database.DbException;

public class Factory {
    private static Factory factory = new Factory();

    private Factory(){

    }

    public static Factory getInstance(){
        return factory;
    }

    public Database getDatabase(String description){
        DatabaseEnum databaseEnum = DatabaseEnum.getDatabaseEnum(description);
        String className = databaseEnum.getClassName();
        Database database = null;
        try{
            Class dbClass = Class.forName(className);
            Object dbObject = dbClass.newInstance();
            database = (Database) dbObject;
        }
        catch (Exception e){
            throw new DbException(e.getMessage());
        }
        return database;
    }
}
