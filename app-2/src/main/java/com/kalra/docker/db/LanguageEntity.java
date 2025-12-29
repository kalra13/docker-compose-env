package com.kalra.docker.db;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "language")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(LanguageId.class)
public class LanguageEntity {


    @Id
    private String name;

    @Id
    private String country;




}
