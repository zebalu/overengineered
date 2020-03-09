package microservice.hello.world.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ApiController {
    
    private final FeignGreeter greeter;
    private final FeignNamer namer;
    private final FeignConcatenator concatenator;
    
    public ApiController(FeignGreeter greeter, FeignNamer namer, FeignConcatenator concatenator) {
        this.greeter=greeter;
        this.namer=namer;
        this.concatenator=concatenator;
    }

    @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public String getGreetings() {
        return concatenator.concatenate(namer.getName(), greeter.getGreet());
    }
    
}
