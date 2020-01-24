package com.example.starWars.application.repository;

import com.example.starWars.application.model.Planets;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PlanetsRepository extends MongoRepository<Planets,String> {

    public Planets findByNome(String nome);

    public List<Planets> findByClima(String clina);

    public List<Planets> findByTerreno(String terreno);
    
}
