package com.example.starWars.application.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Planets {

    @Id
    public String id;

    @NotNull
    public String nome;
    @NotNull
    public String clima;
    @NotNull
    public String terreno;

    public int quantidadeAparicoes;

    public Planets (String nome, String clima, String terreno) {
        this.nome = nome;
        this.terreno = terreno;
        this.clima = clima;
    }
}
