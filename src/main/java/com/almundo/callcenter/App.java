package com.almundo.callcenter;

import com.almundo.callcenter.controller.Request;
import org.apache.log4j.Logger;

/* Author Leandro Franco */
public class App {

    private static final Logger logger = Logger.getLogger(App.class);
    
    public static void main(String[] args) throws Exception {
        logger.info("Bienvenidos a Soporte ALMUNDO - Desarrollado por Leandro Franco");
        try {
            Request.play();
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }
    }    
    
}
