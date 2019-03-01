package com.ann.inherit;

public class Test {
    public void sing() {
        Person p = new Child();
        p.sing();//@Deprecated 注解表示该方法已过时
    }

    @SuppressWarnings("deprecation")//忽略警告
    public void sing2() {
        Person p = new Child();
        p.sing();
    }
}
