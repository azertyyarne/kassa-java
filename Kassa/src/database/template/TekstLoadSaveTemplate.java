package database.template;

import database.DbException;

import java.io.File;
import java.util.*;

public abstract class TekstLoadSaveTemplate {

    protected final void readFromFile(String filepath){
        try{
            File file = new File(filepath);
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){
                List<String> parameters = processLine(sc.nextLine());
                addObject(parameters);
            }
            sc.close();
        }
        catch (Exception e){
            throw new DbException(e.getMessage());
        }
    }

    protected List<String> processLine(String line){
        List<String> parameters = new ArrayList<>();
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter(",");
        while (scanner.hasNext()){
            parameters.add(scanner.next());
        }
        return parameters;
    }

    protected abstract void addObject(List<String> parameters);
}
