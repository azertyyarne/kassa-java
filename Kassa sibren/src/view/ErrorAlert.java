package view;

import javafx.scene.control.Alert;

public class ErrorAlert {
    public static void show(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}
