package microservice.hello.world.common.starter;

import java.net.URL;
import java.net.URLClassLoader;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;

import microservice.hello.world.common.Startable;

public class AllInOneStarter {

    private static final Logger LOG = LoggerFactory.getLogger(AllInOneStarter.class);

    private static final List<AutoCloseable> CLOSEABLES = Collections.synchronizedList(new ArrayList<>());
    private static final List<ConfigurableApplicationContext> CONTEXTS = Collections
            .synchronizedList(new ArrayList<>());
    private static final ThreadGroup STARTER_GROUP = new ThreadGroup("Starters");

    public static void main(String[] args) {
        Instant start = Instant.now();
        registerShutdownHook();
        start("microservice.hello.world.api.ApiApp");
        start("microservice.hello.world.greeter.GreeterApp");
        start("microservice.hello.world.namer.NamerApp");
        start("microservice.hello.world.concatenator.ConcatenatorApp");
        Instant end = Instant.now();
        Duration startUpDuration = Duration.between(start, end);
        LOG.info("\n\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\n");
        LOG.info("All services has started");
        LOG.info("Duration: {} ms", startUpDuration.toMillis());
        LOG.info("Total startup time: {} minutes {} seconds {} milliseconds", startUpDuration.toMinutes(),
                startUpDuration.minus(startUpDuration.truncatedTo(ChronoUnit.MINUTES)).toSeconds(),
                startUpDuration.minus(startUpDuration.truncatedTo(ChronoUnit.SECONDS)).toMillis());
    }

    private static void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            CLOSEABLES.forEach(closeable -> {
                try {
                    closeable.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }));
    }

    @SuppressWarnings({ "deprecation", "unchecked" })
    private static void start(String className) {
        Thread t = new Thread(STARTER_GROUP, () -> {
            try {
                ClassLoader cl = new URLClassLoader(className + " loader", new URL[] {},
                        Thread.currentThread().getContextClassLoader());
                CLOSEABLES.add(() -> ((URLClassLoader) cl).close());
                Class<Startable> startableClass = (Class<Startable>) cl.loadClass(className);
                ConfigurableApplicationContext context = startableClass.newInstance().start();
                CLOSEABLES.add(() -> context.close());
                CONTEXTS.add(context);
            } catch (Exception e) {
                throw new IllegalArgumentException("Could not start: " + className, e);
            }
        }, className + " init");
        t.run();
        // t.start();
    }

}
