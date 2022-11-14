package com.unab.apipartidosg30.services;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unab.apipartidosg30.models.dto.PartidoDto;
import com.unab.apipartidosg30.models.entidades.EquipoEntity;
import com.unab.apipartidosg30.models.entidades.PartidoEntity;
import com.unab.apipartidosg30.models.entidades.UsuarioEntity;
import com.unab.apipartidosg30.repositorios.IEquipoRepository;
import com.unab.apipartidosg30.repositorios.IPartidoRepository;
import com.unab.apipartidosg30.repositorios.IUsuarioRepository;

@Service
public class PartidoService implements IPartidoService{

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    IPartidoRepository iPartidoRepository;

    @Autowired
    IUsuarioRepository iUsuarioRepository;

    @Autowired
    IEquipoRepository iEquipoRepository;

    @Override
    public PartidoDto crearPartido(PartidoDto partidoDto) {

        UsuarioEntity usuarioEntity= iUsuarioRepository.findByUsername(partidoDto.getUsername());

        EquipoEntity equipoEntityLocal= iEquipoRepository.findById(partidoDto.getEquipoLocal());

        EquipoEntity equipoEntityVisitante= iEquipoRepository.findById(partidoDto.getEquipoVisitante());

        PartidoEntity partidoEntity= new PartidoEntity();   
        partidoEntity.setIdPartido(UUID.randomUUID().toString());
        partidoEntity.setFecha(partidoDto.getFecha());
        partidoEntity.setGolesLocal("0");
        partidoEntity.setGolesVisitante("0");
        partidoEntity.setUsuarioEntity(usuarioEntity);
        partidoEntity.setEquipoEntityLocal(equipoEntityLocal);
        partidoEntity.setEquipoEntityVisitante(equipoEntityVisitante);

        PartidoEntity partidoEntityCreado= iPartidoRepository.save(partidoEntity);

        PartidoDto partidoDtoCreado= modelMapper.map(partidoEntityCreado, PartidoDto.class);
        
        return partidoDtoCreado;
    }
    
}
