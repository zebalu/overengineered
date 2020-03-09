package microservice.hello.world.common.starter;

import java.util.Arrays;
import java.util.StringJoiner;

import microservice.hello.world.common.Startable;

public class OnlyOneStarter {

    private static final String[] VALID_ARGS = { 
            "microservice.hello.world.api.ApiApp",
            "microservice.hello.world.greeter.GreeterApp", 
            "microservice.hello.world.namer.NamerApp",
            "microservice.hello.world.concatenator.ConcatenatorApp" 
            };
    
    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        if(args.length!=1) {
            throw new IllegalArgumentException("You moust provide exactly one value from one of these: "+concatValidArgs());
        }
        Startable application = (Startable)(Class.forName(args[0]).newInstance());
        application.start();
    }

    private static String concatValidArgs() {
        StringJoiner sj = new StringJoiner("', '", "['", "']");
        sj.setEmptyValue("[]");
        Arrays.stream(VALID_ARGS).forEach(sj::add);
        return sj.toString();
    }
}
