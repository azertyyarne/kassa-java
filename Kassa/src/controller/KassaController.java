package controller;

import database.InMemoryDB;
import database.ProductDBstrategy;
import database.factory.Factory;
import model.Kassa;
import model.ModelException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

public class KassaController {
    private Kassa model;
    private Properties properties;
    private String filepath = "src/bestanden/kassa.properties";

    public KassaController(Kassa model){
        this.model = model;
    }

    public Properties getProperties(){
        return properties;
    }

    public void setUp(){
        readProperties();
        Factory factory = Factory.getInstance();
        ProductDBstrategy strategy = factory.getProductDB(properties.getProperty("productDB"));
        if (strategy instanceof InMemoryDB){
            ((InMemoryDB) strategy).setLoadSave(factory.getLoadSave(properties.getProperty("loadSave")));
        }
        model.setProductDB(strategy);
    }

    public void breakDown(){
        model.getProductDB().saveProducts();
        writeProperties();
    }

    private void readProperties() {
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
