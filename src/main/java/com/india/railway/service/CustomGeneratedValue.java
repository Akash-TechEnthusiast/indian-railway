
package com.india.railway.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomGeneratedValue {
    String sequenceName(); // Name of the sequence

    int incrementSize() default 1; // Increment size (optional)
}