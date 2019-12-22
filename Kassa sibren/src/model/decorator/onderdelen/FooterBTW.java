package model.decorator.onderdelen;

import model.Verkoop;
import model.decorator.KassabonOnderdeel;

public class FooterBTW extends KassabonOnderdeel {

    @Override
    public String print(Verkoop verkoop) {
        return onderdeel.print(verkoop) + getString(verkoop);
    }

    private String getString(Verkoop verkoop) {
        return String.format("%-20s %26.2f\n%-20s %31.2f\n","Totale prijs zonder BTW: ",verkoop.getTotalPrice() - verkoop.getBTW(),"BTW bedrag: ",verkoop.getBTW());
    }
}
