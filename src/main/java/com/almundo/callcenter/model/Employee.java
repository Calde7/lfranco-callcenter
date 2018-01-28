package com.almundo.callcenter.model;

/* Author Leandro Franco */
public class Employee implements Comparable<Employee> {
    
    private String name;
    private Position position;    
    
    public Employee(String name) {
        this.name = name;                
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }
    
    @Override
    public int compareTo(Employee o) {
        return this.position.getValue().compareTo(o.getPosition().getValue());
    }

    @Override
    public String toString() {
        return name;
    }
}
