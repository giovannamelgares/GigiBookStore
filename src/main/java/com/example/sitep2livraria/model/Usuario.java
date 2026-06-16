package com.example.sitep2livraria.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Usuario {
    private String id;
    private String nome;
    private String email;
    private String password;

    public Usuario(){
    }

    public Usuario(String id, String nome, String email, String password){ //Select!
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.password = password;
}

    public Usuario(String nome, String email, String password){ //Insert!
        this.nome = nome;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static Usuario converter(Map<String,Object> registro){
        UUID id = (UUID) registro.get("id");
        String nome = (String) registro.get("nome");
        String email = (String) registro.get("email");
        String password = (String) registro.get("password");

        return new Usuario(id.toString(), nome, email, password);
    }

    public static ArrayList<Usuario> converterTodos(List<Map<String,Object>> registros){
        ArrayList<Usuario> aux = new ArrayList<>();

        for(Map<String,Object> registro : registros){
            aux.add(converter(registro));
        }
        return aux;
    }
}


