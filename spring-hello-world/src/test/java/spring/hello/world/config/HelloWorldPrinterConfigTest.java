package spring.hello.world.config;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import spring.hello.world.services.HelloWorldPrinter;
import spring.hello.world.util.DummyPrintStream;

public class HelloWorldPrinterConfigTest {

    @Test
    public void printStreamIsUsed() {
        try (DummyPrintStream dps = new DummyPrintStream(System.out)) {
            HelloWorldPrinterConfig hwpc = new HelloWorldPrinterConfig();
            HelloWorldPrinter printService = hwpc.helloWorldPrinter(dps);
            printService.print();
            assertTrue(dps.wasUsed());
        }
    }

}
