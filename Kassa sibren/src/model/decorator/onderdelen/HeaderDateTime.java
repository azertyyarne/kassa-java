package model.decorator.onderdelen;

import model.Verkoop;
import model.decorator.KassabonOnderdeel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Collections;

public class HeaderDateTime extends KassabonOnderdeel {

    @Override
    public String print(Verkoop verkoop) {
        return onderdeel.print(verkoop) + getString(verkoop);
    }

    private String getString(Verkoop verkoop) {
        String string = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
        int spaces = (52 - string.length()) / 2;
        String padding = String.join("", Collections.nCopies(spaces," "));
        return  padding + string+ padding + "\n\n";
    }
}
