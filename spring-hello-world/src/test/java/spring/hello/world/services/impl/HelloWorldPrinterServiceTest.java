package spring.hello.world.services.impl;

import static org.junit.Assert.assertEquals;

import java.io.PrintStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import spring.hello.world.Application;
import spring.hello.world.util.DummyPrintStream;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { Application.class, HelloWorldPrinterServiceTest.TestConfiguration.class })
@TestPropertySource(properties = {"greeter.name=Mundo", "greeter.word=Hola"})
public class HelloWorldPrinterServiceTest {
    
    private static DummyPrintStream dummyStream = new DummyPrintStream(System.out);
    
    @Autowired
    private HelloWorldPrinterService helloWorldPrinter;
    
    @Test
    public void printIsRight() {
        helloWorldPrinter.print();
        assertEquals(dummyStream.getLastLog(), "Hola Mundo!");
    }

    
    static class TestConfiguration {
        
        @Bean
        @Primary
        public PrintStream target() {
            return dummyStream;
        }
        
    }
}
