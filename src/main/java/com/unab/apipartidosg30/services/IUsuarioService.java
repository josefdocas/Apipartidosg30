package com.unab.apipartidosg30.services;

import java.util.List;

import com.unab.apipartidosg30.models.dto.PartidoDto;
import com.unab.apipartidosg30.models.dto.UsuarioDto;

public interface IUsuarioService {
    
    UsuarioDto crearUsuario(UsuarioDto UsuarioDto);
    UsuarioDto leerUsuario(String username);
    List<PartidoDto>leerMispartidos(String username);
}