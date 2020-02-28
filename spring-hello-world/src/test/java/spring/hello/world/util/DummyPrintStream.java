package spring.hello.world.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class DummyPrintStream extends PrintStream {

    private List<String> printedMessages = new ArrayList<>();

    public DummyPrintStream(OutputStream os) {
        super(os);
    }

    @Override
    public void println(String x) {
        printedMessages.add(x);
        super.println(x);
    }

    public String getLastLog() {
        return printedMessages.get(printedMessages.size() - 1);
    }

    public boolean wasUsed() {
        return printedMessages.size() > 0;
    }

    public static class DummyPrintStreamTest {
        @Test
        public void dummyPrintStreamReportsUsageIfUsed() {
            try (DummyPrintStream dps = new DummyPrintStream(System.err)) {
                dps.println("test");
                assertTrue(dps.wasUsed());
            }
        }

        @Test
        public void dummyPrintStreamDoesNotReportsUsageIfNotUsed() {
            try (DummyPrintStream dps = new DummyPrintStream(System.err)) {
                assertFalse(dps.wasUsed());
            }
        }

        @Test
        public void lastMessageCanBeRequested() {
            try (DummyPrintStream dps = new DummyPrintStream(System.err)) {
                dps.println("1");
                dps.println("2");
                assertEquals("2", dps.getLastLog());
            }
        }

        @Test(expected = ArrayIndexOutOfBoundsException.class)
        public void lastMessageCanNotBegetFromUnused() {
            try (DummyPrintStream dps = new DummyPrintStream(System.err)) {
                dps.getLastLog();
            }
        }

    }

}