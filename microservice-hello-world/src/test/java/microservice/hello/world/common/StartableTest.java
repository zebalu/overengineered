package microservice.hello.world.common;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.context.ConfigurableApplicationContext;

import microservice.hello.world.api.ApiApp;
import microservice.hello.world.concatenator.ConcatenatorApp;
import microservice.hello.world.greeter.GreeterApp;
import microservice.hello.world.namer.NamerApp;

@Ignore("slow")
@RunWith(Parameterized.class)
public class StartableTest {

     @Parameters(name = "{index} - {0}")
     public static Collection<Object[]> parameters() {
         Object[][] params = {{"Namer", new NamerApp()}, 
                 {"Greeter", new GreeterApp()},
                 {"Concatenator", new ConcatenatorApp()},
                 {"Api", new ApiApp()}};
         return Arrays.asList(params);
     }
     
     @Parameter(value = 0)
     public String name;
     @Parameter(value = 1)
     public Startable startable;
     
     @Test
     public void contextIsLoadedOnStart() {
         try(ConfigurableApplicationContext conext = startable.start()) {
             assertNotNull(name+" should return a non null conext", conext);
         }
     }
}
