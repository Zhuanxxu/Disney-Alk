package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.PeliculaSerieDTO;
import com.alkemy.disney.dto.PersonajeDTO;
import com.alkemy.disney.entity.GeneroEntity;
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
        GeneroEntity genero = new GeneroEntity();
        genero.setId(dto.getGenero());
        peliculaSerieEntity.setGenero(genero);
        peliculaSerieEntity.setPersonajes(personajeMapper.personajeDTOList2EntityList(dto.getPersonajes(),false));


        return peliculaSerieEntity;
    }

    public PeliculaSerieDTO peliculaSerieEntity2DTO(PeliculaSerieEntity entity, boolean loadPersonaje){
        PeliculaSerieDTO dto = new PeliculaSerieDTO();
        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());
        dto.setCalificacion(entity.getCalificacion());
        dto.setTitulo(entity.getTitulo());
        dto.setFechaCreacion(entity.getFechaCreacion());

        dto.setGenero(entity.getGenero().getId());
        if (loadPersonaje) {
            List<PersonajeDTO> dtos = this.personajeMapper.personajeEntityList2DTOList(entity.getPersonajes(), false);
            dto.setPersonajes(dtos);
        }
        return dto;
    }
    public void refreshPeliculaSerieEntityWithDTO(PeliculaSerieEntity entity, PeliculaSerieDTO dto){
        entity.setImagen(dto.getImagen());
        entity.setCalificacion(dto.getCalificacion());
        entity.setFechaCreacion(dto.getFechaCreacion());
        entity.setTitulo(dto.getTitulo());
        GeneroEntity genero = new GeneroEntity();
        genero.setId(dto.getGenero());
        entity.setGenero(genero);
    }

    public List<PeliculaSerieDTO> peliculaSerieEntityList2DTOList(List<PeliculaSerieEntity> entities, boolean loadPersonaje){
        List<PeliculaSerieDTO> dtos = new ArrayList<>();
        for (PeliculaSerieEntity entity: entities){
            dtos.add(this.peliculaSerieEntity2DTO(entity, loadPersonaje));
        }
        return dtos;
    }

    public List<PeliculaSerieEntity> peliculaSerieDTO2EntityList(List<PeliculaSerieDTO> dtos,boolean loadPersonajes){
        List<PeliculaSerieEntity> entities = new ArrayList<>();
        for (PeliculaSerieDTO peliculaSerieDTO: dtos){
            entities.add(peliculaSerieDTO2Entity(peliculaSerieDTO));
        }
        return entities;
    }
}
