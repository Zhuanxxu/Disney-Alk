package com.alkemy.disney.controller;

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
@RequestMapping("peliculasseries")
public class PeliculaSerieController {

    @Autowired
    private PeliculaSerieService peliculaSerieService;

    @GetMapping
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
    
    @PostMapping
    public ResponseEntity<PeliculaSerieDTO> save(@RequestBody PeliculaSerieDTO peliculaSerie){

        PeliculaSerieDTO peliculaSerieGuardado = peliculaSerieService.save(peliculaSerie);

        return ResponseEntity.status(HttpStatus.CREATED).body(peliculaSerieGuardado);
    }


}
