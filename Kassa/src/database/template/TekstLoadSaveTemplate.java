package database.template;

import database.Database;
import database.DbException;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

public abstract class TekstLoadSaveTemplate implements Database {

    @Override
    public final ArrayList load(){
        ArrayList result = new ArrayList();
        try{
            File file = new File(getFilepath());
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){
                List<String> parameters = processLine(sc.nextLine());
                result.add(getObject(parameters));
            }
            sc.close();
        }
        catch (Exception e){
            throw new DbException(e.getMessage());
        }
        return result;
    }

    protected abstract String getFilepath();

    protected List<String> processLine(String line){
        List<String> parameters = new ArrayList<>();
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter(",");
        while (scanner.hasNext()){
            parameters.add(scanner.next());
        }
        return parameters;
    }

    protected abstract Object getObject(List<String> parameters);

    @Override
    public final void save(ArrayList objects){
        try{
            File file = new File(getFilepath());
            FileWriter fw = new FileWriter(file);
            for (Object o : objects){
                fw.write(getString(o) + "\n");
            }
            fw.close();
        }
        catch (Exception e){
            throw new DbException(e.getMessage());
        }
    }

    protected abstract String getString(Object o);
}
