package com.example.harrypotterex.request;

import com.example.harrypotterex.entity.Personagem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonagemRequest {
    private String nome;

    public Personagem convert(){
        Personagem personagem = new Personagem();
        personagem.setNome(this.nome);
        return personagem;
    }
}
