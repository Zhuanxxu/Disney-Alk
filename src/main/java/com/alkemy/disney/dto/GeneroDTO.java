package com.alkemy.disney.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class GeneroDTO {
    private Long id;
    @NotNull @NotEmpty
    private String nombre;
    private String imagen;
}
