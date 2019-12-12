package model.factory;

import model.ModelException;
import model.kortingStrategy.KortingStrategy;

public class FactoryModel {
    private static FactoryModel factory = new FactoryModel();

    private FactoryModel(){

    }

    public static FactoryModel getInstance(){
        return factory;
    }

    public KortingStrategy getKorting(String name){
        KortingEnum kortingEnum = KortingEnum.getKortingEnum(name);
        String className = kortingEnum.getClassName();
        KortingStrategy korting = null;
        try{
            Class kortingClass = Class.forName(className);
            Object kortingObject = kortingClass.newInstance();
            korting = (KortingStrategy) kortingObject;
        }
        catch (Exception e){
            throw new ModelException(e.getMessage());
        }
        return korting;
    }
}
