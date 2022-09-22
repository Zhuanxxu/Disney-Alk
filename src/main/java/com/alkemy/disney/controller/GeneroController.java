package com.alkemy.disney.controller;


import com.alkemy.disney.dto.GeneroDTO;
import com.alkemy.disney.service.GeneroService;
import com.alkemy.disney.service.impl.GeneroServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("genres")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @GetMapping
    public ResponseEntity<List<GeneroDTO>> getAll(){
        List<GeneroDTO> generos = generoService.getAllGeneros();
        return ResponseEntity.ok().body(generos);
    }

    @PostMapping
    public ResponseEntity<GeneroDTO> save(@Valid @RequestBody GeneroDTO genero) throws Exception{

        GeneroDTO generoGuardado = generoService.save(genero);

        return ResponseEntity.status(HttpStatus.CREATED).body(generoGuardado);
    }


}
