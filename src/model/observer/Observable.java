package model.observer;

public interface Observable {
    void addObserverUpdate(ObserverUpdate observerUpdate);
    void addObserverVerkoop(ObserverVerkoop observerVerkoop);
    void addObserverLogger(ObserverLogger observerLogger);

    void updateObservers();
    void afsluitMenu();
    void standardMenu();
    void notifyObservers();
}
