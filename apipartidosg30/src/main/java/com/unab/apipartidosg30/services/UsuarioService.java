package com.unab.apipartidosg30.services;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.unab.apipartidosg30.models.dto.UsuarioDto;
import com.unab.apipartidosg30.models.entidades.UsuarioEntity;
import com.unab.apipartidosg30.repositorios.IUsuarioRepository;

@Service
public class UsuarioService implements IUsuarioService{

    @Autowired
    ModelMapper modelMapper;
    
    @Autowired
    IUsuarioRepository iUsuarioRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

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
    
}
