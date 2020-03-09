package microservice.hello.world.common;

import org.springframework.context.ConfigurableApplicationContext;

public interface Startable {

    ConfigurableApplicationContext start();
    
}
