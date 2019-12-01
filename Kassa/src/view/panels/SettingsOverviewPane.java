package view.panels;

import database.factory.LoadSaveEnum;
import database.factory.ProductDBEnum;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;

public class SettingsOverviewPane extends GridPane {
    private ComboBox<String> comboBoxLoadSave,comboBoxProductDB;


    public SettingsOverviewPane(){
        this.setPadding(new Insets(20, 20, 20, 20));
        this.setVgap(20);
        this.setHgap(20);
        comboBoxProductDB = new ComboBox<>(FXCollections.observableArrayList(ProductDBEnum.getDescriptions()));
        comboBoxLoadSave = new ComboBox<>(FXCollections.observableArrayList(LoadSaveEnum.getDescriptions()));
        this.add(comboBoxProductDB,0,0);
        this.add(comboBoxLoadSave,1,0);
        comboBoxProductDB.valueProperty().addListener((args,oldValue,newValue) -> {
            if (newValue.equals("InMemory")){
                comboBoxLoadSave.setVisible(true);
            }
            else comboBoxLoadSave.setVisible(false);
        });
    }

    public ComboBox<String> getComboBoxLoadSave() {
        return comboBoxLoadSave;
    }

    public ComboBox<String> getComboBoxProductDB() {
        return comboBoxProductDB;
    }
}
