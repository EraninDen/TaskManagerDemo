package com.example.taskmanagerdemo.controller;

import com.example.taskmanagerdemo.model.User;
import com.example.taskmanagerdemo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerMockMvcTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserService userService;

    @Test
    void getAllUsers_whenGetPersons_thenStatus200() throws Exception {
        User u1 = new User(1L, "John");
        User u2 = new User(2L, "Jack");
        Mockito.when(userService.getAllUsers()).thenReturn((List<User>) List.of(u1, u2));
        mockMvc.perform(
                        get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(u1, u2)))
                );
    }

    @Test
    void getUserById() {
    }

    @Test
    void createUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}

/*
* @RunWith(SpringRunner.class)
@MockMvcTest(MyController.class)
public class MyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testGetAllUsers() throws Exception {
        List<User> userList = new ArrayList<>();
        userList.add(new User("John", "Doe"));
        userList.add(new User("Jane", "Doe"));

        Mockito.when(userService.getAllUsers()).thenReturn(userList);

        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName", Matchers.equalTo("John")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].lastName", Matchers.equalTo("Doe")));
    }
}
* */