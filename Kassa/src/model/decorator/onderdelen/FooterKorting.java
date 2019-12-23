package model.decorator.onderdelen;

import model.Verkoop;
import model.decorator.KassabonOnderdeel;

public class FooterKorting extends KassabonOnderdeel {

    @Override
    public String print(Verkoop verkoop) {
        return onderdeel.print(verkoop) + getString(verkoop);
    }

    private String getString(Verkoop verkoop) {
        return String.format("%-20s %31.2f\n%-20s %31.2f\n","Totale prijs: ",verkoop.getTotalPrice(),"Totale korting: ",verkoop.getKorting());
    }
}
