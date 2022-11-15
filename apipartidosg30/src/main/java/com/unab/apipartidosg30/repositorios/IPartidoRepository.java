package com.unab.apipartidosg30.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.unab.apipartidosg30.models.entidades.PartidoEntity;

@Repository
public interface IPartidoRepository extends PagingAndSortingRepository <PartidoEntity, Long>{
    
    List<PartidoEntity> getByUsuarioEntityIdOrderByCreadoDesc(long UsuarioEntityId); 

    @Query(nativeQuery = true, value = "SELECT * FROM partido ORDER BY creado DESC LIMIT 10")
    List<PartidoEntity> partidosCreados();

    PartidoEntity findByIdPartido(String idPartido);
}
