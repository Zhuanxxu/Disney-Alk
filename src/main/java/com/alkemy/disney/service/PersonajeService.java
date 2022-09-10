package com.alkemy.disney.service;

import com.alkemy.disney.dto.GeneroDTO;
import com.alkemy.disney.dto.PersonajeBasicDTO;
import com.alkemy.disney.dto.PersonajeDTO;

import java.util.List;

public interface PersonajeService {
    PersonajeDTO save(PersonajeDTO dto);

    List<PersonajeDTO> getAllPersonajes();

    PersonajeDTO getDetailsById(Long id);

    void delete(Long id);

    PersonajeDTO editar (Long id, PersonajeDTO dto);

    List<PersonajeBasicDTO> busquedaXparametro(String nombre, Integer edad, List<Long> peliculas, String orden);
}
