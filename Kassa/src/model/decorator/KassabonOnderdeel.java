package model.decorator;

public abstract class KassabonOnderdeel extends DecoratorKassabon{
    protected DecoratorKassabon onderdeel;

    public void setOnderdeel(DecoratorKassabon onderdeel) {
        this.onderdeel = onderdeel;
    }
}
