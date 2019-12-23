package model.factory;

import model.ModelException;
import model.decorator.DecoratorKassabon;
import model.decorator.Kassabon;
import model.decorator.KassabonOnderdeel;
import model.decorator.onderdelen.HasMessage;
import model.korting.KortingStrategy;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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

    public KassabonOnderdeel getKassabonOnderdeel(String name) {
        KassabonOnderdeelEnum kassabonOnderdeelEnum = KassabonOnderdeelEnum.getKassabonOnderdeelEnum(name);
        String className = kassabonOnderdeelEnum.getClassName();
        KassabonOnderdeel kassabonOnderdeel = null;
        try{
            Class kassaOnderdeelClass = Class.forName(className);
            Object kassaOnderdeelObject = kassaOnderdeelClass.newInstance();
            kassabonOnderdeel = (KassabonOnderdeel) kassaOnderdeelObject;
        }
        catch (Exception e){
            throw new ModelException(e.getMessage());
        }
        return kassabonOnderdeel;
    }

    public DecoratorKassabon getKassabon(Properties properties){
        DecoratorKassabon kassabon = new Kassabon();
        for (KassabonOnderdeel onderdeel : getKassabonOnderdelen(properties)){
            onderdeel.setOnderdeel(kassabon);
            kassabon = onderdeel;
        }
        return kassabon;
    }

    private List<KassabonOnderdeel> getKassabonOnderdelen(Properties properties){
        List<KassabonOnderdeel> onderdelen = new ArrayList<>();
        for (KassabonOnderdeelEnum kassaOnderdeelEnum : KassabonOnderdeelEnum.values()){
            if (Boolean.parseBoolean(properties.getProperty(kassaOnderdeelEnum.getName()))){
                KassabonOnderdeel onderdeel = factory.getKassabonOnderdeel(kassaOnderdeelEnum.getName());
                if (onderdeel instanceof HasMessage){
                    String message = properties.getProperty(kassaOnderdeelEnum.getType() + "messagetxt");
                    ((HasMessage) onderdeel).setMessage(message);
                }
                onderdelen.add(onderdeel);
            }
        }
        return onderdelen;
    }
}
