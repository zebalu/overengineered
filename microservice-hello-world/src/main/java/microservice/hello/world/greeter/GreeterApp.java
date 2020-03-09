package microservice.hello.world.greeter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import microservice.hello.world.common.Startable;

@SpringBootApplication
public class GreeterApp implements Startable {
    @Override
    public ConfigurableApplicationContext start() {
        return SpringApplication.run(GreeterApp.class, 
                "--server.port=8082",
                "--spring.application.name=greeter");
    }
}
