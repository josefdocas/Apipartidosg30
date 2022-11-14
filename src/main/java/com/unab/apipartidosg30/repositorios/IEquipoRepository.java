package com.unab.apipartidosg30.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.unab.apipartidosg30.models.entidades.EquipoEntity;

@Repository
public interface IEquipoRepository extends CrudRepository<EquipoEntity, Long>{

    EquipoEntity findById(long id);
    
}
