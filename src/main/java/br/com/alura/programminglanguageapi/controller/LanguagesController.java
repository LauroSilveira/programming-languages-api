package br.com.alura.programminglanguageapi.controller;

import br.com.alura.programminglanguageapi.dto.LanguageDto;
import br.com.alura.programminglanguageapi.service.impl.LanguageServiceImpl;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
  public ResponseEntity<List<LanguageDto>> getLanguages() {
    log.info("Request received to get all Programming Languages");
    final List<LanguageDto> languages = service.getLanguages();
    if (languages.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      //returns list sorted by field position
      return new ResponseEntity<>(
          languages.stream().sorted(Comparator.comparing(LanguageDto::position))
              .toList(), HttpStatus.OK);
    }
  }

  @PostMapping
  public ResponseEntity<LanguageDto> saveLanguage(@RequestBody LanguageDto language) {
    log.info("Request received to Save a Programming Language");
    LanguageDto languageSaved = service.saveLanguage(language);
    return new ResponseEntity<>(languageSaved, HttpStatus.CREATED);
  }

  @PatchMapping("/{ranking}/{id}")
  public ResponseEntity<LanguageDto> partialUpdateLanguage(@PathVariable String ranking,
      @PathVariable String id) {
    log.info("Request received to Partial Update a Programming Language");
    Optional<LanguageDto> languageDto = service.partialUpdate(ranking, id);
    return languageDto.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @GetMapping("/language/{id}")
  public ResponseEntity<LanguageDto> getLanguageById(@PathVariable String id) {
    log.info("Request received to get a Programming Language By Id {} ", id);
    Optional<LanguageDto> languageById = service.getLanguageById(id);
    return languageById.map(languageDto -> new ResponseEntity<>(languageDto, HttpStatus.FOUND))
        .orElseGet(() ->
            new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }


  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteLanguageById(@PathVariable String id) {
    log.info("Request received to Delete a Programming Language By Id {} ", id);
    boolean isDeleted = service.deleteById(id);
    if (isDeleted) {
      return ResponseEntity.ok(HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
