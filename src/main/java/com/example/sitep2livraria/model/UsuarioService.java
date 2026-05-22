package com.example.sitep2livraria.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    UsuarioDAO udao;

    public void inserirUsuario(Usuario usuario){
        udao.inserirUsuario(usuario);
    }

    public String obterUUID(String email){
        return udao.obterUUID(email);
    }

    public void inserirPerfil(String uuid){
        udao.inserirPerfil(uuid);
    }
}
