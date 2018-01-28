package com.almundo.callcenter.controller;

import com.almundo.callcenter.model.Employee;
import org.apache.log4j.Logger;
import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

/* Author Leandro Franco */
public class Dispatcher {
    
    private PriorityBlockingQueue<Employee> employeePriority;
    
    private static final Logger logger = Logger.getLogger(Dispatcher.class);
    private static final Integer MAX_CALL_DURATION = 10000;
    private static final Integer MIN_CALL_DURATION = 5000;   

    /**
     * Constructor
     * @param employee
     */
    public Dispatcher(PriorityBlockingQueue<Employee> employee) {
        this.employeePriority = employee;
    }

    /**
     * Obtenga un empleado disponible y realice la llamada, luego vuelve a la cola de asistentes.
     * Si no hay empleados disponible, entonces el método take() bloquea todas las nuevas llamadas entrantes hasta que un atendee esté disponible nuevamente
     * @param call
     * @return
     */
    public Employee dispatchCall(Integer call) {
        Employee employee = null;
        try {
            employee = employeePriority.take();
            logger.info("Llamada entrante N° " + call + " asignada a " + employee);
            doCall(call, employee);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
        return employee;
    }

    /**
     * Simula la ejecución de la llamada y cuando finaliza la llamada, el asistente vuelve a la cola.
     * @param call
     * @param employeePriority
     */
    private void doCall(Integer call, Employee employee) {
        try {
            Integer duration = new Random().nextInt((MAX_CALL_DURATION - MIN_CALL_DURATION) + 1) + MIN_CALL_DURATION;
            Thread.sleep(duration);
            logger.info("Llamada finalizada N° " + call + " atendida por " + employee + " y con una duración de " + duration);
            logger.info("Muchas Gracias por comunicarse con ALMUNDO");
            employeePriority.add(employee);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
