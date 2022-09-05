package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.PeliculaSerieDTO;
import com.alkemy.disney.dto.PersonajeDTO;
import com.alkemy.disney.entity.PeliculaSerieEntity;
import com.alkemy.disney.entity.PersonajeEntity;
import com.alkemy.disney.excepciones.ParamNotFound;
import com.alkemy.disney.mapper.PeliculaSerieMapper;
import com.alkemy.disney.repository.PeliculaSerieRepository;
import com.alkemy.disney.service.PeliculaSerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PeliculaSerieServiceImpl implements PeliculaSerieService {

    @Autowired
    private PeliculaSerieMapper peliculaSerieMapper;
    @Autowired
    private PeliculaSerieRepository peliculaSerieRepository;

    public PeliculaSerieDTO save(PeliculaSerieDTO dto){
        PeliculaSerieEntity entity = peliculaSerieMapper.peliculaSerieDTO2Entity(dto);
        PeliculaSerieEntity entitySaved = peliculaSerieRepository.save(entity);
        PeliculaSerieDTO result = peliculaSerieMapper.peliculaSerieEntity2DTO(entitySaved, false);

        return result;
    }

    public List<PeliculaSerieDTO> getAllPeliculaSerie() {
        List<PeliculaSerieEntity> entities = peliculaSerieRepository.findAll();

        List<PeliculaSerieDTO> dtos = peliculaSerieMapper.peliculaSerieEntityList2DTOList(entities, true);
        /*for(PeliculaSerieEntity peliculaSerie: entities){
            result.add(peliculaSerieMapper.peliculaSerieEntity2DTO(peliculaSerie));
        }*/
        return dtos;
    }

    public PeliculaSerieDTO getDetailsById(Long id){
        Optional<PeliculaSerieEntity> optPersonaje = peliculaSerieRepository.findById(id);

        PeliculaSerieEntity peliculaSerie = optPersonaje.get();

        PeliculaSerieDTO dto = peliculaSerieMapper.peliculaSerieEntity2DTO(peliculaSerie, true);

        return dto;
    }

    public PeliculaSerieDTO peliculaDetalleId(Long id){
        Optional<PeliculaSerieEntity> optPeli = peliculaSerieRepository.findById(id);
        if(!optPeli.isPresent()){
            throw new ParamNotFound("Pelicula ID no encontrada");
        }
        PeliculaSerieEntity pelicula = optPeli.get();

        PeliculaSerieDTO dto = peliculaSerieMapper.peliculaSerieEntity2DTO(pelicula, true);

        return dto;
    }


}
