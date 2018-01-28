package com.almundo.callcenter.controller;

import com.almundo.callcenter.model.Employee;
import java.util.concurrent.Callable;

/* Author Leandro Franco */
public class IncomingCall implements Callable<Employee>{

    private Integer call;
    private Dispatcher dispatcher;
        
    public IncomingCall(Dispatcher dispatcher, Integer call) {
        this.dispatcher = dispatcher;
        this.call = call;
    }

    @Override
    public Employee call() throws Exception {
        return dispatcher.dispatchCall(call);
    }
}
