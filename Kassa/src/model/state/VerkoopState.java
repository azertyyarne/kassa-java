package model.state;

import model.ModelException;
import model.Verkoop;

public abstract class VerkoopState {
    protected Verkoop verkoop;
    protected String state;

    public VerkoopState(Verkoop verkoop){
        this.verkoop = verkoop;
    }

    public void putOnHold(){
        throw new ModelException("Verkoop kan niet on hold gezet worden. Omdat verkoop in state '" + state + "' is.");
    }

    public void activate(){
        throw new ModelException("Verkoop kan niet actief gezet worden. Omdat verkoop in state '" + state + "' is.");
    }

    public void pay(){
        throw new ModelException("Verkoop kan niet afgekerend worden. Omdat verkoop in state '" + state + "' is.");
    }

    public void cancel(){
        throw new ModelException("Verkoop kan niet geannuleerd worden. Omdat verkoop in state '" + state + "' is.");
    }
}