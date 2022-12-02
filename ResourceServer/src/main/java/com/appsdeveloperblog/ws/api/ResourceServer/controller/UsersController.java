package com.appsdeveloperblog.ws.api.ResourceServer.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    @GetMapping("/status/check")
    public String status() {
        return "Working";
    }

    @Secured("ROLE_developer")
    @DeleteMapping(path = "/{id}")
    public String deleteuser(@PathVariable String id) {
        return "Delete user with id: " + id;
    }
}
