package com.almundo.callcenter.model;

/* Author Leandro Franco */
public class Director extends Employee {

    public Director(String name) {
        super(name);
        setPosition(Position.DIRECTOR);
    }
    
}
