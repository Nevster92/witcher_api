package com.witcher.witcher_api.model.pojo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="armor")
@JsonIgnoreProperties({"character"})
public class Armor {

    @Id
    private Integer id;

    @JsonIgnore
    private int character_id;

    private String name;
    private String category;
    private Integer stopping_power;
    private Integer armor_enhancement;
    private String availability;
    private String effect;
    private Integer encumbrance_value;
    private Double weight;
    private Integer price;
}
