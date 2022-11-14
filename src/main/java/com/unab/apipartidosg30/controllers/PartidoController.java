package com.unab.apipartidosg30.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unab.apipartidosg30.models.dto.PartidoDto;
import com.unab.apipartidosg30.models.peticiones.PartidoCrearReqModel;
import com.unab.apipartidosg30.models.respuestas.PartidoDataResModel;
import com.unab.apipartidosg30.services.IPartidoService;

@RestController
@RequestMapping("/partido")
public class PartidoController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    IPartidoService iPartidoService;

    @PostMapping
    public PartidoDataResModel crearPartido(@RequestBody PartidoCrearReqModel partidoCrearReqModel){

        String username= "krodri";

        PartidoDto partidoDto= modelMapper.map(partidoCrearReqModel, PartidoDto.class);
        partidoDto.setUsername(username);
        
        PartidoDto partidoDtoCreado= iPartidoService.crearPartido(partidoDto);

        PartidoDataResModel partidoDataResModel= modelMapper.map(partidoDtoCreado, PartidoDataResModel.class);

        return partidoDataResModel;
    }
    
}
