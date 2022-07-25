package br.com.alura.programminglanguageapi.service.impl;

import br.com.alura.programminglanguageapi.dto.LanguageDto;
import br.com.alura.programminglanguageapi.dto.LanguagesMapper;
import br.com.alura.programminglanguageapi.entity.Language;
import br.com.alura.programminglanguageapi.exception.PersistException;
import br.com.alura.programminglanguageapi.repository.LanguageRepository;
import br.com.alura.programminglanguageapi.service.LanguageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class LanguageServiceImpl implements LanguageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LanguageServiceImpl.class);

    private final LanguageRepository repository;
    private final LanguagesMapper mapper;

    public LanguageServiceImpl(final LanguageRepository repository, LanguagesMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<LanguageDto> getLanguages() {
        final Optional<List<Language>> languages = Optional.of(repository.findAll());
     return languages.map(mapper::mapperToListDto).orElse(Collections.emptyList());
    }

    @Override
    public LanguageDto saveLanguage(LanguageDto language) {
        LOGGER.info("Received Language to be persist with name {}, url {}, position {} ",
                language.getName(), language.getUrl(), language.getPosition());
        try {
            //first it is mapped to entity
            final Language entity = mapper.mapperToEntity(language);
            //it is persisted
            final Language newLanguage = repository.save(entity);
            //then it is mapped to DTO to return to controller
            return mapper.mapperToDto(newLanguage);
        } catch (PersistException e) {
            throw new PersistException("Error to persist entity", e.getCause());
        }
    }

    @Override
    public Optional<LanguageDto> getLanguageById(String id) {
        LOGGER.info("Request to get Language with Id {} ", id);
        final Optional<Language> language = repository.findById(id);
        return language.map(mapper::mapperToDto);
    }

    @Override
    public boolean deleteById(String id) {
        try {
            repository.deleteById(id);
            LOGGER.info("Language has been Deleted with Id {} ", id);
            return true;
        } catch (PersistException e) {
            LOGGER.info("Cannot Delete Language with Id {} ", id);
            throw new PersistException("Error to Delete entity", e.getCause());
        }
    }

    @Override
    public Optional<LanguageDto> partialUpdate(String ranking, String id) {
        LOGGER.info("Request to Partial Update Language with Id {} ", id);
        Optional<Language> language = repository.findById(id);
        if(language.isPresent()) {
            //update ranking
            language.get().setRanking(ranking);
            //persist entity and returns it updated
            Language languageUpdated = repository.save(language.get());
            LOGGER.info("Partial Update applied to Language with Id {} ", id);
           return Optional.ofNullable(mapper.mapperToDto(languageUpdated));
        }else {
            return Optional.empty();
        }
    }
}
