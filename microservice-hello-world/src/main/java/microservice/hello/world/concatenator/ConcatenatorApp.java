package microservice.hello.world.concatenator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import microservice.hello.world.common.Startable;

@SpringBootApplication
public class ConcatenatorApp implements Startable {

    @Override
    public ConfigurableApplicationContext start() {
        return SpringApplication.run(ConcatenatorApp.class, 
                "--server.port=8084",
                "--spring.application.name=concatenator");
    }
    
}
