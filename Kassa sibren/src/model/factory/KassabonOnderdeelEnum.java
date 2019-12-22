package model.factory;

import model.ModelException;

import java.util.ArrayList;
import java.util.List;

public enum KassabonOnderdeelEnum {
    HEADER_MESSAGE("Algemene boodschap","headermessage","model.decorator.onderdelen.HeaderMessage","header"),
    HEADER_DATETIME("Datum en tijd","headerdatetime","model.decorator.onderdelen.HeaderDateTime","header"),
    HEADER_STANDARD("Standaard","headerStandard","model.decorator.onderdelen.HeaderStandard","header"),
    BODY_STANDARD("Standaard","bodyStandard","model.decorator.onderdelen.BodyStandard","body"),
    FOOTER_STANDARD("Standaard","footerStandard","model.decorator.onderdelen.FooterStandard","footer"),
    FOOTER_KORTING("Prijs en korting","footerkorting","model.decorator.onderdelen.FooterKorting","footer"),
    FOOTER_BTW("BTW","footerBTW","model.decorator.onderdelen.FooterBTW","footer"),
    FOOTER_MESSAGE("Algemene afsluitlijn","footermessage","model.decorator.onderdelen.FooterMessage","footer");

    private String description,name,className,type;

    KassabonOnderdeelEnum(String description,String name,String className,String type) {
        this.description = description;
        this.name = name;
        this.className = className;
        this.type = type;
    }

    public static List<String> getDescriptionsOfType(String type){
        List<String> descriptions = new ArrayList<>();
        for (KassabonOnderdeelEnum kassabonOnderdeelEnum : KassabonOnderdeelEnum.values()){
            if (type.equals(kassabonOnderdeelEnum.type)){
                descriptions.add(kassabonOnderdeelEnum.description);
            }
        }
        return descriptions;
    }

    public String getName() {
        return name;
    }

    public String getClassName() {
        return className;
    }

    public String getType() {
        return type;
    }

    public static String getName(String description, String type){
        for (KassabonOnderdeelEnum kassabonOnderdeelEnum : KassabonOnderdeelEnum.values()){
            if (description.equals(kassabonOnderdeelEnum.description) && type.equals(kassabonOnderdeelEnum.type)){
                return kassabonOnderdeelEnum.name;
            }
        }
        throw new ModelException("Er is geen kassabon onderdeel met omschrijving \"" + description + "\"");
    }

    public static String getDescription(String name){
        for (KassabonOnderdeelEnum kassabonOnderdeelEnum : KassabonOnderdeelEnum.values()){
            if (name.equals(kassabonOnderdeelEnum.name)){
                return kassabonOnderdeelEnum.description;
            }
        }
        throw new ModelException("Er is geen kassabon onderdeel met naam \"" + name + "\"");
    }

    public static KassabonOnderdeelEnum getKassabonOnderdeelEnum(String name){
        for (KassabonOnderdeelEnum kassabonOnderdeelEnum : KassabonOnderdeelEnum.values()){
            if (name.equals(kassabonOnderdeelEnum.name)){
                return kassabonOnderdeelEnum;
            }
        }
        throw new ModelException("Er is geen kassabon onderdeel met naam \"" + name + "\"");
    }
}
