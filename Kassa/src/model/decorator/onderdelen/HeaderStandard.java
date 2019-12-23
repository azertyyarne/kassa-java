package model.decorator.onderdelen;

import model.Verkoop;
import model.decorator.KassabonOnderdeel;

public class HeaderStandard extends KassabonOnderdeel {

    @Override
    public String print(Verkoop verkoop) {
        return onderdeel.print(verkoop) + getString(verkoop);
    }

    private String getString(Verkoop verkoop){
        return String.format("%-20s %10s %20s\n","Omschrijving","Aantal","Prijs");
    }
}
