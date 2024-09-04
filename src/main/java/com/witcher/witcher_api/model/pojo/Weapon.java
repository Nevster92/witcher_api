package com.witcher.witcher_api.model.pojo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="weapon")
@JsonIgnoreProperties({"character"})
public class Weapon {

    @Id
    private Integer id;

    @Transient
    private int character_id;

    private String name;
    private String type;
    private Integer weapon_accuracy;
    private String availability;
    private String damage;
    private Integer reliability;
    private Integer hands_required;
    private String range;
    private String concealment;
    private Integer enhancements;
    private Double weight;
    private Integer cost;
    private String category;
    private String effect;

    @Override
    public String toString() {
        return "Weapon{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", weapon_accuracy=" + weapon_accuracy +
                ", availability='" + availability + '\'' +
                ", damage='" + damage + '\'' +
                ", reliability=" + reliability +
                ", hands_required=" + hands_required +
                ", range='" + range + '\'' +
                ", concealment='" + concealment + '\'' +
                ", enhancements=" + enhancements +
                ", weight=" + weight +
                ", cost=" + cost +
                ", category='" + category + '\'' +
                ", effect='" + effect + '\'' +
                ", id=" + id +
                '}';
    }
}
