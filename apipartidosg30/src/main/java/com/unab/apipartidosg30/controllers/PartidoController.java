package com.unab.apipartidosg30.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unab.apipartidosg30.models.dto.PartidoDto;
import com.unab.apipartidosg30.models.dto.UsuarioDto;
import com.unab.apipartidosg30.models.peticiones.PartidoActualizarReqModel;
import com.unab.apipartidosg30.models.peticiones.PartidoCrearReqModel;
import com.unab.apipartidosg30.models.respuestas.MensajeResModel;
import com.unab.apipartidosg30.models.respuestas.PartidoDataResModel;
import com.unab.apipartidosg30.services.IPartidoService;
import com.unab.apipartidosg30.services.IUsuarioService;

@RestController
@RequestMapping("/partido")
public class PartidoController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    IPartidoService iPartidoService;

    @Autowired
    IUsuarioService iUsuarioService;

    @PostMapping
    public PartidoDataResModel crearPartido(@RequestBody PartidoCrearReqModel partidoCrearReqModel){

        String username= "krodri";

        PartidoDto partidoDto= modelMapper.map(partidoCrearReqModel, PartidoDto.class);
        partidoDto.setUsername(username);
        
        PartidoDto partidoDtoCreado= iPartidoService.crearPartido(partidoDto);

        PartidoDataResModel partidoDataResModel= modelMapper.map(partidoDtoCreado, PartidoDataResModel.class);

        return partidoDataResModel;
    }

    @GetMapping
    public List<PartidoDataResModel> leerPartidos(){

        List<PartidoDto> partidoDtoList= iPartidoService.leePartidos();

        List<PartidoDataResModel> partidoDataResModelList= new ArrayList<>();

        for (PartidoDto partidoDto : partidoDtoList) {
            PartidoDataResModel partidoDataResModel= modelMapper.map(partidoDto, PartidoDataResModel.class);
            partidoDataResModelList.add(partidoDataResModel);
        }
        return partidoDataResModelList;
    }

    @GetMapping(path = "/{id}")
    public PartidoDataResModel detallePartido(@PathVariable String id){

        PartidoDto partidoDto= iPartidoService.detallePartido(id);

        PartidoDataResModel partidoDataResModel= modelMapper.map(partidoDto, PartidoDataResModel.class);

        return partidoDataResModel;
    }

    @PutMapping(path = "/{id}")
    public PartidoDataResModel actualizarPartido(@PathVariable String id, 
                                                    @RequestBody PartidoActualizarReqModel partidoActualizarReqModel){
                                                        
    String username ="krodri";

    PartidoDto partidoDto= modelMapper.map(partidoActualizarReqModel, PartidoDto.class);
    partidoDto.setUsername(username);

    PartidoDto partidoDtoActualizado= iPartidoService.actualizarPartido(id, partidoDto);

    PartidoDataResModel partidoDataResModel= modelMapper.map(partidoDtoActualizado, PartidoDataResModel.class);

    return partidoDataResModel;
                                                
    }

    @DeleteMapping(path = "/{id}")
    public MensajeResModel eliminarPartido(@PathVariable String id){

        String username= "krodri";

        UsuarioDto usuarioDto= iUsuarioService.leerUsuario(username);

        iPartidoService.eliminarPartido(id, usuarioDto.getId());

        MensajeResModel mensajeResModel= new MensajeResModel();
        mensajeResModel.setNombre("Eliminar");
        mensajeResModel.setResultado("Partido eliminado con éxito");

        return mensajeResModel;

    }

    
}
