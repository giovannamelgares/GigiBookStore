package com.example.sitep2livraria.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.sitep2livraria.model.FavoritoDAO;
import com.example.sitep2livraria.model.Livro;
import com.example.sitep2livraria.model.LivroDAO;
import com.example.sitep2livraria.model.LivroService;
import com.example.sitep2livraria.model.Usuario;
import com.example.sitep2livraria.model.UsuarioDAO;
import com.example.sitep2livraria.model.UsuarioService;

@Controller
public class PaginaController {
    @Autowired
    private ApplicationContext context;

    @Autowired
    FavoritoDAO fdao;

    @Autowired
    UsuarioDAO udao;

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

    @GetMapping("/livro/{id}/editar")
    public String formAtualizar(@PathVariable("id") String uuid, Model model) {
        LivroService ls = context.getBean(LivroService.class);
        Livro livroId = ls.mostrarLivro(uuid);
        model.addAttribute("livro", livroId);
        model.addAttribute("id", uuid);
        return "editar-livro";
    }

    @PostMapping("/livro/{id}/editar")
    public String atualizarLivro(@PathVariable("id") String id,
                                 Model model,
                                 @ModelAttribute Livro livro) {
        LivroService ls = context.getBean(LivroService.class);
        ls.atualizarLivro(livro, id);
        return "redirect:/livros";
    }

    @PostMapping("/livro/{id}/deletar")
    public String deletarLivro(@PathVariable("id") String id,
                           Model model) {
    LivroDAO ldao = context.getBean(LivroDAO.class);
    ldao.deletarLivro(id);
    return "redirect:/livros";
    }

    //Usuario!

    @GetMapping("/login")
        public String login(){
        return "login";
    }

    @GetMapping("/cadastro-usuario")
    public String formUsuario(Model model){
        model.addAttribute("usuario", new Usuario());
        return "cadastro-usuario";
    }

    @PostMapping("/cadastro-usuario")
    public String postUsuario(
        @ModelAttribute Usuario usuario){
        UsuarioService us = context.getBean(UsuarioService.class);
        us.inserirUsuario(usuario);
        return "redirect:/login";
    }

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model){
        UsuarioService us =
        context.getBean(UsuarioService.class);
        ArrayList<Usuario> usuarios =
        us.listarUsuariosComDados();
        model.addAttribute("usuarios", usuarios);
        return "lista-usuarios";
    }

    //Favoritos!
    @GetMapping("/favoritar/{id}")
    public String favoritarLivro(
        @PathVariable String id, Authentication auth){
        String email = auth.getName();
        String usuarioId = udao.obterUUID(email);
        fdao.favoritar(usuarioId, id);
        return "redirect:/livros";
    }

    @GetMapping("/favoritos")
    public String favoritos(Authentication auth,Model model){
        String email = auth.getName();
        String usuarioId = udao.obterUUID(email);
        ArrayList<Livro> livros =
            (ArrayList<Livro>)
            fdao.listarLivrosFavoritos(usuarioId);
        model.addAttribute("livros", livros);
        return "favoritos";
    }

    private ArrayList<Usuario> istarUsuariosComDados() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
