package br.com.alura.programminglanguageapi.dto;


import br.com.alura.programminglanguageapi.entity.Language;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LanguagesMapper {
    public List<LanguageDto> mapperToListDto(List<Language> languages) {
        return languages.stream().map(lang -> new LanguageDto(lang.getId(), lang.getTitle(), lang.getImage(),
                lang.getRanking())).toList();
    }

    public LanguageDto mapperToDto(Language lang) {
        return new LanguageDto(lang.getId(), lang.getTitle(), lang.getImage(), lang.getRanking());
    }

    public Language mapperToEntity(LanguageDto dto) {
        return new Language(dto.getName(), dto.getUrl(), dto.getPosition());
    }
}
