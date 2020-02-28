package lambda.hello.world;

import static org.junit.Assert.assertEquals;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.Test;

import lambda.hello.world.util.ExceptionHider;

public class ApplicationTest {

    @Test
    public void mainRunsAsExpected() throws Exception {
        PrintStream original = System.out;
        PrintStreamStub stub = new PrintStreamStub();
        System.setOut(stub);
        try {
            Application.main();
            assertEquals("Hello World!", stub.getLastMessage());
        } finally {
            System.setOut(original);
        }
    }

    @Test
    public void testGreetPrinter() throws Exception {
        List<String> to = new ArrayList<>();
        List<String> from = Arrays.asList("A", "b");
        Application.greetPrinter(to::add, from.iterator()::next);
        assertEquals(1, to.size());
        assertEquals("A", to.get(0));

    }

    @Test
    public void testBiFunctionConverter() throws Exception {
        Supplier<Integer> converted = Application.biFunctionConverter((Integer a, Integer b) -> a + b, () -> 2,
                () -> 3);
        assertEquals(Integer.valueOf(5), converted.get());
    }

    @Test
    public void testTriFunctionConverter() throws Exception {
        Supplier<Integer> converted = Application.triFunctionConverter((Integer a, Integer b, Integer c) -> (a + b) * c,
                1, 2, 3);
        assertEquals(Integer.valueOf(9), converted.get());
    }

    @Test
    public void testConcatanator() throws Exception {
        assertEquals("A B!", Application.concatanator("A", "B"));
    }

    @Test
    public void testFill() throws Exception {
        Properties p = Application.fill(new Properties(), pathOfResource("/a.properties"));
        assertEquals(2, p.keySet().size());
        assertEquals("APPLE", p.getProperty("apple"));
        assertEquals("test", p.getProperty("test"));
    }

    @Test
    public void propertyResolverChainFindsEnvVars() throws Exception {
        String resolvedPath = Application.propertyResolverChain("path",
                Application.springLikePropertyResolverList(new Properties()), "missed");
        assertEquals(System.getenv("PATH"), resolvedPath);
    }

    @Test
    public void propertyResolverChainFindsJVMProps() throws Exception {
        String resolvedUserName = Application.propertyResolverChain("user.name",
                Application.springLikePropertyResolverList(new Properties()), "missed");
        assertEquals(System.getProperty("user.name"), resolvedUserName);
    }

    @Test
    public void propertyResolverChainFindsProperties() throws Exception {
        Properties p = new Properties();
        p.put("testKey", "TestVal");
        String resolvedTestKey = Application.propertyResolverChain("testKey",
                Application.springLikePropertyResolverList(p), "missed");
        assertEquals("TestVal", resolvedTestKey);
    }

    @Test
    public void propertyResolverChainReturnsDefaultValue() throws Exception {
        String resolvedMissingKey = Application.propertyResolverChain("missingKey",
                Application.springLikePropertyResolverList(new Properties()), "missed");
        assertEquals("missed", resolvedMissingKey);
    }

    @Test
    public void testSpringLikePropertyResolverList() throws Exception {
        List<Function<String, String>> list = Application.springLikePropertyResolverList(new Properties());
        assertEquals(3, list.size());
    }

    private String pathOfResource(String resource) {
        try {
            return ApplicationTest.class.getResource(resource).getPath();
        } catch (Exception e) {
            throw ExceptionHider.hiddenThrow(e);
        }
    }

    private static class PrintStreamStub extends PrintStream {
        String lastMessage = null;

        public PrintStreamStub() {
            super(System.out);
        }

        @Override
        public void println(String x) {
            lastMessage = x;
            super.println();
        }

        String getLastMessage() {
            return lastMessage;
        }
    }
}
