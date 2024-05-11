package com.witcher.witcher_api.model.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"character"})
@Table(name = "body_skill")
public class BodySkill {

    @JsonIgnore
    @Id
    @OneToOne
    @JoinColumn(name = "character_id")
    private Character character;


    private Integer physique;
    private Integer endurance;

    public void update(BodySkill body){
        if(body.getPhysique() != null){
            this.physique = body.getPhysique();
        }
        if(body.getEndurance() != null){
            this.endurance = body.getEndurance();
        }
    }

}
