package spring.hello.world.services.impl;

import org.springframework.beans.factory.annotation.Value;

import spring.hello.world.services.Greeter;

public class HelloGreeter implements Greeter {

    @Value("${greeter.word:Hello}")
    private String hello;

    @Override
    public String getGreeterWord() {
        return hello;
    }

}
