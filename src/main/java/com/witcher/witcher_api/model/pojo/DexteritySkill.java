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
@JsonIgnoreProperties({"character"})
@Table(name = "dexterity_skill")
public class DexteritySkill {

    @Id
    @OneToOne
    @JoinColumn(name = "character_id")
    private Character character;

    private Integer archery;
    private Integer athletic;
    private Integer crossbow;
    private Integer sleight_of_hand;
    private Integer stealth;
}
