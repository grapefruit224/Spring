package com.example.spring_0709.another;

import org.springframework.stereotype.Component;

@Component
//@Primary
public class getMessage implements Message {

    @Override
    public void print() {
        System.out.println("Hello, World!2");
    }
}
