package com.appsdeveloperblog.ws.api.ResourceServer.controller;

import com.appsdeveloperblog.ws.api.ResourceServer.response.UserRest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    @GetMapping("/status/check")
    public String status() {
        return "Working";
    }

    //@Secured("ROLE_developer") //This annotation is used to check if the user has the role "developer" iside of the token that was returned from the Authorization Server
    //@PostAuthorize("hasRole('developer') or returnObject.name == #userName")
    @PreAuthorize("hasAuthority('ROLE_developer') or #id == #jwt.subject") // ("hasAnyRole('admin','user')") || ("hasRole('developer') and #id == #userId") etc
    @DeleteMapping(path = "/{id}")
    public String deleteUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        return "Delete user with id: " + id + " and user name: " + jwt.getClaimAsString("preferred_username") + " and JWT subject: " + jwt.getSubject();
    }

    @PostAuthorize("returnObject.userId == # jwt.subject")
    @GetMapping(path = "/{id}")
    public UserRest getUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        return new UserRest("John", "Doe", "f7edb780-64dd-47bf-a7a7-bf266d0bf8");
    }
}
