package org.example.family;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Father implements Parent, ApplicationContextAware, InitializingBean {
    //@Autowired
    private Mother wife;
    private Child child;
    private ApplicationContext context;
    //@Autowired
    //@Lazy
//    public Father(Mother wife){
//        this.wife = wife;
//        System.out.println("Father constructor is created");
//    }

    public Father() {
        System.out.println("Father constructor is created");
    }

    @Override
    public void parentSay(int number) {
        System.out.println("Father say:");
    }

    public Mother getWife() {
        return wife;
    }

    public Child getChild() {
        return child;
    }
    @Override
    public int getChildNumber(Child child){
        return child.getChildNumber();
    }

    //@Autowired
    public void setWife(Mother wife) {
        this.wife = wife;
    }
//    @PostConstruct
//    public void init() {
//        wife.setHusband(this);
//        System.out.println("Init method is work");
//    }

    @Override
    public void afterPropertiesSet() throws Exception {
        wife = context.getBean(Mother.class);
        child = context.getBean(Child.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
