package com.witcher.witcher_api.model.pojo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "reflex_skill")
@JsonIgnoreProperties({"character"})
public class ReflexSkill {
    @JsonIgnore
    @Id
    @OneToOne
    @JoinColumn(name = "character_id")
    private Character character;

    private Integer brawling;
    private Integer dodge;
    private Integer meele;
    private Integer riding;
    private Integer sailing;
    private Integer small_blades;
    private Integer staff;
    private Integer swordsmanship;


}
