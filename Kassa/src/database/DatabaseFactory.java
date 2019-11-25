package database;

public class DatabaseFactory {
    public static Database getDatabase(String description){
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
