package microservice.hello.world.namer;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class NameControllerTest {

    private NameController controller = new NameController();
    private MockMvc mvc = MockMvcBuilders.standaloneSetup(controller).build();

    @Test
    public void wordIsReturned() throws Exception {
        controller.setName("testWord");
        mvc.perform(MockMvcRequestBuilders.get("/"))
        .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.is("testWord")))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

}
