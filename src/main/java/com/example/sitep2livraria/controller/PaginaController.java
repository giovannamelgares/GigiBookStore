package com.example.sitep2livraria.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.sitep2livraria.model.Livro;
import com.example.sitep2livraria.model.LivroService;

@Controller
public class PaginaController {
    @Autowired
    private ApplicationContext context;

    @GetMapping("/")
    public String index(Model model){
        LivroService ls = context.getBean(LivroService.class);
        ArrayList<Livro> livros = (ArrayList<Livro>) ls.listarLivros();
        model.addAttribute("livros", livros.stream().limit(4).toList());
        return "index";
    }

    @GetMapping("/cadastro")
    public String formLivro(Model model) {
        model.addAttribute("livro", new Livro());
        return "cadastro-livro";
    }

    @PostMapping("/livro")
    public String postLivro(@ModelAttribute Livro livro, Model model) {
        LivroService ls = context.getBean(LivroService.class);
        ls.inserirLivro(livro);
        return "sucesso";
    }

    @GetMapping("/livros")
    public String listarLivros(Model model){
        LivroService ls = context.getBean(LivroService.class);
        ArrayList<Livro> livros = (ArrayList<Livro>) ls.listarLivros();
        model.addAttribute("livros", livros);
        return "lista-livros";
    }

    @GetMapping("/livro/{id}")
    public String verLivro(@PathVariable String id, Model model){
        LivroService ls = context.getBean(LivroService.class);
        Livro livro = ls.mostrarLivro(id);
        model.addAttribute("livro", livro);
        return "detalhe-livro";
    }
}
