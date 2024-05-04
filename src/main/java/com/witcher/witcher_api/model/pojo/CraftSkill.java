package com.witcher.witcher_api.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CraftSkill {

    private Integer alchemy = 0;
    private Integer crafting = 0;
    private Integer disguise = 0;
    private Integer first_aid = 0;
    private Integer forgery = 0;
    private Integer pick_lock = 0;
    private Integer trap_crafting = 0;
}
