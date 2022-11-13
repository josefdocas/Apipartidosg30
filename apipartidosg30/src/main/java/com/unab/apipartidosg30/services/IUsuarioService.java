package com.unab.apipartidosg30.services;

import com.unab.apipartidosg30.models.dto.UsuarioDto;

public interface IUsuarioService {
    
    UsuarioDto crearUsuario(UsuarioDto UsuarioDto);
    UsuarioDto leerUsuario(String username);
}