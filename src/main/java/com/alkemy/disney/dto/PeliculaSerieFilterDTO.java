package com.alkemy.disney.dto;

import lombok.Data;

@Data
public class PeliculaSerieFilterDTO {
    private String nombre;
    private Long generoId;
    private String orden;

    public PeliculaSerieFilterDTO(String nombre, Long generoId, String orden) {
        this.nombre = nombre;
        this.generoId = generoId;
        this.orden = orden;
    }

    public boolean isASC(){
        return this.orden.compareToIgnoreCase("ASC")==0;
    }

    public boolean isDESC() {
        return this.orden.compareToIgnoreCase("DESC") == 0;
    }
}
