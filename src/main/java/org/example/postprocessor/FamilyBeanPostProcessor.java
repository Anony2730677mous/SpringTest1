package org.example.postprocessor;

import org.example.family.InjectRandomInt;
import org.example.family.Profiling;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Random;
@Component
public class FamilyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] declaredFields = bean.getClass().getDeclaredFields();
        for(Field field: declaredFields){
            InjectRandomInt annotation = field.getAnnotation(InjectRandomInt.class);
            if (annotation != null){
             int min = annotation.min();
             int max = annotation.max();
             Random random = new Random();
             int temp = min + random.nextInt(max - min);
             field.setAccessible(true);
                ReflectionUtils.setField(field, bean, temp);
            }
        }
        System.out.println("Method postProcessBeforeInitialization is working");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Field[] declaredFields = bean.getClass().getDeclaredFields();
        for(Field field: declaredFields){
            Profiling annotation = field.getAnnotation(Profiling.class);
            if(annotation != null){
                System.out.println("Method postProcessAfterInitialization is working");
            }
        }
        return bean;
    }
}
