package microservice.hello.world.namer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class NameController {

    @Value("${greeter.name:World}")
    private String name;
    
    @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name=name;
    }
    
}
