package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.PeliculaSerieDTO;
import com.alkemy.disney.dto.PersonajeDTO;
import com.alkemy.disney.entity.PeliculaSerieEntity;
import com.alkemy.disney.entity.PersonajeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonajeMapper {

    @Autowired
    private PeliculaSerieMapper peliculaSerieMapper;

    public PersonajeEntity personajeDTO2Entity(PersonajeDTO dto,boolean loadPeliculas){
        PersonajeEntity personajeEntity = new PersonajeEntity();
        personajeEntity.setImagen(dto.getImagen());
        personajeEntity.setNombre(dto.getNombre());
        personajeEntity.setPeso(dto.getPeso());
        personajeEntity.setHistoria(dto.getHistoria());
        personajeEntity.setEdad(dto.getEdad());
        if(loadPeliculas) {
            List<PeliculaSerieEntity> listaPelis = this.peliculaSerieMapper.peliculaSerieDTO2EntityList(dto.getPeliculasSeries(), false);
            personajeEntity.setPeliculasSeries(listaPelis);
        }
        return personajeEntity;
    }

    public PersonajeDTO personajeEntity2DTO(PersonajeEntity entity, boolean loadPeliculas){
        PersonajeDTO dto = new PersonajeDTO();
        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());
        dto.setNombre(entity.getNombre());
        dto.setPeso(entity.getPeso());
        dto.setHistoria(entity.getHistoria());
        dto.setEdad(entity.getEdad());
        if(loadPeliculas) {
            List<PeliculaSerieDTO> listaPelis = this.peliculaSerieMapper.peliculaSerieEntityList2DTOList(entity.getPeliculasSeries(), false);
            dto.setPeliculasSeries(listaPelis);
        }
        return dto;
    }
    
    //TRANSFORMACION LISTAS
    public List<PersonajeDTO> personajeEntityList2DTOList(List<PersonajeEntity> entities, boolean loadPeliculas){
        List<PersonajeDTO> dtos = new ArrayList<>();
        for (PersonajeEntity entity: entities){
            dtos.add(this.personajeEntity2DTO(entity,loadPeliculas));
        }
        return dtos;
    }

    public List<PersonajeEntity> personajeDTOList2EntityList(List<PersonajeDTO> dtos, boolean loadPeliculas){
        List<PersonajeEntity> entities = new ArrayList<>();
        for (PersonajeDTO dto: dtos){
            entities.add(this.personajeDTO2Entity(dto,loadPeliculas));
        }
        return entities;
    }
}
