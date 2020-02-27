package spring.hello.world;

import static org.junit.Assert.assertEquals;

import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import spring.hello.world.util.DummyPrintStream;

public class ApplicationTest {
    
    private PrintStream originalPrintStream;
    private DummyPrintStream testStream;
    
    @Before
    public void setSystemOut() {
        originalPrintStream=System.out;
        testStream=new DummyPrintStream(System.out);
        System.setOut(testStream);
    }
    
    @After
    public void resetSystemOut() {
        System.setOut(originalPrintStream);
    }

    @Test
    public void applicationCanStart() {
        Application.main();
        assertEquals("Szia Vilag!", testStream.getLastLog());
    }

}
