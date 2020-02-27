package spring.hello.world.services.impl;

import java.io.PrintStream;

import spring.hello.world.services.Greeter;
import spring.hello.world.services.HelloWorldPrinter;
import spring.hello.world.services.Namer;

public class HelloWorldPrinterService implements HelloWorldPrinter {

    private final Greeter greeter;
    private final Namer namer;
    private final PrintStream targetStream;
    
    public HelloWorldPrinterService(Greeter greeter, Namer namer, PrintStream target) {
        this.greeter=greeter;
        this.namer=namer;
        this.targetStream=target;
    }
    
    @Override
    public void print() {
        targetStream.println(greeter.getGreeterWord()+" "+namer.getName()+"!");
    }

}
