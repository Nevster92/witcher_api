package com.witcher.witcher_api.model.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_data")
public class User {

     @Id
    private String id;
    private String username;
    private String email;
}
