package microservice.hello.world.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

import microservice.hello.world.common.Startable;

@SpringBootApplication
@EnableFeignClients
public class ApiApp implements Startable {

    @Override
    public ConfigurableApplicationContext start() {
        return SpringApplication.run(ApiApp.class, 
                "--server.port=8085",
                "--spring.application.name=api");
    }
    
}
