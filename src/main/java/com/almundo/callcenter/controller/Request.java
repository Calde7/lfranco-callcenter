package com.almundo.callcenter.controller;

import com.almundo.callcenter.model.Director;
import com.almundo.callcenter.model.Employee;
import com.almundo.callcenter.model.Operator;
import com.almundo.callcenter.model.Supervisor;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;
import org.apache.log4j.Logger;

/* Author Leandro Franco */
public class Request {

    private static final Logger logger = Logger.getLogger(Request.class);

    public static void play() {
        PriorityBlockingQueue<Employee> employee = new PriorityBlockingQueue<>();
        for (int i = 0; i < 5; i++) {
            employee.add(new Operator("Operador N° " + i));
        }
        for (int i = 0; i < 1; i++) {
            employee.add(new Supervisor("Supervisor N° " + i));
        }
        for (int i = 0; i < 1; i++) {
            employee.add(new Director("Director N° " + i));
        }
        Dispatcher d = new Dispatcher(employee);

        List<Callable<Employee>> callablesList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            callablesList.add(new IncomingCall(d, i));
        }
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        try {
            List<Future<Employee>> listEmployee = threadPool.invokeAll(callablesList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            threadPool.shutdown();
        }
    }

}
