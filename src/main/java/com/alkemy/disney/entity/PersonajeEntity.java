package com.alkemy.disney.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter @Getter
@Entity
@SQLDelete(sql = "UPDATE personaje SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@Table(name = "personaje")
public class PersonajeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;
    private String imagen;
    private String nombre;
    private int edad;
    private float peso;
    private String historia;

    @ManyToMany(mappedBy = "personajes",cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    private List<PeliculaSerieEntity> peliculasSeries = new ArrayList<>();

    private boolean deleted = Boolean.FALSE;

}
