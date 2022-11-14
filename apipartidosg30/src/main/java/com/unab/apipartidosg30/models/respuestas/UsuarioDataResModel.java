package com.unab.apipartidosg30.models.respuestas;

import java.util.List;

public class UsuarioDataResModel {

    private String idUsuario;
    private String nombre;
    private String email;
    private String username;
    private List<PartidoDataResModel> partidoDataResModelList;

    public String getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<PartidoDataResModel> getPartidoDataResModelList() {
        return this.partidoDataResModelList;
    }

    public void setPartidoDataResModelList(List<PartidoDataResModel> partidoDataResModelList) {
        this.partidoDataResModelList = partidoDataResModelList;
    }

}
