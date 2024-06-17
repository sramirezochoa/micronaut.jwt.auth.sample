/*
 * Copyright 2024 saulramirez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sro.example.controller;

/**
 *
 * @author saulramirez
 */
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.security.token.jwt.generator.JwtTokenGenerator;
import jakarta.inject.Inject;
import java.util.Optional;

@Controller("/login")
@Secured(SecurityRule.IS_ANONYMOUS)
public class LoginController {

    @Inject
    private JwtTokenGenerator tokenGenerator;

    @Post("/post")
    @Produces(MediaType.TEXT_PLAIN)
    public String loginPost() {
        // Authenticate user
        System.out.println("Hey from Post!!");
        Authentication authentication = authenticateUser("", "");
        if (authentication != null) {
            // Generate token
            return tokenGenerator.generateToken(authentication, Integer.MIN_VALUE).get();
        } else {
            return "Authentication failed";
        }
    }
    
    @Get("/get")
    @Produces(MediaType.TEXT_PLAIN)
    public String loginGet() {
        // Authenticate user
        System.out.println("Hey from Get!!");
        Authentication authentication = authenticateUser("", "");
        if (authentication != null) {
            // Generate token
            return tokenGenerator.generateToken(authentication, 300).get();
        } else {
            return "Authentication failed";
        }
    }    

    private Authentication authenticateUser(String username, String password) {
        // Implement authentication logic here (e.g., LDAP authentication)
        // Return Authentication object upon successful authentication
        // Return null if authentication fails
        return Authentication.build("a1024408");
    }
}

