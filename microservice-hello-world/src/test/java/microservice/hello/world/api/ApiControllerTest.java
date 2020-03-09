package microservice.hello.world.api;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class ApiControllerTest {

    private FeignNamer namerMock = mock(FeignNamer.class);
    private FeignGreeter greeterMock = mock(FeignGreeter.class);
    private FeignConcatenator concatenatorMock = mock(FeignConcatenator.class);

    private ApiController apiController = new ApiController(greeterMock, namerMock, concatenatorMock);

    private MockMvc mvc = MockMvcBuilders.standaloneSetup(apiController).build();

    @Test
    public void apiConcatenates() throws Exception {
        when(namerMock.getName()).thenReturn("mock name");
        when(greeterMock.getGreet()).thenReturn("mock greet");
        when(concatenatorMock.concatenate("mock name", "mock greet")).thenReturn("mock message");
        mvc.perform(MockMvcRequestBuilders.get("/")).andDo(MockMvcResultHandlers.print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string(Matchers.is("mock message")))
                .andExpect(status().is2xxSuccessful());
        verify(namerMock).getName();
        verify(greeterMock).getGreet();
        verify(concatenatorMock).concatenate("mock name", "mock greet");
    }

}
