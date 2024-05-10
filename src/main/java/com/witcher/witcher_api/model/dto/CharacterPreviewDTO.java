package com.witcher.witcher_api.model.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CharacterPreviewDTO {

    @Id
    private Integer id;


    private String name;

    private String profession;

    private String race;

    private String gender;

    private Integer age;

    private Integer intelligence;
    private Integer ref;
    private Integer dex;
    private Integer body;
    private Integer spd;
    private Integer emp;
    private Integer cra;
    private Integer will;
    private Integer luck;
    private Integer stun;
    private Integer run;
    private Integer leap;
    private Integer hp;
    private Integer sta;
    private Integer enc;
    private Integer rec;

    private Integer max_hp;
    private Integer melee_bonus;

    private Integer punch = 0;
    private Integer kick = 0;

}
