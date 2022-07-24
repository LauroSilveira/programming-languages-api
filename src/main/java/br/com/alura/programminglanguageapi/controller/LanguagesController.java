package br.com.alura.programminglanguageapi.controller;

import br.com.alura.programminglanguageapi.dto.LanguageDto;
import br.com.alura.programminglanguageapi.entity.Language;
import br.com.alura.programminglanguageapi.service.impl.LanguageServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/languages")
public class LanguagesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LanguagesController.class);
    private final LanguageServiceImpl service;

    @Autowired
    public LanguagesController(LanguageServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<LanguageDto>> getLanguages() {
        LOGGER.info("Request received to get all Programming Languages");
        final List<LanguageDto> languages = service.getLanguages();
        if (languages.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(languages, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<LanguageDto> saveLanguage(@RequestBody Language language) {
        LOGGER.info("Request received to Save a Programming Language");
        LanguageDto languageSaved = service.saveLanguage(language);
        return new ResponseEntity<>(languageSaved, HttpStatus.CREATED);
    }


    @PatchMapping("/{ranking}/{id}")
    public ResponseEntity<LanguageDto> partialUpdateLanguage(@PathVariable String ranking, @PathVariable String id) {
        LOGGER.info("Request received to Partial Update a Programming Language");
        Optional<LanguageDto> languageDto = service.partialUpdate(ranking, id);
        return languageDto.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LanguageDto> getLanguageById(@PathVariable String id) {
        LOGGER.info("Request received to get a Programming Language By Id {} ", id);
        Optional<LanguageDto> languageById = service.getLanguageById(id);
        return languageById.map(languageDto -> new ResponseEntity<>(languageDto, HttpStatus.FOUND)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteLanguageById(@PathVariable String id) {
        LOGGER.info("Request received to Delete a Programming Language By Id {} ", id);
        boolean isDeleted = service.deleteById(id);
        if (isDeleted) {
            return ResponseEntity.ok(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
