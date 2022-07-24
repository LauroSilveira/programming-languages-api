package br.com.alura.programminglanguageapi.dto;


import br.com.alura.programminglanguageapi.entity.Language;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LanguagesMapper {
   public List<LanguageDto> mapperToListDto(List<Language> languages) {
        return languages.stream().map(lang -> new LanguageDto(lang.getTitle(), lang.getImage(), lang.getRanking()))
                .collect(Collectors.toList());
    }

    public LanguageDto mapperToDto(Language lang) {
        return new LanguageDto(lang.getTitle(), lang.getImage(), lang.getRanking());
    }
}
