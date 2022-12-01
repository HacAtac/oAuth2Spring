package com.appsdeveloperblog.ws.api.ResourceServer.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/token")
public class TokenController {

        //Getting an access token from the Authorization Server into this Resource Server and
        //retuns the token as a Map with the a key "principal" and the jwt object
        // this is possible with the @AuthenticationPrincipal annotation.
        @GetMapping
        public Map<String, Object> getToken(@AuthenticationPrincipal Jwt jwt) {
            return Collections.singletonMap("principal", jwt);
        }

        //We can also return just jwt object like this
//    @GetMapping
//    public Jwt getToken(@AuthenticationPrincipal Jwt jwt) {
//        return jwt;
//    }

}
