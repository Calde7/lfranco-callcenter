package com.almundo.callcenter.model;

/* Author Leandro Franco */
public enum Position {

    OPERATOR(1),
    SUPERVISOR(2),
    DIRECTOR(3);

    private Integer value;

    public Integer getValue() {
        return value;
    }

    Position(Integer i) {
        this.value = i;
    }

}
