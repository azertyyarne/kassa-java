package model.state;

import model.Verkoop;

public class CancelledState extends VerkoopState {

    public CancelledState(Verkoop verkoop){
        super(verkoop);
        state = "Cancelled";
    }
}
