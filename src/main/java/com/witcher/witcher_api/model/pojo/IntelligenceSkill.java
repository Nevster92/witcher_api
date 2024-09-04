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
@Table(name = "intelligence_skill")
 @JsonIgnoreProperties({"character"})
public class IntelligenceSkill {

    @JsonIgnore
    @Id
    @OneToOne
    @JoinColumn(name = "character_id")
    private Character character;

    private Integer awareness ;
    private Integer business ;
    private Integer deduction;
    private Integer education;
    private Integer common_speech;
    private Integer elder_speech;
    private Integer dwarven_speech;
    private Integer monster_lore;
    private Integer social_etiquette;
    private Integer streetwise;
    private Integer tactics;
    private Integer teaching ;
    private Integer wilderness_survival;
}
