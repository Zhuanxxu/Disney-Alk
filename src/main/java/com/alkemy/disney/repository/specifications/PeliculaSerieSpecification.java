package com.alkemy.disney.repository.specifications;

import com.alkemy.disney.dto.PeliculaSerieFilterDTO;
import com.alkemy.disney.entity.GeneroEntity;
import com.alkemy.disney.entity.PeliculaSerieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PeliculaSerieSpecification {

    public Specification<PeliculaSerieEntity> getByFilters(PeliculaSerieFilterDTO filtrosDto){
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates =new ArrayList<>();
            if (StringUtils.hasLength(filtrosDto.getNombre())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("titulo")),
                                "%"+filtrosDto.getNombre()+"%"
                        )
                );
            }
            if(filtrosDto.getGeneroId()!= null){
                Join<PeliculaSerieEntity, GeneroEntity> join= root.join("genero", JoinType.INNER);
                Expression<String> peliculasId= join.get("Id");
                predicates.add(
                        peliculasId.in(filtrosDto.getGeneroId())
                );

            }
            /*if(!CollectionUtils.isEmpty(filtrosDto.getPeliculas())){
                Join<PeliculaSerieEntity, PersonajeEntity> join= root.join("peliculasSeries", JoinType.INNER);
                Expression<String> peliculasId= join.get("Id");
                predicates.add(peliculasId.in(filtrosDto.getPeliculas()));
            };*/
            query.distinct(true);

            String orderByField="titulo";
            query.orderBy(
                    filtrosDto.isASC()?
                            criteriaBuilder.asc(root.get(orderByField)):
                            criteriaBuilder.desc(root.get(orderByField))
            );


            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }
}
