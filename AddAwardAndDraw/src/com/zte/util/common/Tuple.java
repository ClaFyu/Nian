package com.zte.util.common;

public class Tuple<T, U> {
    private T first;
    private U second;

    public Tuple(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public void setFirst(T first) { this.first = first; }
    public T getFirst() { return first; }
    public void setSecond(U second) { this.second = second;}
    public U getSecond() { return second; }
}
