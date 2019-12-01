package database;

import java.util.ArrayList;

public interface LoadSaveStrategy {
    ArrayList load();
    void save(ArrayList products);
}
