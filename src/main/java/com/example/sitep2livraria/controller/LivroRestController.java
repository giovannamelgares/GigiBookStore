package com.example.sitep2livraria.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sitep2livraria.model.Livro;
import com.example.sitep2livraria.model.LivroService;

@RestController
@RequestMapping("/rest")
@CrossOrigin(origins = "*")
public class LivroRestController {
    @Autowired
    private LivroService livroService;

    @GetMapping("/livros")
    public ArrayList<Livro> listarLivros() {
        return livroService.listarLivros();
    }
    
    @GetMapping("/livros/{id}")
    public Livro mostrarLivro(@PathVariable String id) {
        return livroService.mostrarLivro(id);
    }

    @PostMapping("/livros")
    public Livro inserirLivro(@RequestBody Livro livro) {
        livroService.inserirLivro(livro);
        return livro;
    }
}