package lambda.hello.world;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Logger;

import lambda.hello.world.util.ExceptionHider;

public class Application {

    public static void main(String... args) {
        multyApplier(() -> fill(new Properties(), "./application.properties"),
                props -> greetPrinter(System.out::println,
                        biFunctionConverter(Application::concatenator,
                                triFunctionConverter(Application::propertyResolverChain, "greeter.word",
                                        springLikePropertyResolverList(props), "Hello"),
                                triFunctionConverter(Application::propertyResolverChain, "greeter.name",
                                        springLikePropertyResolverList(props), "World"))));

    }

    static void greetPrinter(Consumer<String> stringConsumer, Supplier<String> stringSupplier) {
        stringConsumer.accept(stringSupplier.get());
    }

    static <T, A, B> Supplier<T> biFunctionConverter(BiFunction<A, B, T> function, Supplier<A> a, Supplier<B> b) {
        return () -> function.apply(a.get(), b.get());
    }

    static <T, A, B, C> Supplier<T> triFunctionConverter(TriFunction<A, B, C, T> function, A a, B b, C c) {
        return () -> function.apply(a, b, c);
    }

    static String concatenator(String greet, String greeted) {
        return greet + " " + greeted + "!";
    }

    static Properties fill(Properties props, String fileName) {
        return tryCatchConsume(() -> {
            try (FileReader fr = new FileReader(fileName)) {
                props.load(fr);
                return props;
            }
        }, (FileNotFoundException fnfe) -> Logger.getLogger(Application.class.toString())
                .info(()->fileName + " is missing")).orElse(props);
    }

    static String propertyResolverChain(String propertyKey, List<Function<String, String>> list, String defaultValue) {
        return list.stream().map(f -> f.apply(propertyKey)).filter(Objects::nonNull).findFirst().orElse(defaultValue);
    }

    static List<Function<String, String>> springLikePropertyResolverList(Properties props) {
        return Arrays.asList(System::getProperty,
                (String key) -> System.getenv(key.toUpperCase(Locale.ROOT).replaceAll("[\\.-]", "_")),
                props::getProperty);
    }

    @SuppressWarnings("unchecked")
    static <A, B extends Throwable> Optional<A> tryCatchConsume(ThrowingSupplier<A> supplier, Consumer<B> consumer) {
        try {
            return Optional.of(supplier.get());
        } catch (Throwable b) {
            try {
                consumer.accept((B) b);
            } catch (Exception e) {
                throw ExceptionHider.hiddenThrow(b);
            }
            return Optional.empty();
        }
    }
    
    @SafeVarargs
    static <A> void multyApplier(Supplier<A> b, Consumer<A>... cs) {
        Arrays.stream(cs).forEach(c -> c.accept(b.get()));
    }

    @FunctionalInterface
    static interface TriFunction<P1, P2, P3, R> {
        R apply(P1 p1, P2 p2, P3 p3);
    }
    
    @FunctionalInterface
    static interface ThrowingSupplier<T> {
        
        T get() throws Throwable;
    }

}
