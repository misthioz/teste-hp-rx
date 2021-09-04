package com.example.harrypotterex.service;

import com.example.harrypotterex.entity.Casa;
import com.example.harrypotterex.entity.Personagem;
import com.example.harrypotterex.entity.SorteioChapeu;
import com.example.harrypotterex.repository.PersonagemRepository;
import com.example.harrypotterex.request.PersonagemRequest;
import com.example.harrypotterex.response.PersonagemResponse;
import com.google.gson.Gson;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

@Service
@AllArgsConstructor
public class PersonagemService {
    private final PersonagemRepository personagemRepository;

    public Casa retornaInfoCasa(Personagem personagem) {

        String url = "https://api-harrypotter.herokuapp.com/house/" + personagem.getId_casa();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resposta = restTemplate.getForEntity(url, String.class);
        Gson gson = new Gson();
        return gson.fromJson(resposta.getBody(), Casa.class);
    }

    public XsiNilLoader.Single<PersonagemResponse> gravarPersonagem(PersonagemRequest personagemRequest) {
        return XsiNilLoader.Single.create(single -> {
            Personagem personagem = personagemRequest.convert();
            personagem.setId_casa(ecolherCasa().getId());
            Casa casa = retornaInfoCasa(personagem);
            personagemRepository.save(personagem);
            single.onSuccess(new PersonagemResponse(personagem, casa));
        });


    }

    public SorteioChapeu ecolherCasa() {
        String url = "https://api-harrypotter.herokuapp.com/sortinghat";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resposta = restTemplate.getForEntity(url, String.class);
        Gson gson = new Gson();
        return gson.fromJson(resposta.getBody(), SorteioChapeu.class);
    }

    public List<PersonagemResponse> converter(List<Personagem> personagens) {
        List<PersonagemResponse> lista = new ArrayList<>();
        for (Personagem personagem : personagens) {
            Casa casa = retornaInfoCasa(personagem);
            PersonagemResponse personagemResponse = new PersonagemResponse(personagem, casa);
            lista.add(personagemResponse);
        }
        return lista;
    }


    public Observable<PersonagemResponse> findAll() {

        return Observable.fromIterable(personagemRepository.findAll()).map(this::Sconvert);

    }

    public PersonagemResponse Sconvert(Personagem personagem) {
        Casa casa = retornaInfoCasa(personagem);
        return new PersonagemResponse(personagem, casa);
    }



}
