package org.example.family;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class SchoolMeet {
    //@Autowired
    private Parent parent;

    public SchoolMeet(@Qualifier("mother") Parent parent) {
        this.parent = parent;
    }
}
