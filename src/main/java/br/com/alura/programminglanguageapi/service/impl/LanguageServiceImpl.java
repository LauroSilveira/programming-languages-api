package br.com.alura.programminglanguageapi.service.impl;

import br.com.alura.programminglanguageapi.dto.LanguageDto;
import br.com.alura.programminglanguageapi.dto.LanguagesMapper;
import br.com.alura.programminglanguageapi.entity.Language;
import br.com.alura.programminglanguageapi.exception.PersistException;
import br.com.alura.programminglanguageapi.repository.LanguageRepository;
import br.com.alura.programminglanguageapi.service.LanguageService;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
    public List<LanguageDto> getLanguages() {
        Optional<List<Language>> languages = Optional.of(repository.findAll());
     return languages.map(mapper::mapperToListDto).orElse(Collections.emptyList());
    }

    @Override
    public LanguageDto saveLanguage(Language language) {
        try {
            Language lang = repository.save(language);
            return mapper.mapperToDto(lang);
        } catch (PersistException e) {
            throw new PersistException("Error to persist entity", e.getCause());
        }

    }

    @Override
    public Optional<LanguageDto> getLanguageById(String id) {
        Optional<Language> language = repository.findById(id);
        return language.map(mapper::mapperToDto);
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

    @Override
    public Optional<LanguageDto> partialUpdate(String ranking, String id) {
        Optional<Language> language = repository.findById(id);
        if(language.isPresent()) {
            //update ranking
            language.get().setRanking(ranking);
            //persist entity and returns it updated
            Language languageUpdated = repository.save(language.get());
           return Optional.ofNullable(mapper.mapperToDto(languageUpdated));
        }else {
            return Optional.empty();
        }
    }
}
