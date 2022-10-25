package br.com.alura.programminglanguageapi.dto;


import br.com.alura.programminglanguageapi.model.Language;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class LanguagesMapper {

  public List<LanguageDto> mapperToListDto(List<Language> languages) {
    return languages.stream().map(lang -> LanguageDto.builder().id(lang.getId())
        .name(lang.getTitle())
        .url(lang.getImage())
        .position(lang.getRanking()).build())
        .toList();
  }

  public LanguageDto mapperToDto(Language lang) {
    return LanguageDto.builder()
        .id(lang.getId())
        .name(lang.getTitle())
        .url(lang.getImage())
        .position(lang.getRanking())
        .build();
  }

  public Language mapperToEntity(LanguageDto dto) {
    return Language.builder()
        .image(dto.url())
        .title(dto.name())
        .ranking(dto.position())
        .build();
  }
}
