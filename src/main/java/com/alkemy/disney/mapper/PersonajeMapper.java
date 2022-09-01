package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.PersonajeDTO;
import com.alkemy.disney.entity.PersonajeEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonajeMapper {

    public PersonajeEntity personajeDTO2Entity(PersonajeDTO dto){
        PersonajeEntity personajeEntity = new PersonajeEntity();
        personajeEntity.setImagen(dto.getImagen());
        personajeEntity.setNombre(dto.getNombre());
        personajeEntity.setPeso(dto.getPeso());
        personajeEntity.setHistoria(dto.getHistoria());
        personajeEntity.setEdad(dto.getEdad());

        return personajeEntity;
    }

    public PersonajeDTO personajeEntity2DTO(PersonajeEntity entity){
        PersonajeDTO dto = new PersonajeDTO();
        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());
        dto.setNombre(entity.getNombre());
        dto.setPeso(entity.getPeso());
        dto.setHistoria(entity.getHistoria());
        dto.setEdad(entity.getEdad());
        return dto;
    }

    public List<PersonajeDTO> personajeEntityList2DTOList(List<PersonajeEntity> entities){
        List<PersonajeDTO> dtos = new ArrayList<>();
        for (PersonajeEntity entity: entities){
            dtos.add(this.personajeEntity2DTO(entity));
        }
        return dtos;
    }
}
