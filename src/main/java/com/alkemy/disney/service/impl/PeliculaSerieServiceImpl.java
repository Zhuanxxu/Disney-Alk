package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.PeliculaSerieDTO;
import com.alkemy.disney.dto.PersonajeDTO;
import com.alkemy.disney.entity.PeliculaSerieEntity;
import com.alkemy.disney.entity.PersonajeEntity;
import com.alkemy.disney.mapper.PeliculaSerieMapper;
import com.alkemy.disney.repository.PeliculaSerieRepository;
import com.alkemy.disney.service.PeliculaSerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PeliculaSerieServiceImpl implements PeliculaSerieService {

    @Autowired
    private PeliculaSerieMapper peliculaSerieMapper;
    @Autowired
    private PeliculaSerieRepository peliculaSerieRepository;

    public PeliculaSerieDTO save(PeliculaSerieDTO dto){
        PeliculaSerieEntity entity = peliculaSerieMapper.peliculaSerieDTO2Entity(dto);
        PeliculaSerieEntity entitySaved = peliculaSerieRepository.save(entity);
        PeliculaSerieDTO result = peliculaSerieMapper.peliculaSerieEntity2DTO(entitySaved);

        return result;
    }

    public List<PeliculaSerieDTO> getAllPeliculaSerie() {
        List<PeliculaSerieEntity> entities = peliculaSerieRepository.findAll();

        List<PeliculaSerieDTO> dtos = peliculaSerieMapper.peliculaSerieEntityList2DTOList(entities);
        /*for(PeliculaSerieEntity peliculaSerie: entities){
            result.add(peliculaSerieMapper.peliculaSerieEntity2DTO(peliculaSerie));
        }*/
        return dtos;
    }


}
