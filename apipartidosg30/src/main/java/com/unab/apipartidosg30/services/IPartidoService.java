package com.unab.apipartidosg30.services;

import java.util.List;

import com.unab.apipartidosg30.models.dto.PartidoDto;

public interface IPartidoService {
    
    PartidoDto crearPartido(PartidoDto partidoDto);

    List<PartidoDto> leePartidos();

    PartidoDto detallePartido(String idPartido);

    PartidoDto actualizarPartido(String idPartido, PartidoDto partidoDto);

    void eliminarPartido(String idPartido, long usuarioEntityId);

}
