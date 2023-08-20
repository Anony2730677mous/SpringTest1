package org.example.family;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME) // обязательно присутствие RetentionPolicy.RUNTIME
public @interface InjectRandomInt {
    int min();
    int max();
}
