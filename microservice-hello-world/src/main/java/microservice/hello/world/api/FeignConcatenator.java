package microservice.hello.world.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="concatenator")
public interface FeignConcatenator {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    String concatenate(
            @RequestParam(name = "name", required = true) String name,
            @RequestParam(name="word", required = true) String greet
            );
    
}
