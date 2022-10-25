package br.com.alura.programminglanguageapi.repository;

import br.com.alura.programminglanguageapi.model.Language;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LanguageRepository extends MongoRepository<Language,String> {

}
