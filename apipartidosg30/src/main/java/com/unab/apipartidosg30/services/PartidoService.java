package com.unab.apipartidosg30.services;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public List<PartidoDto> leePartidos() {

        List<PartidoEntity> partidoEntityList= iPartidoRepository.partidosCreados();
        
        List<PartidoDto> partidoDtoList= new ArrayList<>();

        for(PartidoEntity partidoEntity : partidoEntityList) {
            PartidoDto partidoDto= modelMapper.map(partidoEntity, PartidoDto.class);
            partidoDtoList.add(partidoDto);
        }

        return partidoDtoList;
    }

    @Override
    public PartidoDto detallePartido(String idPartido) {
        
        PartidoEntity partidoEntity= iPartidoRepository.findByIdPartido(idPartido);
        
        PartidoDto partidoDto= modelMapper.map(partidoEntity, PartidoDto.class);
        
        return partidoDto;

    }

    @Override
    public PartidoDto actualizarPartido(String idPartido, PartidoDto partidoDto) {

        PartidoEntity partidoEntity= iPartidoRepository.findByIdPartido(idPartido);

        UsuarioEntity usuarioEntity= iUsuarioRepository.findByUsername(partidoDto.getUsername());

        if(partidoEntity.getUsuarioEntity().getId()!= usuarioEntity.getId()){
            throw new RuntimeException("No se puede realizar esta acción");
        }

        partidoEntity.setGolesLocal(partidoDto.getGolesLocal());
        partidoEntity.setGolesVisitante(partidoDto.getGolesVisitante());

        PartidoEntity partidoEntityActualizado= iPartidoRepository.save(partidoEntity);

        PartidoDto partidoDtoActualizado= modelMapper.map(partidoEntityActualizado, PartidoDto.class);

        return partidoDtoActualizado;
    }

    @Override
    public void eliminarPartido(String idPartido, long usuarioEntityId) {
      
        PartidoEntity partidoEntity= iPartidoRepository.findByIdPartido(idPartido);

        if(partidoEntity.getUsuarioEntity().getId()!= usuarioEntityId){
            throw new RuntimeException("No se puede realizar esta acción");
        }

        iPartidoRepository.delete(partidoEntity);
        
    }
    
}
