package com.example.taskmanagerdemo.controller;

import com.example.taskmanagerdemo.model.User;
import com.example.taskmanagerdemo.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(description = "Users data", name = "User Resource")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get users", description = "Get All users list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.response-codes.ok.desc}"),
            @ApiResponse(responseCode = "400", description = "${api.response-codes.badRequest.desc}",
                    content = {@Content(examples = {@ExampleObject(value = "")})}),
            @ApiResponse(responseCode = "400", description = "${api.response-codes.notFound.desc}",
                    content = {@Content(examples = {@ExampleObject(value = "")})})

    })
    @GetMapping("")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(summary = "Get users by ID", description = "Get user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.response-codes.ok.desc}"),
            @ApiResponse(responseCode = "400", description = "${api.response-codes.badRequest.desc}",
                    content = {@Content(examples = {@ExampleObject(value = "")})}),
            @ApiResponse(responseCode = "400", description = "${api.response-codes.notFound.desc}",
                    content = {@Content(examples = {@ExampleObject(value = "")})})

    })
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @Operation(summary = "Create user", description = "Create new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.response-codes.ok.desc}"),
            @ApiResponse(responseCode = "400", description = "${api.response-codes.badRequest.desc}",
                    content = {@Content(examples = {@ExampleObject(value = "")})}),
            @ApiResponse(responseCode = "400", description = "${api.response-codes.notFound.desc}",
                    content = {@Content(examples = {@ExampleObject(value = "")})})

    })
    @PostMapping("")
    public User createUser(@RequestBody User user) {
        User user1 = userService.createUser(user);
        if (user1 != null) {
            return user1;
        } else {
            return null;
        }
    }
//    return userService.createUser(user)

    @Operation(summary = "Update user", description = "Update user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.response-codes.ok.desc}"),
            @ApiResponse(responseCode = "400", description = "${api.response-codes.badRequest.desc}",
                    content = {@Content(examples = {@ExampleObject(value = "")})}),
            @ApiResponse(responseCode = "400", description = "${api.response-codes.notFound.desc}",
                    content = {@Content(examples = {@ExampleObject(value = "")})})

    })
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @Operation(summary = "Delete user", description = "Delete user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.response-codes.ok.desc}"),
            @ApiResponse(responseCode = "400", description = "${api.response-codes.badRequest.desc}",
                    content = {@Content(examples = {@ExampleObject(value = "")})}),
            @ApiResponse(responseCode = "400", description = "${api.response-codes.notFound.desc}",
                    content = {@Content(examples = {@ExampleObject(value = "")})})

    })
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
