package br.com.alura.programminglanguageapi.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "languages")
public class Language {

    @Id
    private String id;
    private String title;
    private String image;
    private String ranking;

    public Language() {

    }

    public Language(String title, String image, String ranking) {
        this.title = title;
        this.image = image;
        this.ranking = ranking;
    }

    public String getId() {
        return id;
    }

    public Language setId(String id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Language setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Language setImage(String image) {
        this.image = image;
        return this;
    }

    public String getRanking() {
        return ranking;
    }

    public Language setRanking(String ranking) {
        this.ranking = ranking;
        return this;
    }

    public Language builder() {
        final Language language = new Language();
        language.id = this.id;
        language.title = this.title;
        language.image = this.image;
        return language;
    }
}
