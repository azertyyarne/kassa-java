package view.panels;

import database.factory.DatabaseEnum;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;

public class SettingsOverviewPane extends GridPane {
    private ComboBox<String> comboBox;


    public SettingsOverviewPane(){
        this.setPadding(new Insets(20, 20, 20, 20));
        this.setVgap(20);
        this.setHgap(20);
        comboBox = new ComboBox<>(FXCollections.observableArrayList(DatabaseEnum.getDescriptions()));
        this.add(comboBox,0,0);
    }

    public ComboBox<String> getComboBox() {
        return comboBox;
    }
}
