package microservice.hello.world.namer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import microservice.hello.world.common.Startable;

@SpringBootApplication
public class NamerApp implements Startable {


    @Override
    public ConfigurableApplicationContext start() {
        return SpringApplication.run(NamerApp.class, 
                "--server.port=8083",
                "--spring.application.name=namer");
    }
    
}
