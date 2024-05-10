package com.witcher.witcher_api.model.pojo;

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

    @Id
    @OneToOne
    @JoinColumn(name = "character_id")
    private Character character;

    private Integer awareness = 0;
    private Integer business = 0;
    private Integer deduction = 0;
    private Integer education = 0;
    private Integer common_speech = 0;
    private Integer elder_speech = 0;
    private Integer dwarven_speech = 0;
    private Integer monster_lore = 0;
    private Integer social_etiquette = 0;
    private Integer streetwise = 0;
    private Integer tactics = 0;
    private Integer teaching = 0;
    private Integer wilderness_survival = 0;
}
