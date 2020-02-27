package spring.hello.world.services.impl;

import org.springframework.beans.factory.annotation.Value;

import spring.hello.world.services.Namer;

public class WorldNamer implements Namer {

    @Value("${greeter.name:World}")
    private String name;
    
    @Override
    public String getName() {
        return name;
    }

}
