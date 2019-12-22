package controller;

import model.Kassa;
import model.observer.ObserverLogger;
import view.panels.LogsPane;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class LogsController implements ObserverLogger {
    private Kassa model;
    private LogsPane view;

    public LogsController(Kassa model,LogsPane view){
        this.model = model;
        model.addObserverLogger(this);
        this.view = view;
    }

    @Override
    public void log() {
        String logs = view.getAreaLogs().getText();
        logs += String.format("Totale prijs: %.2f euro | ",model.getTotalPriceVerkoop());
        logs += String.format("Totale korting: %.2f euro | ",model.getTotalKortingVerkoop());
        logs += String.format("Totaal te betalen: %.2f euro | ",model.getTotalPriceVerkoop() - model.getTotalKortingVerkoop());
        logs += LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
        logs += "\n";
        view.getAreaLogs().setText(logs);
    }
}
