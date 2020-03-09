package microservice.hello.world.greeter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class GreeterController {

    @Value("${greeter.word:Hello}")
    private String word;
    
    @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public String getWord() {
        return word;
    }
    
    public void setWord(String word) {
        this.word=word;
    }
    
}
