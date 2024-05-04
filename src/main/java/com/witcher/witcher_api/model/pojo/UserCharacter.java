package com.witcher.witcher_api.model.pojo;

import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
@Table(name = "user_character")
public class UserCharacter implements Serializable {


    @Column(name = "user_id")
    private String userId;

    @Column(name = "character_id")
    private Long characterId;

}
