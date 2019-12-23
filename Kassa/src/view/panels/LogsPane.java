package view.panels;

import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class LogsPane extends GridPane {
    private TextArea areaLogs;

    public LogsPane(){
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        areaLogs = new TextArea();
        areaLogs.setPrefWidth(740);
        areaLogs.setPrefHeight(490);
        areaLogs.setEditable(false);
        this.add(areaLogs,0,0);
    }

    public TextArea getAreaLogs() {
        return areaLogs;
    }
}
