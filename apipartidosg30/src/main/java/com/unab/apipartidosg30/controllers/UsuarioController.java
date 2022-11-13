package com.unab.apipartidosg30.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unab.apipartidosg30.models.dto.UsuarioDto;
import com.unab.apipartidosg30.models.peticiones.UsuarioCrearReqModel;
import com.unab.apipartidosg30.models.respuestas.UsuarioDataResModel;
import com.unab.apipartidosg30.services.IUsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    IUsuarioService iUsuarioService;


    @PostMapping
    public UsuarioDataResModel crearUsuario (@RequestBody UsuarioCrearReqModel usuarioCrearRequestModel){

        UsuarioDto usuarioDto= modelMapper.map(usuarioCrearRequestModel, UsuarioDto.class);

        UsuarioDto usuarioDtoCreado= iUsuarioService.crearUsuario(usuarioDto);

        UsuarioDataResModel usuarioDataRestModel= modelMapper.map(usuarioDtoCreado, UsuarioDataResModel.class);
         
        return usuarioDataRestModel;
    }

    @GetMapping
    public UsuarioDataResModel leerUsuario(){

        String username= "krodri";

        UsuarioDto usuarioDto= iUsuarioService.leerUsuario(username);

        UsuarioDataResModel usuarioDataRestModel= modelMapper.map(usuarioDto, UsuarioDataResModel.class);

        return usuarioDataRestModel;
    }
}
    