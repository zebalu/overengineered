package microservice.hello.world.greeter;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class GreeterControllerTest {
    
    private GreeterController controller = new GreeterController();
    private MockMvc mvc = MockMvcBuilders.standaloneSetup(controller).build();

    @Test
    public void testGetWord() throws Exception {
        controller.setWord("testWord");
        mvc.perform(MockMvcRequestBuilders.get("/"))
        .andDo(MockMvcResultHandlers.print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string(Matchers.is("testWord")))
                .andExpect(status().is2xxSuccessful());
    }

}
