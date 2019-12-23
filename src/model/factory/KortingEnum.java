package model.factory;

import database.DbException;
import model.ModelException;

import java.util.ArrayList;
import java.util.List;

public enum KortingEnum {
    GEEN("Geen Korting","geen","model.korting.Geenkorting"),
    GROEP("Groep Korting","groep","model.korting.Groepkorting"),
    DREMPEL("Drempel Korting","drempel","model.korting.Drempelkorting"),
    DUURSTE("Duurste Korting","duurste","model.korting.Duurstekorting");

    private final String description,name,className;

    KortingEnum(String description,String name,String className){
        this.description = description;
        this.name = name;
        this.className = className;
    }

    public static List<String> getDescriptions(){
        List<String> descriptions = new ArrayList<>();
        for (KortingEnum kortingEnum : KortingEnum.values()){
            descriptions.add(kortingEnum.description);
        }
        return descriptions;
    }

    public String getClassName() {
        return className;
    }

    public static String getName(String description){
        for (KortingEnum kortingEnum : KortingEnum.values()){
            if (description.equals(kortingEnum.description)){
                return kortingEnum.name;
            }
        }
        throw new DbException("Er is geen korting met omschrijving \"" + description + "\"");
    }

    public static String getDescription(String name){
        for (KortingEnum kortingEnum : KortingEnum.values()){
            if (name.equals(kortingEnum.name)){
                return kortingEnum.description;
            }
        }
        throw new DbException("Er is geen korting met naam \"" + name + "\"");
    }

    public static KortingEnum getKortingEnum(String name){
        for (KortingEnum kortingEnum : KortingEnum.values()){
            if (name.equals(kortingEnum.name)){
                return kortingEnum;
            }
        }
        throw new ModelException("Er is geen korting met naam \"" + name + "\"");
    }
}
