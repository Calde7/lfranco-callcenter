package com.almundo.callcenter.model;

/* Author Leandro Franco */
public class Operator extends Employee {

    public Operator(String name) {
        super(name);
        setPosition(Position.OPERATOR);
    }

}
