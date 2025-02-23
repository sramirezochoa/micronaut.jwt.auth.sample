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

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import sro.example.config.StadiumConfiguration;
import sro.example.config.TeamConfiguration;

@MicronautTest
public class SportsControllerTest {    

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void testMyTeam() {
        TeamConfiguration teamConfiguration = client.toBlocking()
                .retrieve(HttpRequest.GET("/sports/team"), TeamConfiguration.class);
        assertEquals("Steelers", teamConfiguration.getName());
        assertEquals("Black", teamConfiguration.getColor());
        List<String> expectedPlayers = Arrays.asList("Mason Rudolph", "James Connor");
        assertEquals(expectedPlayers.size(), teamConfiguration.getPlayerNames().size());
        expectedPlayers.forEach(name -> assertTrue(teamConfiguration.getPlayerNames().contains(name)));
    }

    @Test
    void testMyStadium() {
        StadiumConfiguration conf = client.toBlocking()
                .retrieve(HttpRequest.GET("/sports/stadium"), StadiumConfiguration.class);
        assertEquals("Pittsburgh", conf.getCity());
        assertEquals(35000, conf.getSize());
    }
}