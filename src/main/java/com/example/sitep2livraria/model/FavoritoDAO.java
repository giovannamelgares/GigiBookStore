package com.example.sitep2livraria.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class FavoritoDAO {
     @Autowired
    DataSource dataSource;

    JdbcTemplate jdbc;

    @PostConstruct
    private void initialize(){
        jdbc = new JdbcTemplate(dataSource);
    }

    public void favoritar(String usuarioId, String livroId){
        String sql = "INSERT INTO favorito(usuarioid, livroid) VALUES (?, ?)";
        Object[] obj = new Object[2];
        obj[0] = UUID.fromString(usuarioId);
        obj[1] = UUID.fromString(livroId);
        jdbc.update(sql, obj);
    }

    public List<Favorito> listarFavoritos(){
        String sql = "SELECT * FROM favorito";
        List<Map<String,Object>> registros = jdbc.queryForList(sql);
        List<Favorito> favoritos = new ArrayList<>();
        for(Map<String,Object> reg : registros){
            favoritos.add(Favorito.converter(reg));
        }
        return favoritos;
    }

    public List<Livro> listarLivrosFavoritos(String usuarioId){
        String sql = "SELECT l.* FROM favorito f JOIN livro l ON f.livroid = l.id WHERE f.usuarioid = ?";
        List<Map<String,Object>> registros = jdbc.queryForList(sql,UUID.fromString(usuarioId));
        List<Livro> livros = new ArrayList<>();
        for(Map<String,Object> reg : registros){
            livros.add(Livro.converter(reg));
        }
        return livros;
}
}
