package com.kalra.docker.db;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "countries")
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CountryEntity {

    @Id
    private int id;

    private String name;
}


