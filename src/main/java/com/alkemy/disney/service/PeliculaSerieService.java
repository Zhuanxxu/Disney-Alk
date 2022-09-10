package com.alkemy.disney.service;

import com.alkemy.disney.dto.PeliculaSerieBasicDTO;
import com.alkemy.disney.dto.PeliculaSerieDTO;
import com.alkemy.disney.dto.PersonajeDTO;

import java.util.List;
import java.util.Set;

public interface PeliculaSerieService {

    PeliculaSerieDTO save(PeliculaSerieDTO dto);
    List<PeliculaSerieDTO> getAllPeliculaSerie();
    PeliculaSerieDTO getDetailsById(Long id);
    PeliculaSerieDTO agregarPje(Long idPeli, Long idPj);
    PeliculaSerieDTO quitarPje(Long idPeli, Long idPj);
    List<PeliculaSerieBasicDTO> busquedaXparametro(String nombre, Long generoId, String orden);


}
