package com.unab.apipartidosg30.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.unab.apipartidosg30.models.dto.PartidoDto;
import com.unab.apipartidosg30.models.dto.UsuarioDto;
import com.unab.apipartidosg30.models.entidades.PartidoEntity;
import com.unab.apipartidosg30.models.entidades.UsuarioEntity;
import com.unab.apipartidosg30.repositorios.IPartidoRepository;
import com.unab.apipartidosg30.repositorios.IUsuarioRepository;

@Service
public class UsuarioService implements IUsuarioService{

    @Autowired
    ModelMapper modelMapper;
    
    @Autowired
    IUsuarioRepository iUsuarioRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    IPartidoRepository iPartidoRepository;

    @Override
    public UsuarioDto crearUsuario(UsuarioDto UsuarioDto) {

        if(iUsuarioRepository.findByEmail(UsuarioDto.getEmail()) !=null){
            throw new RuntimeException("Este email ya se encuentra registrado");
        }

        if(iUsuarioRepository.findByUsername(UsuarioDto.getUsername()) !=null){
            throw new RuntimeException("Este nombre de usuario ya se encuentra registrado");
        }

        UsuarioEntity usuarioEntity= modelMapper.map(UsuarioDto, UsuarioEntity.class);
        usuarioEntity.setIdUsuario(UUID.randomUUID().toString());
        usuarioEntity.setPasswordEncriptada(bCryptPasswordEncoder.encode(UsuarioDto.getPassword()));

        UsuarioEntity usuarioEntityCreado= iUsuarioRepository.save(usuarioEntity);

        UsuarioDto usuarioDtoCreado= modelMapper.map(usuarioEntityCreado, UsuarioDto.class);

        return usuarioDtoCreado;
    }


    @Override
    public UsuarioDto leerUsuario(String username) {
        
        UsuarioEntity usuarioEntity= iUsuarioRepository.findByUsername(username);

        if(usuarioEntity==null){
            throw new UsernameNotFoundException(username);
        }

        UsuarioDto usuarioDto= modelMapper.map(usuarioEntity, UsuarioDto.class);

        return usuarioDto;
    }


    @Override
    public List<PartidoDto> leerMispartidos(String username) {
        
        UsuarioEntity usuarioEntity= iUsuarioRepository.findByUsername(username);
        
        List<PartidoEntity> partidoEntityList= iPartidoRepository.getByUsuarioEntityIdOrderByCreadoDesc(usuarioEntity.getId());

        List<PartidoDto> partidoDtoList= new ArrayList<>();

        for (PartidoEntity partidoEntity : partidoEntityList) {
            PartidoDto partidoDto= modelMapper.map(partidoEntity, PartidoDto.class);
            partidoDtoList.add(partidoDto);
        }

        return partidoDtoList;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    
        UsuarioEntity usuarioEntity= iUsuarioRepository.findByUsername(username);

        if(usuarioEntity==null){
            throw new UsernameNotFoundException(username);
        }

        User usuario= new User(usuarioEntity.getUsername(), usuarioEntity.getPasswordEncriptada(), new ArrayList<>());

        return usuario;
    }
    
}
