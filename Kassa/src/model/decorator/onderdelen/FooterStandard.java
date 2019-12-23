package model.decorator.onderdelen;

import model.Verkoop;
import model.decorator.KassabonOnderdeel;

public class FooterStandard extends KassabonOnderdeel {

    @Override
    public String print(Verkoop verkoop) {
        return onderdeel.print(verkoop) + getString(verkoop);
    }

    private String getString(Verkoop verkoop) {
        return String.format("%-20s %23.2f\n","Betaald (inclusief korting):",verkoop.getTotalPrice() - verkoop.getKorting());
    }
}
