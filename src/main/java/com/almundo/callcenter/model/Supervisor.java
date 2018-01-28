package com.almundo.callcenter.model;

/* Author Leandro Franco */
public class Supervisor extends Employee {

    public Supervisor(String name) {
        super(name);
        setPosition(Position.SUPERVISOR);
    }
}
