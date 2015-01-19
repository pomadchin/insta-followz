package com.dc.insta.core;

public class Triple<F, S, T> {
    private F fst;
    private S snd;
    private T trd;

    public Triple(F fst, S snd, T trd) {
        this.fst = fst;
        this.snd = snd;
        this.trd = trd;
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

    public T getTrd() {
        return trd;
    }

    public void setTrd(T trd) {
        this.trd = trd;
    }

    @Override
    public String toString() {
        return "Triple{" +
                "fst=" + fst +
                ", snd=" + snd +
                ", trd=" + trd +
                '}';
    }

}
