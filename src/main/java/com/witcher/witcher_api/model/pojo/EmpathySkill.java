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
@Table(name = "empathy_skill")
public class EmpathySkill {

    @Id
    @OneToOne
    @JoinColumn(name = "character_id")
    private Character character;


    private Integer charisma = 0;
    private Integer deceit = 0;
    private Integer fine_art = 0;
    private Integer gambling = 0;
    private Integer style = 0;
    private Integer human_perception = 0;
    private Integer leadership = 0;
    private Integer persuasion = 0;
    private Integer performance = 0;
    private Integer seduction = 0;
}
