package com.example.harrypotterex.controller;

import com.example.harrypotterex.request.PersonagemRequest;
import com.example.harrypotterex.response.PersonagemResponse;
import com.example.harrypotterex.service.PersonagemService;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Observable;

@RestController
@RequestMapping("/personagem")
@AllArgsConstructor
public class PersonagemController {
    private final PersonagemService personagemService;

    @PostMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    public XsiNilLoader.Single<PersonagemResponse> addPersonagem(@RequestBody PersonagemRequest personagemRequest) {
        return personagemService.gravarPersonagem(personagemRequest);
    }

    @GetMapping("lista")
    @ResponseStatus(HttpStatus.OK)
    public Observable<PersonagemResponse> listarTodos() {
        return personagemService.listarTodos();

    }

}
