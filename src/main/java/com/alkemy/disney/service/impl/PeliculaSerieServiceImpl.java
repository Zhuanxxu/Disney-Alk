package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.PeliculaSerieBasicDTO;
import com.alkemy.disney.dto.PeliculaSerieDTO;
import com.alkemy.disney.dto.PeliculaSerieFilterDTO;
import com.alkemy.disney.dto.PersonajeDTO;
import com.alkemy.disney.entity.PeliculaSerieEntity;
import com.alkemy.disney.entity.PersonajeEntity;
import com.alkemy.disney.excepciones.ParamNotFound;
import com.alkemy.disney.mapper.PeliculaSerieMapper;
import com.alkemy.disney.repository.PeliculaSerieRepository;
import com.alkemy.disney.repository.PersonajeRepository;
import com.alkemy.disney.repository.specifications.PeliculaSerieSpecification;
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
    @Autowired
    private PersonajeRepository personajeRepository;
    @Autowired
    private PeliculaSerieSpecification peliculaSerieSpecification;

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

    public PeliculaSerieDTO agregarPje(Long idPeli, Long idPj){
        Optional<PeliculaSerieEntity> buscar = peliculaSerieRepository.findById(idPeli);
        if(!buscar.isPresent())
            throw new ParamNotFound("Pelicula no encontrada");
        Optional<PersonajeEntity> buscarPje = personajeRepository.findById(idPj);
        if(!buscarPje.isPresent())
            throw new ParamNotFound("Personaje no encontrado");

        PersonajeEntity pjAdd = buscarPje.get();
        PeliculaSerieEntity pelicula = buscar.get();
        pelicula.agregarPje(pjAdd);
        peliculaSerieRepository.save(pelicula);
        PeliculaSerieDTO dto = peliculaSerieMapper.peliculaSerieEntity2DTO(pelicula, true);
        return dto;
    }
    public PeliculaSerieDTO quitarPje(Long idPeli, Long idPj){
        Optional<PeliculaSerieEntity> buscar = peliculaSerieRepository.findById(idPeli);
        if(!buscar.isPresent())
            throw new ParamNotFound("Pelicula no encontrada");
        Optional<PersonajeEntity> buscarPje = personajeRepository.findById(idPj);
        if(!buscarPje.isPresent())
            throw new ParamNotFound("Personaje no encontrado");

        PersonajeEntity pjAdd = buscarPje.get();
        PeliculaSerieEntity pelicula = buscar.get();
        pelicula.quitarPje(pjAdd);
        peliculaSerieRepository.save(pelicula);
        PeliculaSerieDTO dto = peliculaSerieMapper.peliculaSerieEntity2DTO(pelicula, true);
        return dto;
    }

    @Override
    public List<PeliculaSerieBasicDTO> busquedaXparametro(String nombre, String genero, String orden) {
        PeliculaSerieFilterDTO peliFilter = new PeliculaSerieFilterDTO(nombre,genero, orden);
        List<PeliculaSerieEntity> entidades = peliculaSerieRepository.findAll(peliculaSerieSpecification.getByFilters(peliFilter));
        List<PeliculaSerieDTO> dtos = peliculaSerieMapper.peliculaSerieEntityList2DTOList(entidades, true);
        List<PeliculaSerieBasicDTO> dtosBasic = new ArrayList<>();
        for(PeliculaSerieDTO dto: dtos){
            PeliculaSerieBasicDTO dtoBasic = new PeliculaSerieBasicDTO();
            dtoBasic.setFechaCreacion(dto.getFechaCreacion());
            dtoBasic.setTitulo(dto.getTitulo());
            dtoBasic.setImagen(dto.getImagen());
            dtosBasic.add(dtoBasic);
        }

        return dtosBasic;
    }

}
