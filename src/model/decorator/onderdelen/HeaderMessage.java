package model.decorator.onderdelen;

import model.Verkoop;
import model.decorator.KassabonOnderdeel;

import java.util.Collections;

public class HeaderMessage extends KassabonOnderdeel implements HasMessage {
    private String message;

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String print(Verkoop verkoop) {
        return onderdeel.print(verkoop) + getString(verkoop);
    }

    private String getString(Verkoop verkoop) {
        return message + "\n" + String.join("", Collections.nCopies(52,"*")) + "\n";
    }
}
