package org.example.family;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Mother implements Parent {
    //@Autowired
    private Father husband;
    private Child child;


    @Autowired
    public Mother(Father husband, Child child){
        this.husband = husband;
        this.child = child;
        System.out.println("Mother constructor is created");
    }

    public Mother() {
        System.out.println("Mother constructor is created");
    }
    @Lookup
    public Child createChild() {
        return null;
    };
    @Override
    public void parentSay(int number) {
        Child child = createChild();
        child.setChildNumber(number);
        System.out.println("Mother say: " + child.getChildNumber() + " good child");
    }

    public Father getHusband() {
        return husband;
    }

    public void setHusband(Father husband) {
        this.husband = husband;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }
    @Override
    public int getChildNumber(Child child){
        return child.getChildNumber();
    }
}
