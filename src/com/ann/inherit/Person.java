package com.ann.inherit;

import com.ann.custom.Description;

//子类可以继承父类上的类注解，接口上的注解不会被继承，
@Description(desc = "I am a interface", author = "Bob")
public interface Person {

    //方法的注解也不会被继承
    @Description(desc = "I am a interface method", author = "Tom")
    public String name();

    public int age();

    @Deprecated
    public void sing();
}
