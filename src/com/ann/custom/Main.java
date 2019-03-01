package com.ann.custom;

@Description(desc = "I am a class annotation", author = "Bob")
public class Main {

    @Description(desc = "I am a method annotation", author = "Tom", age = 18)
    public String eyeColor() {
        return "red";
    }

    public static void main(String[] args) {

    }
}
