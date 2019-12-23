package model.state;

import model.Verkoop;

public class ActiveState extends VerkoopState {

    public ActiveState(Verkoop verkoop){
        super(verkoop);
        state = "Active";
    }

    @Override
    public void putOnHold() {
        verkoop.setState(verkoop.getOnholdState());
    }

    @Override
    public void pay() {
        verkoop.setState(verkoop.getPayedState());
    }

    @Override
    public void cancel() {
        verkoop.setState(verkoop.getCancelledState());
    }
}
