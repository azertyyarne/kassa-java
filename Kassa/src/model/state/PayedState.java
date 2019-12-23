package model.state;

import model.Verkoop;

public class PayedState extends VerkoopState {

    public PayedState(Verkoop verkoop){
        super(verkoop);
        state = "Payed";
    }
}
