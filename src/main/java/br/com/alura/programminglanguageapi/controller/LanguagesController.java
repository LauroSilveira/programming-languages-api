package br.com.alura.programminglanguageapi.controller;

import br.com.alura.programminglanguageapi.dto.LanguagesDto;
import br.com.alura.programminglanguageapi.entity.Language;
import br.com.alura.programminglanguageapi.service.impl.LanguageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Slf4j
@RestController
@RequestMapping("/languages")
public class LanguagesController {

    private final LanguageServiceImpl service;

    @Autowired
    public LanguagesController(LanguageServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<LanguagesDto>> languages() {
        log.info("Test");
        final List<LanguagesDto> languages = service.languages();
        if (languages.isEmpty()) {
            return new ResponseEntity<>(languages, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping
    public ResponseEntity<Language> language(@RequestBody Language language) {
        Language languageSaved = service.saveLanguage(language);
        return new ResponseEntity<>(languageSaved, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity languageById(@PathVariable String id) {
        Optional<Language> languageById = service.languageById(id);
        return languageById.map(language -> new ResponseEntity(language, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteLanguageById(@PathVariable String id) {
        boolean isDeleted = service.deleteById(id);
        if (isDeleted) {
            return ResponseEntity.ok(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
