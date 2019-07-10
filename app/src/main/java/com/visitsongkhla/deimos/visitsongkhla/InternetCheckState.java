package com.visitsongkhla.deimos.visitsongkhla;

public class InternetCheckState{
    static public int state = 1;

    static public int getState() {
        return state;
    }

    static public void setTrue() {
        state=1;
    }


    static public void setFalse() {
        state=0;
    }
    public InternetCheckState(int state) {
        this.state=state;
    }
}
