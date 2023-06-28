package com.example.taskmanagerdemo.controller;

import com.example.taskmanagerdemo.model.Taska;
import com.example.taskmanagerdemo.service.TaskaService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/taskas")
@Tag(description = "Taska data", name = "Taska Resource")
public class TaskaController {

    private final TaskaService taskaService;

    public TaskaController(TaskaService taskaService) {
        this.taskaService = taskaService;
    }

    @Operation(summary = "Get taskas", description = "Get All taskas list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.response-codes.ok.desc}"),
            @ApiResponse(responseCode = "400", description = "${api.response-codes.badRequest.desc}",
                    content = {@Content(examples = {@ExampleObject(value = "")})}),
            @ApiResponse(responseCode = "400", description = "${api.response-codes.notFound.desc}",
                    content = {@Content(examples = {@ExampleObject(value = "")})})

    })
    @GetMapping("")
    public List<Taska> getAllTaskas() {
        return taskaService.getAllTaskas();
    }

    @Operation(summary = "Get taska", description = "Get taska by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.response-codes.ok.desc}"),
            @ApiResponse(responseCode = "400", description = "${api.response-codes.badRequest.desc}",
                    content = {@Content(examples = {@ExampleObject(value = "")})}),
            @ApiResponse(responseCode = "400", description = "${api.response-codes.notFound.desc}",
                    content = {@Content(examples = {@ExampleObject(value = "")})})

    })
    @GetMapping("/{id}")
    public Taska getTaskaById(@PathVariable Long id) {
        return taskaService.getTaskaById(id);
    }

    @Operation(summary = "Create taska", description = "Create new taska")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.response-codes.ok.desc}"),
            @ApiResponse(responseCode = "400", description = "${api.response-codes.badRequest.desc}",
                    content = {@Content(examples = {@ExampleObject(value = "")})}),
            @ApiResponse(responseCode = "400", description = "${api.response-codes.notFound.desc}",
                    content = {@Content(examples = {@ExampleObject(value = "")})})

    })
    @PostMapping()
    public Taska addTaska(@RequestBody Taska taska, @RequestParam Long userId) {
        return taskaService.addTaska(taska, userId);
    }

//    public Taska createTaska(@RequestBody Taska taska) {
//        return taskaService.createTaska(taska);
//    }

    @Operation(summary = "Udate taska", description = "Udate taska by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.response-codes.ok.desc}"),
            @ApiResponse(responseCode = "400", description = "${api.response-codes.badRequest.desc}",
                    content = {@Content(examples = {@ExampleObject(value = "")})}),
            @ApiResponse(responseCode = "400", description = "${api.response-codes.notFound.desc}",
                    content = {@Content(examples = {@ExampleObject(value = "")})})

    })
    @PutMapping("/{id}")
    public Taska updateTaska(@PathVariable Long id, @RequestBody Taska taska) {
        return taskaService.updateTaska(id, taska);
    }

    @Operation(summary = "Delete taska", description = "Delete taska by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.response-codes.ok.desc}"),
            @ApiResponse(responseCode = "400", description = "${api.response-codes.badRequest.desc}",
                    content = {@Content(examples = {@ExampleObject(value = "")})}),
            @ApiResponse(responseCode = "400", description = "${api.response-codes.notFound.desc}",
                    content = {@Content(examples = {@ExampleObject(value = "")})})

    })
    @DeleteMapping("/{id}")
    public void deleteTaska(@PathVariable Long id) {
        taskaService.deleteTaska(id);
    }
}

