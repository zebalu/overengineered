package lambda.hello.world.util;

import java.io.IOException;

import org.junit.Test;

public class ExceptionHiderTest {

    @Test(expected = IOException.class)
    public void originalExceptionIsThrown() {
        ExceptionHider.hiddenThrow(new IOException());
    }
    
}
