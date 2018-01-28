package com.almundo.callcenter;

import com.almundo.callcenter.model.Director;
import com.almundo.callcenter.model.Employee;
import com.almundo.callcenter.model.Operator;
import com.almundo.callcenter.model.Supervisor;
import com.almundo.callcenter.controller.Dispatcher;
import com.almundo.callcenter.controller.IncomingCall;
import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/* Author Leandro Franco */
public class TestUnit {

    PriorityBlockingQueue<Employee> employee;
    ExecutorService incomingCallExecutor;
    Dispatcher dispatcher;

    @Test
    public void testConcurrentCalls() throws InterruptedException, ExecutionException {
        setup(7, 2, 1, 10);

        List<Callable<Employee>> callableList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            callableList.add(new IncomingCall(dispatcher, i));
        }

        List<Future<Employee>> futureList = incomingCallExecutor.invokeAll(callableList);

        int counCalls = 0;
        for (Future<Employee> future : futureList) {
            assert future.get() != null;
            counCalls++;
        }

        assert counCalls == 10;
    }

    @Test
    /**
     * 7 Operadores - 2 Supervisores - 1 Director
     */
    public void testPriorityCallAntedee() throws InterruptedException, ExecutionException {
        setup(7, 2, 1, 10);

        List<Callable<Employee>> callableList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            callableList.add(new IncomingCall(dispatcher, i));
        }

        List<Future<Employee>> futureList = incomingCallExecutor.invokeAll(callableList);

        for (Future<Employee> future : futureList) {
            assert future.get() instanceof Operator;
        }
    }

    @Test
    /**
     * 7 Operadores - 2 Supervisores - 1 Director
     */
    public void testPriorityCallWithSupervisor() throws InterruptedException, ExecutionException {
        setup(7, 2, 1, 10);

        List<Callable<Employee>> callableList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            callableList.add(new IncomingCall(dispatcher, i));
        }

        List<Future<Employee>> futureList = incomingCallExecutor.invokeAll(callableList);

        for (Future<Employee> future : futureList) {
            Employee e = future.get();
            assert e != null;
            assert e instanceof Operator || e instanceof Supervisor;
        }

    }

    private void setup(Integer operators, Integer supervisors, Integer directors, Integer callPoolSize) {
        employee = new PriorityBlockingQueue<>();
        for (int i = 0; i < operators; i++) {
            employee.add(new Operator("Operator N° " + i));
        }

        for (int i = 0; i < supervisors; i++) {
            employee.add(new Supervisor("Supervisor N° " + i));
        }

        for (int i = 0; i < directors; i++) {
            employee.add(new Director("Director N° " + i));
        }

        incomingCallExecutor = Executors.newFixedThreadPool(callPoolSize);
        dispatcher = new Dispatcher(employee);
    }

    @After
    public void tearDown() {
        employee = null;
        dispatcher = null;
        incomingCallExecutor.shutdown();
        if (incomingCallExecutor.isShutdown()) {
            incomingCallExecutor = null;
        }
    }
}
