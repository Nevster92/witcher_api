package com.witcher.witcher_api.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DexteritySkill {
    private Integer archery = 0;
    private Integer athletic = 0;
    private Integer crossbow = 0;
    private Integer sleight_of_hand = 0;
    private Integer stealth = 0;
}
