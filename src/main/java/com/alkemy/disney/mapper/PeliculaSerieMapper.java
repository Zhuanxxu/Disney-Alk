package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.PeliculaSerieDTO;
import com.alkemy.disney.dto.PersonajeDTO;
import com.alkemy.disney.entity.PeliculaSerieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PeliculaSerieMapper {

    @Autowired
    private PersonajeMapper personajeMapper;

    public PeliculaSerieEntity peliculaSerieDTO2Entity(PeliculaSerieDTO dto){
        PeliculaSerieEntity peliculaSerieEntity = new PeliculaSerieEntity();
        peliculaSerieEntity.setImagen(dto.getImagen());
        peliculaSerieEntity.setCalificacion(dto.getCalificacion());
        peliculaSerieEntity.setFechaCreacion(dto.getFechaCreacion());
        peliculaSerieEntity.setTitulo(dto.getTitulo());
        peliculaSerieEntity.setGenero(dto.getGenero());
        //peliculaSerieEntity.setPersonajes(dto.getPersonajes());


        return peliculaSerieEntity;
    }

    public PeliculaSerieDTO peliculaSerieEntity2DTO(PeliculaSerieEntity entity){
        PeliculaSerieDTO dto = new PeliculaSerieDTO();
        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());
        dto.setCalificacion(entity.getCalificacion());
        dto.setTitulo(entity.getTitulo());
        dto.setFechaCreacion(entity.getFechaCreacion());
        dto.setGenero(entity.getGenero());
        List<PersonajeDTO> dtos = this.personajeMapper.personajeEntityList2DTOList(entity.getPersonajes());//Esta linea y la de abajo hay que anularlas para que devuelva bien los personajes
        dto.setPersonajes(dtos);
        return dto;
    }

    public List<PeliculaSerieDTO> peliculaSerieEntityList2DTOList(List<PeliculaSerieEntity> entities){
        List<PeliculaSerieDTO> dtos = new ArrayList<>();
        for (PeliculaSerieEntity entity: entities){
            dtos.add(this.peliculaSerieEntity2DTO(entity));
        }
        return dtos;
    }

    public List<PeliculaSerieEntity> peliculaSerieDTO2EntityList(List<PeliculaSerieDTO> dtos){
        List<PeliculaSerieEntity> entities = new ArrayList<>();
        for (PeliculaSerieDTO peliculaSerieDTO: dtos){
            entities.add(peliculaSerieDTO2Entity(peliculaSerieDTO));
        }
        return entities;
    }
}
