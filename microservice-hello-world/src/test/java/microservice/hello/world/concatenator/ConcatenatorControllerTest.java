package microservice.hello.world.concatenator;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class ConcatenatorControllerTest {

    private MockMvc mvc = MockMvcBuilders.standaloneSetup(new ConcatenatorController()).build();

    @Test
    public void testGetWord() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").param("name", "test_name").param("word", "test_word"))
        .andDo(MockMvcResultHandlers.print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string(Matchers.is("test_word test_name!")))
                .andExpect(status().is2xxSuccessful());
    }
    
}
