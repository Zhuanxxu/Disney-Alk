package com.alkemy.disney.repository.specifications;

import com.alkemy.disney.dto.PersonajeFilterDTO;
import com.alkemy.disney.entity.PeliculaSerieEntity;
import com.alkemy.disney.entity.PersonajeEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
@Component
public class PersonajeSpecification {

    public Specification<PersonajeEntity> getByFilters(PersonajeFilterDTO filtrosDto){
        return (root, query, criteriaBuilder) ->{


            List<Predicate> predicates= new ArrayList<>();

            if(StringUtils.hasLength(filtrosDto.getNombre())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("nombre")),
                                "%" +filtrosDto.getNombre().toLowerCase() +"%"
                        )
                );
            }/*Creo el primer filtro si tiene contenido el parametro nombre*/


            if(filtrosDto.getEdad()!= null){
                predicates.add(
                        criteriaBuilder.equal( (root.get("edad")),filtrosDto.getEdad())
                );

            }

            if(!CollectionUtils.isEmpty(filtrosDto.getPeliculas())){
                Join<PeliculaSerieEntity, PersonajeEntity> join= root.join("peliculasSeries", JoinType.INNER);
                Expression<String> peliculasId= join.get("Id");
                predicates.add(peliculasId.in(filtrosDto.getPeliculas()));
            };
            query.distinct(true);

            String orderByField="nombre";
            query.orderBy(
                    filtrosDto.isASC()?
                            criteriaBuilder.asc(root.get(orderByField)):
                            criteriaBuilder.desc(root.get(orderByField))
            );


            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

    }
}
