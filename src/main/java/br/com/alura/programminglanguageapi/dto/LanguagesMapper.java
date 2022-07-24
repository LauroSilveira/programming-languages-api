package br.com.alura.programminglanguageapi.dto;


import br.com.alura.programminglanguageapi.entity.Language;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LanguagesMapper {

    @Mapping(target = "name", source = "title")
    @Mapping(target = "url", source = "image")
    @Mapping(target = "position", source = "ranking")
    List<LanguagesDto> entitiesToDto(List<Language> list);


    LanguagesDto entitiesToDto(Language entity);
}
