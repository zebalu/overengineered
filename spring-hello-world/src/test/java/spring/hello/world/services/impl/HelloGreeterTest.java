package spring.hello.world.services.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import spring.hello.world.Application;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
@TestPropertySource(properties = {"greeter.word=Csakany"})
public class HelloGreeterTest {
    
    @Autowired
    private HelloGreeter greeter;
    
    @Test
    public void wordIsDeterminedByProperty() {
        assertEquals("Csakany", greeter.getGreeterWord());
    }
    

}
