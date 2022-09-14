package com.alkemy.disney.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GeneroDTO {
    private Long id;
    @NotNull
    private String nombre;
    private String imagen;
}
