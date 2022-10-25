package br.com.alura.programminglanguageapi.dto;

import lombok.Builder;

@Builder
public record LanguageDto(String id, String name, String url, String position) {

}
