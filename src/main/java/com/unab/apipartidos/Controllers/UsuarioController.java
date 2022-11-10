package com.unab.apipartidos.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    
    @GetMapping
    public String leerUsuario(){

        return "Fernando";
    }

    @PostMapping
    public String crearUsuario(){
        
        return "Usuario Creado con exito";
    }

}
