package com.unab.apipartidosg30.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.unab.apipartidosg30.models.entidades.UsuarioEntity;

@Repository
public interface IUsuarioRepository extends CrudRepository<UsuarioEntity, Long> {
     
    UsuarioEntity findByEmail(String email); 
    UsuarioEntity findByUsername(String username); 
    
}
