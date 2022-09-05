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

    @GetMapping
    public ResponseEntity<List<PersonajeDTO>> getAll(){
        List<PersonajeDTO> personajes = personajeService.getAllPersonajes();
        return ResponseEntity.ok().body(personajes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonajeDTO> getDetailsById(@PathVariable Long id){
        PersonajeDTO personaje = this.personajeService.getDetailsById(id);

        return ResponseEntity.ok(personaje);
    }

    @PostMapping
    public ResponseEntity<PersonajeDTO> save(@RequestBody PersonajeDTO personaje){

        PersonajeDTO personajeGuardado = personajeService.save(personaje);

        return ResponseEntity.status(HttpStatus.CREATED).body(personajeGuardado);
    }
}
