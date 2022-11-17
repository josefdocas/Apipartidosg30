package com.unab.apipartidosg30.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class AppContexto implements ApplicationContextAware{

    private static ApplicationContext contexto;

    @Override
    public void setApplicationContext(org.springframework.context.ApplicationContext applicationContext)
            throws BeansException {
                
                contexto= applicationContext;
        
    }

    public static Object getBean(String nombre){
        return contexto.getBean(nombre);
    }

}
