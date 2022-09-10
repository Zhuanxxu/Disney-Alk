package com.alkemy.disney.controller;

import com.alkemy.disney.dto.PeliculaSerieBasicDTO;
import com.alkemy.disney.dto.PeliculaSerieDTO;
import com.alkemy.disney.dto.PeliculaSerieDTO;
import com.alkemy.disney.dto.PersonajeDTO;
import com.alkemy.disney.service.PeliculaSerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("movies")
public class PeliculaSerieController {

    @Autowired
    private PeliculaSerieService peliculaSerieService;

    @GetMapping("/all")
    public ResponseEntity<List<PeliculaSerieDTO>> getAll(){
        System.out.println("hola");
        List<PeliculaSerieDTO> peliculaSeries = peliculaSerieService.getAllPeliculaSerie();
        return ResponseEntity.ok().body(peliculaSeries);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeliculaSerieDTO> getDetailsById(@PathVariable Long id){
        PeliculaSerieDTO peliculaSeriesDTO = this.peliculaSerieService.getDetailsById(id);

        return ResponseEntity.ok(peliculaSeriesDTO);
    }

    @GetMapping
    public ResponseEntity<List<PeliculaSerieBasicDTO>> peliculaFiltro(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String genero,
            @RequestParam(required = false, defaultValue = "ASC") String orden){

        List<PeliculaSerieBasicDTO> dtos = peliculaSerieService.busquedaXparametro(titulo, genero, orden);
        return ResponseEntity.ok(dtos);
    }
    
    @PostMapping
    public ResponseEntity<PeliculaSerieDTO> save(@RequestBody PeliculaSerieDTO peliculaSerie){

        PeliculaSerieDTO peliculaSerieGuardado = peliculaSerieService.save(peliculaSerie);

        return ResponseEntity.status(HttpStatus.CREATED).body(peliculaSerieGuardado);
    }

    @PostMapping("/{id}/characters/{idPj}")
    public ResponseEntity<Void> agregarPj(@PathVariable Long id, @PathVariable Long idPj){

        peliculaSerieService.agregarPje(id, idPj);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}/characters/{idPj}")
    public ResponseEntity<Void> quitarPj(@PathVariable Long id, @PathVariable Long idPj){

        peliculaSerieService.quitarPje(id, idPj);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
