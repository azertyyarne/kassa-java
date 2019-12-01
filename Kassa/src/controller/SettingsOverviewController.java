package controller;

import model.Kassa;
import view.panels.SettingsOverviewPane;

import java.util.Properties;

public class SettingsOverviewController {
    private Kassa model;
    private SettingsOverviewPane view;
    private Properties properties;

    public SettingsOverviewController(Kassa model, SettingsOverviewPane view,Properties properties){
        this.model = model;
        this.view = view;
        this.properties = properties;
        view.getComboBoxProductDB().getSelectionModel().select(properties.getProperty("productDB"));
        view.getComboBoxProductDB().setOnAction(event -> properties.setProperty("productDB",view.getComboBoxProductDB().getValue()));
        view.getComboBoxLoadSave().getSelectionModel().select(properties.getProperty("loadSave"));
        view.getComboBoxLoadSave().setOnAction(event -> properties.setProperty("loadSave",view.getComboBoxLoadSave().getValue()));
    }
}
