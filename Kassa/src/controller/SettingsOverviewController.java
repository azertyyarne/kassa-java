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
        view.getComboBox().getSelectionModel().select(properties.getProperty("database"));
        view.getComboBox().setOnAction(event -> properties.setProperty("database",view.getComboBox().getValue()));
    }
}
