package bartosz.szablewski.account.contoller;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnCreateResponseWhenSendPostRequest() throws Exception {
        //given
        String account = "{\"firstName\": \"bob\", \"lastName\" : \"test\", \"accountBalance\": \"100.00\"}";

        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/accounts")
                        .content(account)
                        .contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void shouldReturnValidationMessageWhenSendPostRequest() throws Exception {
        //given
        String account = "{\"firstName\": null, \"lastName\" : \"test\", \"accountBalance\": \"100.00\"}";

        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/accounts")
                        .content(account)
                        .contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Is.is("firstName cannot be null")));
    }
}
