package org.example.family;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Scope("prototype")
public class Child {
    private int childNumber = new Random().nextInt(100);
    String message = "Hello Parent";


    public Child() {
        System.out.println("Child constructor is created");
        System.out.println(childNumber);
    }

    public int getChildNumber() {
        return childNumber;
    }

    public void setChildNumber(int childNumber) {
        this.childNumber = childNumber;
    }

}
