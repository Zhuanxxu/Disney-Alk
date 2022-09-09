package com.alkemy.disney.repository;

import com.alkemy.disney.entity.PeliculaSerieEntity;
import com.alkemy.disney.entity.PersonajeEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PeliculaSerieRepository extends JpaRepository<PeliculaSerieEntity, Long>, JpaSpecificationExecutor<PeliculaSerieEntity> {

    List<PeliculaSerieEntity> findAll(Specification<PeliculaSerieEntity> spec);
}
