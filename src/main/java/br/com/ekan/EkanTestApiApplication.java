package br.com.ekan;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@SpringBootApplication
public class EkanTestApiApplication implements ApplicationContextAware {

    private static ApplicationContext APPLICATION_CONTEXT;

    public static void main(String[] args) {
        SpringApplication.run(EkanTestApiApplication.class, args);
    }

    public static <T> T getBean(Class<T> type) {
        return APPLICATION_CONTEXT.getBean(type);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        APPLICATION_CONTEXT = applicationContext;
    }
}
