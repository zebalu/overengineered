package microservice.hello.world.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "greeter")
public interface FeignGreeter {
    
    @RequestMapping(method = RequestMethod.GET, value = "/")
    String getGreet();

}
