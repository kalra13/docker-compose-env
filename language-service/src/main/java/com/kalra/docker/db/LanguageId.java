package com.kalra.docker.db;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LanguageId implements Serializable {

    private String name;
    private String country;

}