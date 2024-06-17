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

import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Named;
import sro.example.config.StadiumConfiguration;
import sro.example.config.TeamConfiguration;

/**
 *
 * @author saulramirez
 */
@Controller("/sports")
public class SportsController {

    private final TeamConfiguration teamConfiguration;
    private final StadiumConfiguration stadiumConfiguration;
    
   public SportsController(@Nullable TeamConfiguration teamConfiguration,
                        @Nullable @Named("pnc") StadiumConfiguration stadiumConfiguration) { 
        this.teamConfiguration = teamConfiguration;
        this.stadiumConfiguration = stadiumConfiguration;
    }    
    
    @Get("/team")
    @Secured(SecurityRule.IS_ANONYMOUS)
    public TeamConfiguration team() {
        return this.teamConfiguration;
    }

    @Get("/stadium")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    public  StadiumConfiguration stadium() {
        return this.stadiumConfiguration;
    }   
}
