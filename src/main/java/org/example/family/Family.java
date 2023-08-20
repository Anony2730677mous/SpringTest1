package org.example.family;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Family {
    @InjectRandomInt(min = 2, max = 7)
    private int childNumber;
    @Profiling
    private String message = "Hello";

    public Family() {
        System.out.println("Constructor Family is created");
    }

    public void sayRepeat(){
        for (int i = 0; i < childNumber; i++) {
            System.out.println("Child say " + message);
        }
    }
    @PostConstruct
    public void init() {
        System.out.println("Init method in FamilyBean is working +  childNumber: " + childNumber);
    }

}
