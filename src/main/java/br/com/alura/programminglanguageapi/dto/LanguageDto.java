package br.com.alura.programminglanguageapi.dto;

public class LanguageDto {

    private String name;
    private String url;
    private String position;

    public LanguageDto(String name, String url, String position) {
        this.name = name;
        this.url = url;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
