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
@Table(name = "craft_skill")
public class CraftSkill {

    @Id
    @OneToOne
    @JoinColumn(name = "character_id")
    private Character character;

    private Integer alchemy = 0;
    private Integer crafting = 0;
    private Integer disguise = 0;
    private Integer first_aid = 0;
    private Integer forgery = 0;
    private Integer pick_lock = 0;
    private Integer trap_crafting = 0;
}
