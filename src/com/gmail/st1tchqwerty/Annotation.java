package com.gmail.st1tchqwerty;
import java.lang.annotation.*;
import java.lang.reflect.*;

@Target(value= ElementType.METHOD)
@Retention(value= RetentionPolicy.RUNTIME)
@interface Test {
    int a();
    int b();
}

class Operation {
    @Test(a = 7, b = 5)
    public static void test(int a, int b) {
        System.out.println("a%b = " + (a%b));
    }
}

public class Annotation {
    public static void main(String[] args) {
        final Class<?> cls = Operation.class;
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                Test myOwn = method.getAnnotation(Test.class);
                int two = 0;
                try {
                    two = (Integer) method.invoke(null, myOwn.a(), myOwn.a());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                System.out.println(two);
            }
        }
    }
}