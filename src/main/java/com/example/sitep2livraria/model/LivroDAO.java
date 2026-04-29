package com.example.sitep2livraria.model;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class LivroDAO {
    @Autowired
    DataSource dataSource;
    
    JdbcTemplate jdbc;
    
    @PostConstruct
    private void initialize() {
        jdbc = new JdbcTemplate(dataSource);
    }

    public void inserirLivro(Livro livro) {
        String sql = "INSERT INTO livro(titulo, autor, genero, paginas, imagem, sinopse) VALUES (?, ?, ?, ?, ?, ?)";
        
        Object[] obj = new Object[6];
        obj[0] = livro.getTitulo();
        obj[1] = livro.getAutor();
        obj[2] = livro.getGenero();
        obj[3] = livro.getPaginas();
        obj[4] = livro.getImagem();
        obj[5] = livro.getSinopse();

        jdbc.update(sql, obj);
    }

    public void atualizarLivro(Livro novo, String uuid){
    String sql = "UPDATE livro " +
            "SET titulo = ?, autor = ?, genero = ?, paginas = ?, imagem = ?, sinopse = ? " +
            "WHERE id = ?::uuid";

    Object[] obj = new Object[7];
    obj[0] = novo.getTitulo();
    obj[1] = novo.getAutor();
    obj[2] = novo.getGenero();
    obj[3] = novo.getPaginas();
    obj[4] = novo.getImagem();
    obj[5] = novo.getSinopse();
    obj[6] = uuid;

    jdbc.update(sql, obj);
}

    public Livro mostrarLivro(String id){
        String sql = "SELECT * FROM livro WHERE id=?::uuid";
        return Livro.converter(jdbc.queryForMap(sql, id));
    }

    public ArrayList<Livro> listarLivros(){
        String sql = "SELECT * FROM livro";
        return Livro.converterTodos(jdbc.queryForList(sql));
    }
}
