package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.GeneroDTO;
import com.alkemy.disney.entity.GeneroEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GeneroMapper {

    public GeneroEntity generoDTO2Entity(GeneroDTO dto){
        GeneroEntity generoEntity = new GeneroEntity();
        generoEntity.setImagen(dto.getImagen());
        generoEntity.setNombre(dto.getNombre());

        return generoEntity;
    }

    public GeneroDTO generoEntity2DTO(GeneroEntity entity){
        GeneroDTO dto = new GeneroDTO();
        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());
        dto.setNombre(entity.getNombre());
        return dto;
    }

    public List<GeneroDTO> generoEntityList2DTOList(List<GeneroEntity> entities){
        List<GeneroDTO> dtos = new ArrayList<>();
        for (GeneroEntity entity: entities){
            dtos.add(this.generoEntity2DTO(entity));
        }
        return dtos;
    }
}
