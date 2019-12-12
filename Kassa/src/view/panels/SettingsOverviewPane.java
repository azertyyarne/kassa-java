package view.panels;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SettingsOverviewPane extends GridPane {
    private ComboBox<String> comboBoxLoadSave,comboBoxProductDB,comboboxKorting,comboBoxGroup;
    private TextField fieldProcent,fieldBedrag;
    private Label label,labelProcent;


    public SettingsOverviewPane(){
        this.setPadding(new Insets(20, 20, 20, 20));
        this.setVgap(20);
        this.setHgap(20);
        databaseSettings();
        kortingSettings();
    }

    private void databaseSettings(){
        Label labelDatabase = new Label("Kies databank");
        comboBoxProductDB = new ComboBox<>();
        comboBoxLoadSave = new ComboBox<>();
        this.add(labelDatabase,0,0);
        this.add(comboBoxProductDB,1,0);
        comboBoxProductDB.valueProperty().addListener((args,oldValue,newValue) -> {
            this.getChildren().remove(comboBoxLoadSave);
            if (newValue.equals("In Memory")){
                this.add(comboBoxLoadSave,2,0);
            }
        });
    }

    private void kortingSettings(){
        Label labelKorting = new Label("Kies korting");
        labelKorting.setAlignment(Pos.BASELINE_CENTER);
        comboboxKorting = new ComboBox<>();
        comboBoxGroup = new ComboBox<>();
        fieldProcent = new TextField();
        fieldBedrag = new TextField();
        labelProcent = new Label("Procent:");
        label = new Label();
        this.add(labelKorting,0,1,1,2);
        this.add(comboboxKorting,1,1);
        comboboxKorting.valueProperty().addListener((args,oldValue,newValue) -> {
            this.getChildren().remove(fieldBedrag);
            this.getChildren().remove(comboBoxGroup);
            this.getChildren().remove(fieldProcent);
            this.getChildren().remove(label);
            this.getChildren().remove(labelProcent);
            if (!newValue.equals("Geen Korting")){
                this.add(labelProcent,1,2);
                this.add(fieldProcent,2,2);
            }
            if (newValue.equals("Groep Korting")){
                label.setText("Geef de groep: ");
                this.add(label,2,1);
                this.add(comboBoxGroup,3,1);
            }
            if (newValue.equals("Drempel Korting")){
                label.setText("Geef de drempel: ");
                this.add(label,2,1);
                this.add(fieldBedrag,3,1);
            }
        });
    }

    public ComboBox<String> getComboBoxLoadSave() {
        return comboBoxLoadSave;
    }

    public ComboBox<String> getComboBoxProductDB() {
        return comboBoxProductDB;
    }

    public ComboBox<String> getComboboxKorting() {
        return comboboxKorting;
    }

    public TextField getFieldProcent() {
        return fieldProcent;
    }

    public TextField getFieldBedrag() {
        return fieldBedrag;
    }

    public ComboBox<String> getComboBoxGroup() {
        return comboBoxGroup;
    }


}
