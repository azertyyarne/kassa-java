package controller;

import model.Kassa;
import model.ModelException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

public class KassaController {
    private Kassa model;
    private String filepath;
    private Properties properties;

    public KassaController(Kassa model, String filepath){
        this.model = model;
        this.filepath = filepath;
    }

    public Properties getProperties(){
        return properties;
    }

    public void setUp(){
        readProperties(filepath);
        model.setDatabase(properties.getProperty("database"));
        model.loadProducts();
    }

    public void breakDown(){
        model.saveProducts();
        writeProperties();
    }

    private void readProperties(String filepath) {
        properties = new Properties();
        try{
            InputStream is = new FileInputStream(new File(filepath));
            properties.load(is);
            is.close();
        }
        catch (Exception e){
            throw new ModelException(e.getMessage());
        }
    }

    private void writeProperties(){
        try{
            FileOutputStream os = new FileOutputStream(new File(filepath));
            properties.store(os,"Configuration kassa");
            os.close();
        }
        catch (Exception e){
            throw new ModelException(e.getMessage());
        }
    }
}
