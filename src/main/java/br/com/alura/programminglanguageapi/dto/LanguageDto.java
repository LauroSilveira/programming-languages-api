package br.com.alura.programminglanguageapi.dto;

public class LanguageDto {

    private String id;
    private String name;
    private String url;
    private String position;

    public LanguageDto(final String id, final String name, final String url, final String position) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
