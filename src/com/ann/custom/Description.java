package com.ann.custom;

import java.lang.annotation.*;

//作用域，CONSTRUCTOR-构造方法；FIELD-字段；LOCAL_VARIABLE-局部变量；METHOD-方法；PACKAGE-包；PARAMETER-参数；TYPE-类、接口
@Target({ElementType.METHOD, ElementType.TYPE})
//生命周期，SOURCE-只在源码显示，编译时丢弃；CLASS-编译时记录到class中，运行时忽略；RUNTIME-运行时存在，可通过反射读取
@Retention(RetentionPolicy.RUNTIME)
@Inherited//允许子类继承
@Documented//生成javadoc时会包含注解
//上面4个称为元注解
public @interface Description {//@interface定义注解

    //成员类型是受限的，合法类型包括原始类型以及String、Class、Annotation和Enumeration
    //如果注解只有一个成员，则成员名必须叫value()，在使用该注解时可以忽略成员名和赋值号（=）
    //注解类可以没有成员，没有成员的注解称为标识注解
    String desc();//成员以无参无异常方式声明

    String author();

    int age() default 18;//可以用default指定默认值
}
