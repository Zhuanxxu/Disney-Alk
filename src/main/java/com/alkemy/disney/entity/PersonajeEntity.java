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
    private Integer edad;
    private Long peso;
    private String historia;
    @ManyToMany(mappedBy = "personajes",cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    private List<PeliculaSerieEntity> peliculasSeries = new ArrayList<>();
    private boolean deleted = Boolean.FALSE;

    @Override
    public boolean equals(Object o) {

        if (o ==null)return false;
        if (getClass() != o.getClass()) return false;
        if (!(o instanceof PersonajeEntity)) return false;
        final PersonajeEntity personaje = (PersonajeEntity) o;
        return personaje.Id==this.Id;
    }

    public boolean isDeleted() {
        return deleted;
    }
}
