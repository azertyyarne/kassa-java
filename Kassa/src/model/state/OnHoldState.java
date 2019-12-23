package model.state;

import model.Verkoop;

public class OnHoldState extends VerkoopState {

    public OnHoldState(Verkoop verkoop){
        super(verkoop);
        state = "On hold";
    }

    @Override
    public void activate() {
        verkoop.setState(verkoop.getActiveState());
    }

    @Override
    public void cancel(){
        verkoop.setState(verkoop.getCancelledState());
    }
}
