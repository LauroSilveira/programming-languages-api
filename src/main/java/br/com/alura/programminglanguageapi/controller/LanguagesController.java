package br.com.alura.programminglanguageapi.controller;

import br.com.alura.programminglanguageapi.entity.Language;
import br.com.alura.programminglanguageapi.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/languages")
public class LanguagesController {

    private final LanguageService service;

    @Autowired
    public LanguagesController(LanguageService service) {
        this.service = service;
    }

    @GetMapping
    private ResponseEntity<List<Language>> getAllLanguages() {
        Optional<List<Language>> languages = service.getAllLanguages();
        return languages.map(languageList -> new ResponseEntity<>(languageList, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.of(Optional.of(Collections.singletonList(new Language().builder()))));
    }

}
