package spring.hello.world.config;

import java.io.PrintStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.hello.world.services.Greeter;
import spring.hello.world.services.HelloWorldPrinter;
import spring.hello.world.services.Namer;
import spring.hello.world.services.impl.HelloGreeter;
import spring.hello.world.services.impl.HelloWorldPrinterService;
import spring.hello.world.services.impl.WorldNamer;

@Configuration
public class HelloWorldPrinterConfig {

    @Bean
    public Greeter greeter() {
        return new HelloGreeter();
    }
    
    @Bean
    public Namer namer() {
        return new WorldNamer();
    }
    
    @Bean
    public PrintStream target() {
        return System.out;
    }
    
    @Bean
    public HelloWorldPrinter helloWorldPrinter(@Autowired PrintStream target) {
        return new HelloWorldPrinterService(greeter(), namer(), target);
    }
    
}
