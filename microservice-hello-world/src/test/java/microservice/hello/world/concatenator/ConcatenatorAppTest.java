package microservice.hello.world.concatenator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConcatenatorApp.class)
public class ConcatenatorAppTest {

    @Test
    public void startup() {
        
    }
    
}
