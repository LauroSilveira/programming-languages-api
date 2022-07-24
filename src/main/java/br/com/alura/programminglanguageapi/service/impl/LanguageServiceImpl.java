package br.com.alura.programminglanguageapi.service.impl;

import br.com.alura.programminglanguageapi.dto.LanguagesDto;
import br.com.alura.programminglanguageapi.dto.LanguagesMapper;
import br.com.alura.programminglanguageapi.entity.Language;
import br.com.alura.programminglanguageapi.exception.PersistException;
import br.com.alura.programminglanguageapi.repository.LanguageRepository;
import br.com.alura.programminglanguageapi.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository repository;
    private final LanguagesMapper mapper;

    public LanguageServiceImpl(final LanguageRepository repository, LanguagesMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<LanguagesDto> languages() {
        Optional<List<Language>> languages = Optional.of(repository.findAll());
        return mapper.entitiesToDto(languages.get());
    }

    @Override
    public Language saveLanguage(Language language) {
        try {
            return repository.save(language);
        } catch (PersistException e) {
            throw new PersistException("Error to persist entity", e.getCause());
        }

    }

    @Override
    public Optional<Language> languageById(String id) {
        return repository.findById(id);
    }

    @Override
    public boolean deleteById(String id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (PersistException e) {
            throw new PersistException("Error to Delete entity", e.getCause());
        }
    }
}
