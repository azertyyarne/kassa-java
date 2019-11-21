package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;

public class Company {
    private ObservableList<Artikel> data;

    public Company() throws Exception {
        data = FXCollections.observableArrayList(new ArrayList<Artikel>());
        data.add(new Artikel(125871559, "Chicken nuggets", (float) 5.95));
    }
}
