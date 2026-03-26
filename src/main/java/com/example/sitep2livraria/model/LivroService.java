package com.example.sitep2livraria.model;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LivroService {
    @Autowired
    LivroDAO livroDAO;

    public void inserirLivro(Livro livro){
        livroDAO.inserirLivro(livro);
    }

    public Livro mostrarLivro(String id){
        return livroDAO.mostrarLivro(id);
    }

    public ArrayList<Livro> listarLivros(){
        return livroDAO.listarLivros();
    }
}
