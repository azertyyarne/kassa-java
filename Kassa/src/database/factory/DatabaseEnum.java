package database.factory;

import database.DbException;

import java.util.ArrayList;
import java.util.List;

public enum DatabaseEnum {
    ARTIKELTEKST("ArtikelsTekstbestand","database.template.ArtikelTekstLoadSave"),
    ARTIKELEXCEL("ArtikelsExcelbestand","database.ExcelLoadSaveStrategy");

    private final String description,className;

    DatabaseEnum(String description,String className){
        this.description = description;
        this.className = className;
    }

    public static List<String> getDescriptions(){
        List<String> descriptions = new ArrayList<>();
        for (DatabaseEnum databaseEnum : DatabaseEnum.values()){
            descriptions.add(databaseEnum.description);
        }
        return descriptions;
    }

    public String getClassName() {
        return className;
    }

    public static DatabaseEnum getDatabaseEnum(String description){
        for (DatabaseEnum databaseEnum : DatabaseEnum.values()){
            if (description.equals(databaseEnum.description)){
                return databaseEnum;
            }
        }
        throw new DbException("Er is geen database met omschrijving \"" + description + "\"");
    }
}
