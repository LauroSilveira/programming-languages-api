package br.com.alura.programminglanguageapi.service;

import br.com.alura.programminglanguageapi.dto.LanguageDto;

import java.util.List;
import java.util.Optional;

public interface LanguageService {
    List<LanguageDto> getLanguages();

    LanguageDto saveLanguage(LanguageDto language);

    Optional<LanguageDto> getLanguageById(String id);

    boolean deleteById(String id);

    Optional<LanguageDto> partialUpdate(String ranking, String id);
}
