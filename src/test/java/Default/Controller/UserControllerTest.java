package Default.Controller;

import Default.DTO.RegistrationDTO;
import Default.Model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)

@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void registerNewUser() throws Exception {

       User user = new User();

       user.setFirstName("Ozumba");
       user.setLastName("Mbadiwe");
       user.setEmail("ozmba@gmail.com");
       user.setPassword("1234");

        ObjectMapper objectMapper = new ObjectMapper();

       mockMvc.perform(
               post("/register")
                       .content(objectMapper.writeValueAsString(user)).contentType(MediaType.APPLICATION_JSON)
       ).andExpect(status().isOk())
               .andExpect(model().attribute("person", new RegistrationDTO()))
               .andExpect(view().name("/"));
    }
}