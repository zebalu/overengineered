package microservice.hello.world.concatenator;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ConcatenatorController {

    @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    String getConcatenated(
            @RequestParam(name = "name", required = true) String name,
            @RequestParam(name = "word", required = true) String word
            ) {
        return String.format("%s %s!", word, name);
    }
    
}
