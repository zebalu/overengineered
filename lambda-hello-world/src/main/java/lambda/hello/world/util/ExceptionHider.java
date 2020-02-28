package lambda.hello.world.util;

public class ExceptionHider {

    private ExceptionHider() {
    }

    public static RuntimeException hiddenThrow(Throwable throwable) {
        return ExceptionHider.<RuntimeException>hiddenThrowImpl(throwable);
    }

    @SuppressWarnings("unchecked")
    private static <E extends Throwable> E hiddenThrowImpl(Throwable throwable) throws E {
        throw (E) throwable;
    }

}
