package com.example.demo;

import com.example.demo.controller.Taco;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(Taco.class)
//@SpringBootTest
@RunWith(SpringRunner.class)
class DemoApplicationTests {

    @Autowired
    private MockMvc mockMvc;

/*    @Test
    void contextLoads() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("Taco"))
                .andExpect(content().string(containsString("welcome to ....")));
    }*/

}
