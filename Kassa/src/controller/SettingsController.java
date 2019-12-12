package controller;

import database.InMemoryDB;
import database.ProductDBstrategy;
import database.factory.FactoryDB;
import database.factory.LoadSaveEnum;
import database.factory.ProductDBEnum;
import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import model.GroupEnum;
import model.Kassa;
import model.ModelException;
import model.factory.FactoryModel;
import model.factory.KortingEnum;
import model.kortingStrategy.DrempelKorting;
import model.kortingStrategy.GroepKorting;
import model.kortingStrategy.KortingStrategy;
import view.panels.SettingsOverviewPane;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

public class SettingsController {
    private Kassa model;
    private SettingsOverviewPane view;
    private Properties properties;
    private String filepath = "src/bestanden/kassa.properties";

    public SettingsController(Kassa model, SettingsOverviewPane view){
        this.model = model;
        this.view = view;
    }

    public void setup(){
        readProperties();
        setProductDB();
        setKorting();
        setupView();
    }

    private void setupView(){
        view.getComboBoxProductDB().setItems(FXCollections.observableArrayList(ProductDBEnum.getDescriptions()));
        view.getComboBoxProductDB().getSelectionModel().select(ProductDBEnum.getDescription(properties.getProperty("productDB")));

        view.getComboBoxLoadSave().setItems(FXCollections.observableArrayList(LoadSaveEnum.getDescriptions()));
        view.getComboBoxLoadSave().getSelectionModel().select(LoadSaveEnum.getDescription(properties.getProperty("loadSave")));

        view.getComboboxKorting().setItems(FXCollections.observableArrayList(KortingEnum.getDescriptions()));
        view.getComboboxKorting().getSelectionModel().select(KortingEnum.getDescription(properties.getProperty("korting")));

        view.getComboBoxGroup().setItems(FXCollections.observableArrayList(GroupEnum.getDescriptions()));
        view.getComboBoxGroup().getSelectionModel().select(properties.getProperty("groep"));

        view.getFieldProcent().setText(properties.getProperty("procent"));
        view.getFieldBedrag().setText(properties.getProperty("drempel"));
    }

    private String getTypeFromPane(Pane pane){
        return ((Label) pane.getChildren().get(0)).getText().toLowerCase();
    }

    private void setProductDB(){
        FactoryDB factoryDB = FactoryDB.getInstance();
        ProductDBstrategy strategy = factoryDB.getProductDB(properties.getProperty("productDB"));
        if (strategy instanceof InMemoryDB){
            ((InMemoryDB) strategy).setLoadSave(factoryDB.getLoadSave(properties.getProperty("loadSave")));
        }
        model.setProductDB(strategy);
    }

    private void setKorting(){
        FactoryModel factoryModel = FactoryModel.getInstance();
        KortingStrategy kortingStrategy = factoryModel.getKorting(properties.getProperty("korting"));
        kortingStrategy.setProcent(Integer.parseInt(properties.getProperty("procent")));
        if (kortingStrategy instanceof GroepKorting){
            ((GroepKorting) kortingStrategy).setKortingsGroep(properties.getProperty("groep"));
        }
        if (kortingStrategy instanceof DrempelKorting){
            ((DrempelKorting) kortingStrategy).setDrempel(Integer.parseInt(properties.getProperty("drempel")));
        }
        model.setKortingStrategy(kortingStrategy);
    }

    public void breakDown(){
        setProperties();
        writeProperties();
        model.getProductDB().saveProducts();
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

    private void setProperties(){
        properties.setProperty("productDB",ProductDBEnum.getName(view.getComboBoxProductDB().getValue()));
        properties.setProperty("loadSave",LoadSaveEnum.getName(view.getComboBoxLoadSave().getValue()));
        properties.setProperty("korting",KortingEnum.getName(view.getComboboxKorting().getValue()));
        properties.setProperty("procent",view.getFieldProcent().getText());
        properties.setProperty("drempel",view.getFieldBedrag().getText());
        properties.setProperty("groep",view.getComboBoxGroup().getValue());
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
