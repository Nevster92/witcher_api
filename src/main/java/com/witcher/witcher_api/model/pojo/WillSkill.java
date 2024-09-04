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
@Table(name = "will_skill")
public class WillSkill {

    @Id
    @OneToOne
    @JoinColumn(name = "character_id")
    private Character character;

    private Integer courage;
    private Integer hex_weaving;
    private Integer intimidation;
    private Integer spell_casting;
    private Integer resist_magic;
    private Integer resist_coercion;
    private Integer ritual_crafting;

}
