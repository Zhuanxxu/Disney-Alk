package com.alkemy.disney.controller;

import com.alkemy.disney.dto.GeneroDTO;
import com.alkemy.disney.dto.PersonajeDTO;
import com.alkemy.disney.dto.PersonajeDTO;
import com.alkemy.disney.service.PersonajeService;
import com.alkemy.disney.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("personajes")
public class PersonajeController {

    @Autowired
    private PersonajeService personajeService;

    //GET todos los personajes
    @GetMapping
    public ResponseEntity<List<PersonajeDTO>> getAll(){
        List<PersonajeDTO> personajes = personajeService.getAllPersonajes();
        return ResponseEntity.ok().body(personajes);
    }

    //GET 1 personaje por ID
    @GetMapping("/{id}")
    public ResponseEntity<PersonajeDTO> getDetailsById(@PathVariable Long id){
        PersonajeDTO personaje = this.personajeService.getDetailsById(id);

        return ResponseEntity.ok(personaje);
    }

    //GET personajes por filtros
    @GetMapping("/filter")
    public ResponseEntity<List<PersonajeDTO>> listPjeFiltro(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Integer edad,
            @RequestParam(required = false) List<Long> peliculas,
            @RequestParam(required = false, defaultValue = "ASC") String orden) {


        List<PersonajeDTO> dtos = personajeService.busquedaXparametro(nombre, edad, peliculas, orden);
        return ResponseEntity.ok(dtos);
    }

    //POST nuevo personaje
    @PostMapping
    public ResponseEntity<PersonajeDTO> save(@RequestBody PersonajeDTO personaje){

        PersonajeDTO personajeGuardado = personajeService.save(personaje);

        return ResponseEntity.status(HttpStatus.CREATED).body(personajeGuardado);
    }

    //DELETE borrar personaje
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.personajeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //PUT editar personaje
    @PutMapping("/{id}")
    public ResponseEntity<PersonajeDTO> editar(@PathVariable Long id, @RequestBody PersonajeDTO dto) {

        PersonajeDTO dtoFull = personajeService.editar(id, dto);
        return ResponseEntity.ok().body(dtoFull);
    }
}
