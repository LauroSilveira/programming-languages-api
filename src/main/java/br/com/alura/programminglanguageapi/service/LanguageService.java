package br.com.alura.programminglanguageapi.service;

import br.com.alura.programminglanguageapi.entity.Language;
import br.com.alura.programminglanguageapi.repository.LanguageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {

    private final LanguageRepository repository;

    public LanguageService(LanguageRepository repository) {
        this.repository = repository;
    }

    public Optional<List<Language>> getAllLanguages() {
        return Optional.ofNullable(repository.findAll());
    }
}
