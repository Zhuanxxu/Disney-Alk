package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.PersonajeDTO;
import com.alkemy.disney.entity.PersonajeEntity;
import com.alkemy.disney.mapper.PeliculaSerieMapper;
import com.alkemy.disney.mapper.PersonajeMapper;
import com.alkemy.disney.mapper.PersonajeMapper;
import com.alkemy.disney.repository.PersonajeRepository;
import com.alkemy.disney.repository.PersonajeRepository;
import com.alkemy.disney.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonajeServiceImpl implements PersonajeService {
    @Autowired
    private PersonajeMapper personajeMapper;
    @Autowired
    private PersonajeRepository personajeRepository;

    @Autowired
    private PeliculaSerieMapper peliculaSerieMapper;

    public PersonajeDTO save(PersonajeDTO dto){
        PersonajeEntity entity = personajeMapper.personajeDTO2Entity(dto);
        PersonajeEntity entitySaved = personajeRepository.save(entity);
        PersonajeDTO result = personajeMapper.personajeEntity2DTO(entitySaved);

        return result;
    }
    public PersonajeDTO getDetailsById(Long id){
        Optional<PersonajeEntity> optPersonaje = personajeRepository.findById(id);

        PersonajeEntity personaje = optPersonaje.get();

        PersonajeDTO dto = personajeMapper.personajeEntity2DTO(personaje);

        return dto;
    }

    @Override
    public List<PersonajeDTO> getAllPersonajes() {
        List<PersonajeEntity> entities = personajeRepository.findAll();
        List<PersonajeDTO> result = new ArrayList<>();
        for(PersonajeEntity entity : entities){
            result.add(personajeMapper.personajeEntity2DTO(entity));
        }

        return result;
    }

    public void delete(Long id){
        this.personajeRepository.deleteById(id);
    }

    public PersonajeDTO editar (Long id, PersonajeDTO dto){
        Optional<PersonajeEntity> optPersonaje = personajeRepository.findById(id);
        if (!optPersonaje.isPresent()){

        }
        PersonajeEntity entity = optPersonaje.get();
        entity.setNombre(dto.getNombre());
        entity.setEdad(dto.getEdad());
        entity.setHistoria(dto.getHistoria());
        entity.setPeso(dto.getPeso());
        entity.setImagen(dto.getImagen());
        entity.setPeliculasSeries(peliculaSerieMapper.peliculaSerieDTO2EntityList(dto.getPeliculasSeries()));
        entity.setId(id);

        PersonajeEntity personajeGuardado = personajeRepository.save(entity);

        PersonajeDTO personajeDTO = personajeMapper.personajeEntity2DTO(entity);
        return personajeDTO;
    }


}
