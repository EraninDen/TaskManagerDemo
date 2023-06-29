package com.example.taskmanagerdemo.controller;

import com.example.taskmanagerdemo.model.User;
import com.example.taskmanagerdemo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
        when(userService.getAllUsers()).thenReturn(List.of(u1, u2));
        mockMvc.perform(
                        get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(u1, u2)))
                );
    }

    @Test
    void getUserById_whenGetExistingPerson_thenStatus200andUserReturned() throws Exception {
        User u3 = new User(3L, "Vasya");
        when(userService.getUserById(Mockito.any())).thenReturn(u3);
        mockMvc.perform(
                        get("/users/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("3"))
                .andExpect(jsonPath("$.name").value("Vasya"));
    }

    @Test
    void createUser_whenAdd_thenStatus201andUserReturned() throws Exception {
        User u4 = new User(4L, "Petya");
        when(userService.createUser(Mockito.any())).thenReturn(u4);
        mockMvc.perform(
                post("/users")
                        .content(objectMapper.writeValueAsString(u4))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(u4)));
    }

    @Test
    public void updateUser_whenUpdate_thenStatus200andUpdateReturns() throws Exception {
        // Создание тестовых данных
        Long id = 1L;
        User user = new User();
        user.setName("John");
        user.setEmail("john@example.com");

        User updatedUser = new User();
        updatedUser.setId(id);
        updatedUser.setName(user.getName());
        updatedUser.setEmail(user.getEmail());

        // Настройка поведения mock объекта userService
        when(userService.getUserById(id)).thenReturn(updatedUser);
        when(userService.updateUser(id, updatedUser)).thenReturn(updatedUser);

        // Выполнение запроса к контроллеру
        mockMvc.perform(put("/users/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value((updatedUser.getId().intValue())))
                .andExpect(jsonPath("$.name").value(updatedUser.getName()))
                .andExpect(jsonPath("$.email").value(updatedUser.getEmail()));
    }

    @Test
    void deleteUser_whenDeleteUser_thenStatus200() throws Exception {
        User u6 = new User(6L, "Pavluntiy");
        when(userService.getUserById(Mockito.any())).thenReturn(u6);
        mockMvc.perform(
                delete("/users/6"))
                .andExpect(status().isOk());
    }
}
