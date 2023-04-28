package com.company.buzzstreet.controller;

import com.company.buzzstreet.entity.User;
import com.company.buzzstreet.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "Get all users",
            description = "Return a list of Buzz Street users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok, succesful operation",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<User>> getUsers(){ return userService.getUsers(); }

    @Operation(summary = "Create user",
            description = "Create a Buzz Street user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok, succesful operation",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "400", description = "Bad request, user exists in the database",
                    content = @Content(schema = @Schema()))
    })
    @PostMapping(produces = "application/json")
    public ResponseEntity<User> createUser(@RequestBody final User user){
        return userService.createUser(user);
    }

    @Operation(summary = "Delete user",
            description = "Delete a Buzz Street user by searching with the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok, succesful operation",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(schema = @Schema()))
    })
    @DeleteMapping(value = "{id}", produces = "application/json")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }

    @Operation(summary = "Get user",
            description = "Return an specifit Buzz Street user by searching with the name and email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok, succesful operation",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(schema = @Schema()))
    })
    @PostMapping(value ="/getUser", produces = "application/json")
    public ResponseEntity<User> getUser(@RequestBody final User user){ return userService.getUser(user); }

    @Operation(summary = "Update user",
            description = "Update name and email of a Buzz Street user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok, succesful operation",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(schema = @Schema()))
    })
    @PutMapping(value = "{id}", produces = "application/json")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
        return userService.updateUser(id, user);
    }
}
