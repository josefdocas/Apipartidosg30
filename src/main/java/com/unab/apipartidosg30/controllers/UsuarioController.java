package com.unab.apipartidosg30.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unab.apipartidosg30.models.dto.PartidoDto;
import com.unab.apipartidosg30.models.dto.UsuarioDto;
import com.unab.apipartidosg30.models.peticiones.UsuarioCrearReqModel;
import com.unab.apipartidosg30.models.respuestas.PartidoDataResModel;
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

    @GetMapping(path = "/mispartidos")
    public List<PartidoDataResModel> leerMisPartidos() {
        
        String username= "krodri";

        List<PartidoDto> partidoDtoList= iUsuarioService.leerMispartidos(username);

        List<PartidoDataResModel> partidoDataResModelList= new ArrayList<>();

        for (PartidoDto partidoDto : partidoDtoList) {
            PartidoDataResModel partidoDataResModel= modelMapper.map(partidoDto, PartidoDataResModel.class);
            if(partidoDataResModel.getFecha().compareTo(new Date(System.currentTimeMillis())) < 0){
                partidoDataResModel.setJugado(true);
            }
            partidoDataResModelList.add(partidoDataResModel);            
        }

        return partidoDataResModelList;
    }
}
    