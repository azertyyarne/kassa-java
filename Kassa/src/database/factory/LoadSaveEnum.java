package database.factory;

import database.DbException;

import java.util.ArrayList;
import java.util.List;

public enum LoadSaveEnum {
    ARTIKELTEKST("ArtikelsTekstbestand","database.template.ArtikelTekstLoadSave"),
    ARTIKELEXCEL("ArtikelsExcelbestand","database.ExcelLoadSave");

    private final String description,className;

    LoadSaveEnum(String description, String className){
        this.description = description;
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

    public static LoadSaveEnum getLoadSaveEnum(String description){
        for (LoadSaveEnum databaseEnum : LoadSaveEnum.values()){
            if (description.equals(databaseEnum.description)){
                return databaseEnum;
            }
        }
        throw new DbException("Er is geen database met omschrijving \"" + description + "\"");
    }
}
