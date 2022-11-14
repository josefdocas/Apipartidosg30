package com.unab.apipartidosg30.repositorios;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.unab.apipartidosg30.models.entidades.PartidoEntity;

@Repository
public interface IPartidoRepository extends PagingAndSortingRepository <PartidoEntity, Long>{
    
    List<PartidoEntity> getByUsuarioEntityIdOrderByCreadoDesc(long UsuarioEntityId);
}
