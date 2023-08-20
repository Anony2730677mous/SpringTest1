package org.example.family;

import org.springframework.stereotype.Component;

@Component
public interface Parent {
    public void parentSay(int number);
    public int getChildNumber(Child child);
}
