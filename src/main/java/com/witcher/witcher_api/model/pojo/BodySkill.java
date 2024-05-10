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
@Table(name = "body_skill")
public class BodySkill {

    @Id
    @OneToOne
    @JoinColumn(name = "character_id")
    private Character character;

    private Integer physique = 0;
    private Integer endurance = 0;
}
