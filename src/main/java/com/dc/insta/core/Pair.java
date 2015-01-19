package com.dc.insta.core;

public class Pair<F, S> {
    private F fst;
    private S snd;

    public Pair(F fst, S snd) {
        this.fst = fst;
        this.snd = snd;
    }

    public F getFst() {
        return fst;
    }

    public void setFst(F fst) {
        this.fst = fst;
    }

    public S getSnd() {
        return snd;
    }

    public void setSnd(S snd) {
        this.snd = snd;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "fst=" + fst +
                ", snd=" + snd +
                '}';
    }
}
