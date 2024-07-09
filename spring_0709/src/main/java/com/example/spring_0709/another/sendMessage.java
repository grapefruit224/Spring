package com.example.spring_0709.another;

import org.springframework.stereotype.Component;

@Component
public class sendMessage implements Message {

    @Override
    public void print() {
        System.out.println("Hello, World!");
    }
}