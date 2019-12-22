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
    private Pane paneHeader,paneBody,paneFooter;
    private TextArea areaHeaderMessage,areaFooterMessage;


    public SettingsOverviewPane(){
        this.setPadding(new Insets(20, 20, 20, 20));
        this.setVgap(20);
        this.setHgap(20);
        databaseSettings();
        kortingSettings();
        kassabonSettings();
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

    private void kassabonSettings(){
        Label labelKassabon = new Label("Kies kassabon");
        paneHeader = new VBox();
        paneHeader.getChildren().add(new Label("Header"));
        paneBody = new VBox();
        paneBody.getChildren().add(new Label("Body"));
        paneFooter = new VBox();
        paneFooter.getChildren().add(new Label("Footer"));
        areaHeaderMessage = new TextArea();
        areaFooterMessage = new TextArea();
        areaHeaderMessage.setMaxHeight(100);
        areaHeaderMessage.setMaxWidth(200);
        areaFooterMessage.setMaxHeight(100);
        areaFooterMessage.setMaxWidth(200);
        this.add(labelKassabon,0,3);
        this.add(paneHeader,1,3);
        this.add(paneBody,2,3);
        this.add(paneFooter,3,3);
    }

    public void addListenerHeader(){
        Label labelHeaderMessage = new Label("Boodschap:");
        ((CheckBox) paneHeader.getChildren().get(1)).selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.getChildren().remove(labelHeaderMessage);
            this.getChildren().remove(areaHeaderMessage);
            if (newValue){
                this.add(labelHeaderMessage,1,4);
                this.add(areaHeaderMessage,1,5);
            }
        });
    }

    public void addListenerFooter(){
        Label labelFooterMessage = new Label("Afsluitlijn:");
        ((CheckBox) paneFooter.getChildren().get(4)).selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.getChildren().remove(labelFooterMessage);
            this.getChildren().remove(areaFooterMessage);
            if (newValue){
                this.add(labelFooterMessage,3,4);
                this.add(areaFooterMessage,3,5);
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

    public TextArea getAreaHeaderMessage() {
        return areaHeaderMessage;
    }

    public TextArea getAreaFooterMessage() {
        return areaFooterMessage;
    }

    public ComboBox<String> getComboBoxGroup() {
        return comboBoxGroup;
    }

    public Pane getPaneHeader() {
        return paneHeader;
    }

    public Pane getPaneBody() {
        return paneBody;
    }

    public Pane getPaneFooter() {
        return paneFooter;
    }
}
