package com.witcher.witcher_api.model.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CharacterRequest {

    private int character_id;
    private String name;
    private String gender;
    private String profession;
}
