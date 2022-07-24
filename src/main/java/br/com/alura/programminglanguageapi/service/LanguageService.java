package br.com.alura.programminglanguageapi.service;

import br.com.alura.programminglanguageapi.dto.LanguagesDto;
import br.com.alura.programminglanguageapi.entity.Language;

import java.util.List;
import java.util.Optional;

public interface LanguageService {
    List<LanguagesDto> languages();

    Language saveLanguage(Language language);

    Optional<Language> languageById(String id);

    boolean deleteById(String id);
}
