package com.example.sitep2livraria.model;

import java.util.Map;
import java.util.UUID;

public class Favorito {
    private String usuarioId;
    private String livroId;

    public Favorito(String usuarioId, String livroId){
        this.usuarioId = usuarioId;
        this.livroId = livroId;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getLivroId() {
        return livroId;
    }

    public void setLivroId(String livroId) {
        this.livroId = livroId;
    }

    public static Favorito converter(Map<String,Object> registro){

        UUID usuarioId = (UUID) registro.get("usuarioid");
        UUID livroId = (UUID) registro.get("livroid");

        return new Favorito(
            usuarioId.toString(),
            livroId.toString()
        );
    }
}
