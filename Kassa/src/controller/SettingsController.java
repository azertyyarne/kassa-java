package controller;

import database.InMemoryDB;
import database.ProductDBstrategy;
import database.factory.FactoryDB;
import database.factory.LoadSaveEnum;
import database.factory.ProductDBEnum;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import model.GroupEnum;
import model.Kassa;
import model.ModelException;
import model.decorator.DecoratorKassabon;
import model.decorator.Kassabon;
import model.decorator.KassabonOnderdeel;
import model.decorator.onderdelen.HasMessage;
import model.factory.FactoryModel;
import model.factory.KassabonOnderdeelEnum;
import model.factory.KortingEnum;
import model.korting.Drempelkorting;
import model.korting.Groepkorting;
import model.korting.KortingStrategy;
import view.panels.SettingsOverviewPane;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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
        setKassabon();
        setupView();
    }

    private void setupView(){
        setupViewDatabase();
        setupViewKorting();
        setupViewKassabon();
    }

    private void setupViewDatabase(){
        view.getComboBoxProductDB().setItems(FXCollections.observableArrayList(ProductDBEnum.getDescriptions()));
        view.getComboBoxProductDB().getSelectionModel().select(ProductDBEnum.getDescription(properties.getProperty("productDB")));

        view.getComboBoxLoadSave().setItems(FXCollections.observableArrayList(LoadSaveEnum.getDescriptions()));
        view.getComboBoxLoadSave().getSelectionModel().select(LoadSaveEnum.getDescription(properties.getProperty("loadSave")));
    }

    private void setupViewKorting(){
        view.getComboboxKorting().setItems(FXCollections.observableArrayList(KortingEnum.getDescriptions()));
        view.getComboboxKorting().getSelectionModel().select(KortingEnum.getDescription(properties.getProperty("korting")));

        view.getComboBoxGroup().setItems(FXCollections.observableArrayList(GroupEnum.getDescriptions()));
        view.getComboBoxGroup().getSelectionModel().select(properties.getProperty("groep"));

        view.getFieldProcent().setText(properties.getProperty("procent"));
        view.getFieldBedrag().setText(properties.getProperty("drempel"));
    }

    private void setupViewKassabon(){
        view.getPaneHeader().getChildren().addAll(getCheckboxes(KassabonOnderdeelEnum.getDescriptionsOfType("header")));
        view.addListenerHeader();
        selectCheckBoxes(view.getPaneHeader());

        view.getPaneBody().getChildren().addAll(getCheckboxes(KassabonOnderdeelEnum.getDescriptionsOfType("body")));
        selectCheckBoxes(view.getPaneBody());

        view.getPaneFooter().getChildren().addAll(getCheckboxes(KassabonOnderdeelEnum.getDescriptionsOfType("footer")));
        view.addListenerFooter();
        selectCheckBoxes(view.getPaneFooter());

        view.getAreaHeaderMessage().setText(properties.getProperty("headermessagetxt"));
        view.getAreaFooterMessage().setText(properties.getProperty("footermessagetxt"));
    }

    private List<Node> getCheckboxes(List<String> descriptions){
        List<Node> nodes = new ArrayList<>();
        for (String description : descriptions){
            CheckBox box = new CheckBox(description);
            if (description.equals("Standaard")){
                box.setDisable(true);
            }
            nodes.add(box);
        }
        return nodes;
    }

    private void selectCheckBoxes(Pane pane){
        String type = getTypeFromPane(pane);
        for (Node node : pane.getChildren()){
            if (node instanceof CheckBox){
                CheckBox box = (CheckBox) node;
                box.setSelected(Boolean.parseBoolean(properties.getProperty(KassabonOnderdeelEnum.getName(box.getText(),type))));
            }
        }
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
        if (kortingStrategy instanceof Groepkorting){
            ((Groepkorting) kortingStrategy).setGroup(properties.getProperty("groep"));
        }
        if (kortingStrategy instanceof Drempelkorting){
            ((Drempelkorting) kortingStrategy).setDrempel(Integer.parseInt(properties.getProperty("drempel")));
        }
        model.setKorting(kortingStrategy);
    }

    private void setKassabon(){
        FactoryModel factory = FactoryModel.getInstance();
        model.setKassabon(factory.getKassabon(properties));
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
        setKassabonProperties(view.getPaneHeader(),"header");
        setKassabonProperties(view.getPaneBody(),"body");
        setKassabonProperties(view.getPaneFooter(),"footer");
        properties.setProperty("headermessagetxt",view.getAreaHeaderMessage().getText());
        properties.setProperty("footermessagetxt",view.getAreaFooterMessage().getText());
    }

    private void setKassabonProperties(Pane pane,String type){
        for (Node node : pane.getChildren()){
            if (node instanceof CheckBox){
                properties.setProperty(KassabonOnderdeelEnum.getName(((CheckBox) node).getText(),type),String.valueOf(((CheckBox) node).isSelected()));
            }
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
