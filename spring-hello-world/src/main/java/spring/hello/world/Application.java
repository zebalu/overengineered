package spring.hello.world;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import spring.hello.world.services.HelloWorldPrinter;

@ComponentScan(basePackages = "spring.hello.world")
@PropertySource(value = { "classpath:/application.properties",
        "file:./application.properties" }, ignoreResourceNotFound = true)
public class Application {

    public static void main(String... args) {
        try (ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(Application.class)) {
            context.getBean(HelloWorldPrinter.class).print();;
        }
    }

}
