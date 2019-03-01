package com.ann.custom;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class ParseAnnotation {
    public static void main(String[] args) {
        try {
            //只能取到运行时注解（RetentionPolicy.RUNTIME）
            //1. 使用类加载器加载类
            Class c = Class.forName("com.ann.custom.Main");
            //2. 找到类上面的注解
            boolean isExist = c.isAnnotationPresent(Description.class);
            if (isExist) {
                //3. 拿到注解实例
                Description d = (Description) c.getAnnotation(Description.class);
                System.out.println(d.desc());
            }
            //4. 找到方法上的注解
            Method[] ms = c.getMethods();
            for (Method m : ms) {
                boolean isMExist = m.isAnnotationPresent(Description.class);
                if (isMExist) {
                    Description d = m.getAnnotation(Description.class);
                    System.out.println(d.desc());
                }
            }
            //另一种解析方法
            for (Method m : ms) {
                Annotation[] as = m.getAnnotations();
                for (Annotation a : as) {
                    if (a instanceof Description) {
                        Description d = (Description) a;
                        System.out.println(d.desc());
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
