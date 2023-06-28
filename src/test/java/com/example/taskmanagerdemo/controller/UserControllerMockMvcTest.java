package com.example.taskmanagerdemo.controller;

import com.example.taskmanagerdemo.model.User;
import com.example.taskmanagerdemo.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    void getUserById_whenGetExistingPerson_thenStatus200andUserReturned() throws Exception {
        User u3 = new User(3l, "Vasya");
        Mockito.when(userService.getUserById(Mockito.any())).thenReturn(u3);
        mockMvc.perform(
                        get("/users/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("3"))
                .andExpect(jsonPath("$.name").value("Vasya")
                );

    }

    @Test
    void createUser_whenAdd_thenStatus200andUserReturned() throws Exception {
        User u4 = new User(4l, "Petya");
        Mockito.when(userService.createUser(Mockito.any())).thenReturn(u4);
        mockMvc.perform(
                post("/users")
                        .content(objectMapper.writeValueAsString(u4))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(u4)));
    }

    @Test
    void updateUser_whenUpdate_thenStatus200andUpdateReturns() throws Exception {
        User u5 = new User();
        u5.setName("Serj");
        User updateUser5 = new User((long) 5L, "Serjjj");
        Mockito.when(userService.updateUser(5l, u5)).thenReturn(updateUser5);
        mockMvc.perform(
                put("/users/5")
                        .content(new ObjectMapper().writeValueAsString(u5))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("5"))
                .andExpect(jsonPath("$.name").value("Serjjj"));
    }

    @Test
    void deleteUser_whenDeleteUser_thenStatus200() throws Exception {
        User u6 = new User(6l, "Pavluntiy");
        Mockito.when(userService.getUserById(Mockito.any())).thenReturn(u6);
        mockMvc.perform(
                delete("/users/6"))
                .andExpect(status().isOk());
    }
}

/*


Для написания юнит-теста с использованием MockMvc для метода updateUser вам потребуется
создать экземпляр класса MockMvc, а затем выполнить запрос PUT с указанием пути и тела запроса.

Вот пример того, как вы можете написать юнит-тест с использованием MockMvc:

java
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testUpdateUser() throws Exception {
        // Создание объекта User для передачи в качестве тела запроса
        User user = new User();
        user.setName("John");
        user.setEmail("john@example.com");

        // Установка ожидаемого результата от userService.updateUser()
        User updatedUser = new User();
        updatedUser.setId(1L);
        updatedUser.setName("John Doe");
        updatedUser.setEmail("johndoe@example.com");
        when(userService.updateUser(1L, user)).thenReturn(updatedUser);

        // Выполнение PUT-запроса с указанием пути и тела запроса
        mockMvc.perform(put("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isOk());
    }
}


В этом примере используется аннотация @WebMvcTest, которая позволяет создать экземпляр
класса MockMvc и настроить контекст приложения только для тестирования контроллера UserController.
Аннотация @MockBean создает заглушку для сервиса UserService, чтобы можно было настроить его поведение с помощью when().

В методе testUpdateUser() создается объект User для передачи в качестве тела запроса.
Затем настраивается ожидаемый результат от вызова userService.updateUser().
Наконец, выполняется PUT-запрос с указанием пути и тела запроса, а затем проверяется ожидаемый статус ответа
 с помощью метода andExpect().
*******************************************

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