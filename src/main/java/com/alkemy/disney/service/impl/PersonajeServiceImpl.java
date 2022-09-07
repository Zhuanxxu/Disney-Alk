package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.PersonajeDTO;
import com.alkemy.disney.dto.PersonajeFilterDTO;
import com.alkemy.disney.entity.PersonajeEntity;
import com.alkemy.disney.excepciones.ParamNotFound;
import com.alkemy.disney.mapper.PeliculaSerieMapper;
import com.alkemy.disney.mapper.PersonajeMapper;
import com.alkemy.disney.mapper.PersonajeMapper;
import com.alkemy.disney.repository.PersonajeRepository;
import com.alkemy.disney.repository.PersonajeRepository;
import com.alkemy.disney.repository.specifications.PersonajeSpecification;
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
    private PersonajeSpecification personajeSpecification;
    @Autowired
    private PeliculaSerieMapper peliculaSerieMapper;
    public PersonajeDTO save(PersonajeDTO dto){
        PersonajeEntity entity = personajeMapper.personajeDTO2Entity(dto);
        PersonajeEntity entitySaved = personajeRepository.save(entity);
        PersonajeDTO result = personajeMapper.personajeEntity2DTO(entitySaved, false);

        return result;
    }
    public PersonajeDTO getDetailsById(Long id){
        Optional<PersonajeEntity> optPersonaje = personajeRepository.findById(id);

        PersonajeEntity personaje = optPersonaje.get();

        PersonajeDTO dto = personajeMapper.personajeEntity2DTO(personaje,true);

        return dto;
    }

    @Override
    public List<PersonajeDTO> getAllPersonajes() {
        List<PersonajeEntity> entities = personajeRepository.findAll();
        List<PersonajeDTO> result = new ArrayList<>();
        for(PersonajeEntity entity : entities){
            result.add(personajeMapper.personajeEntity2DTO(entity,true));
        }

        return result;
    }

    public void delete(Long id){
        this.personajeRepository.deleteById(id);
    }

    public PersonajeDTO editar (Long id, PersonajeDTO dto){
        Optional<PersonajeEntity> optPersonaje = personajeRepository.findById(id);
        if (!optPersonaje.isPresent()){
            throw new ParamNotFound("Personaje id no encontrado");
        }
        PersonajeEntity entity = optPersonaje.get();

        entity.setNombre(dto.getNombre());
        entity.setEdad(dto.getEdad());
        entity.setHistoria(dto.getHistoria());
        entity.setPeso(dto.getPeso());
        entity.setImagen(dto.getImagen());
        if (!(dto.getPeliculasSeries()==null || dto.getPeliculasSeries().isEmpty()))
            entity.setPeliculasSeries(peliculaSerieMapper.peliculaSerieDTO2EntityList(dto.getPeliculasSeries()));

        entity.setId(id);

        PersonajeEntity entitySaved = personajeRepository.save(entity);

        PersonajeDTO personajeDTO = personajeMapper.personajeEntity2DTO(entity,false);
        return personajeDTO;
    }

    public List<PersonajeDTO> busquedaXparametro(String nombre, Integer edad,Long peso, Long id,
                                              List<Long> peliculas, String orden){
        PersonajeFilterDTO filtro = new PersonajeFilterDTO(id,nombre,edad, peso,orden , peliculas);
        List<PersonajeEntity> entities = personajeRepository.findAll(this.personajeSpecification.getByFilters(filtro));
        List<PersonajeDTO> dtos = personajeMapper.personajeEntityList2DTOList(entities, true);

        return dtos;

    };


}
