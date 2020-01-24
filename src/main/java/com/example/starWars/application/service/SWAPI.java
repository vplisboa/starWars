package com.example.starWars.application.service;

import org.json.JSONObject;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SWAPI {

    public static JSONObject request(String planetName) throws IOException, InterruptedException {
       // URI uri = UriComponentsBuilder.newInstance().scheme("https").host("swapi.co").path("api/planets").queryParam("search",planetName).build().toUri();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://swapi.co/api/planets?search="+planetName))
                //.GET()
                //.version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpResponse<String> response = HttpClient
                .newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject apiResult = new JSONObject(response.body());
        if(apiResult.getJSONArray("results").length() == 0) return new JSONObject();
        return apiResult;
    }
}
