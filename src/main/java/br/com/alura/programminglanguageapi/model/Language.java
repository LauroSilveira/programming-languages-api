package br.com.alura.programminglanguageapi.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "languages")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Language {

  @Id
  private String id;
  private String title;
  private String image;
  private String ranking;

}
