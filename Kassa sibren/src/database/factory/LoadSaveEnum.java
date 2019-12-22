package database.factory;

import database.DbException;

import java.util.ArrayList;
import java.util.List;

public enum LoadSaveEnum {
    ARTIKELTEKST("Artikels Tekstbestand","tekst","database.template.ArtikelTekstLoadSave"),
    ARTIKELEXCEL("Artikels Excelbestand","excel","database.ExcelLoadSave");

    private final String description,name,className;

    LoadSaveEnum(String description,String name,String className){
        this.description = description;
        this.name = name;
        this.className = className;
    }

    public static List<String> getDescriptions(){
        List<String> descriptions = new ArrayList<>();
        for (LoadSaveEnum databaseEnum : LoadSaveEnum.values()){
            descriptions.add(databaseEnum.description);
        }
        return descriptions;
    }

    public String getClassName() {
        return className;
    }

    public static String getName(String description){
        for (LoadSaveEnum loadSaveEnum : LoadSaveEnum.values()){
            if (description.equals(loadSaveEnum.description)){
                return loadSaveEnum.name;
            }
        }
        throw new DbException("Er is geen LoadSave met omschrijving \"" + description + "\"");
    }

    public static String getDescription(String name){
        for (LoadSaveEnum loadSaveEnum : LoadSaveEnum.values()){
            if (name.equals(loadSaveEnum.name)){
                return loadSaveEnum.description;
            }
        }
        throw new DbException("Er is geen LoadSave met naam \"" + name + "\"");
    }

    public static LoadSaveEnum getLoadSaveEnum(String name){
        for (LoadSaveEnum databaseEnum : LoadSaveEnum.values()){
            if (name.equals(databaseEnum.name)){
                return databaseEnum;
            }
        }
        throw new DbException("Er is geen LoadSave met naam \"" + name + "\"");
    }
}
