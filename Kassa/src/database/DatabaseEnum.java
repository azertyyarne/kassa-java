package database;

import java.util.ArrayList;
import java.util.List;

public enum DatabaseEnum {
    ARTIKELTEKST("Artikels tekstbestand","database.template.ArtikelTekstLoadSave"),
    ARTIKELEXCEL("Artikels excelbestand","database.ExcelLoadSaveStrategy");

    private final String description,className;

    DatabaseEnum(String description,String className){
        this.description = description;
        this.className = className;
    }

    public List<String> getDescriptions(){
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
