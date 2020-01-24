package com.example.starWars.application.controller;

import com.example.starWars.application.model.Planets;
import com.example.starWars.application.repository.PlanetsRepository;
import com.example.starWars.application.service.SWAPI;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/planets")
public class StarWarsController {

    @Autowired
    private PlanetsRepository planetsRepository;

    @GetMapping("/listar")
    public List<Planets> findAll(){
        return planetsRepository.findAll();
    }

    @PostMapping()
    public ResponseEntity salvar(@RequestBody @Validated Planets planets) throws IOException, InterruptedException {
        JSONObject swAPIResponse = SWAPI.request(planets.getNome());
        if(swAPIResponse.isNull("result")) {
            return status(HttpStatus.OK).body("Planet name not found");
        }
        planets.setQuantidadeAparicoes(calculateMoviesAppearance(swAPIResponse));
        planetsRepository.save(planets);
        return status(HttpStatus.CREATED).body("Planet Created.");
    }

    @GetMapping("{planets}")
    public Planets findById(@PathVariable Planets planets) {
        return planets;
    }

    @GetMapping(params = "clima")
    public List<Planets> findByClima(String clima) {
        return planetsRepository.findByClima(clima);
    }

    @GetMapping(params = "terreno")
    public List<Planets> findByTerreno(String terreno) {
        return planetsRepository.findByTerreno(terreno);
    }

    @GetMapping(params = "nome")
    public Planets findByNome(String nome) {
        return planetsRepository.findByNome(nome);
    }

    private int calculateMoviesAppearance(JSONObject swAPIResponse) {
        return swAPIResponse.getJSONArray("results").getJSONObject(0).getJSONArray("films").length();
    }
}
