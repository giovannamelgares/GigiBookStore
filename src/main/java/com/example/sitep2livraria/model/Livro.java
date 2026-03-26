package com.example.sitep2livraria.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Livro {

    private String id;
    private String titulo;
    private String autor;
    private String genero;
    private Integer paginas;
    private String imagem;
    private String sinopse;

    public Livro() {
    }

    public Livro(String id, String titulo, String autor, String genero, Integer paginas, String imagem, String sinopse){ //Select!
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.paginas = paginas;
        this.imagem = imagem;
        this.sinopse = sinopse;
    }

    public Livro(String titulo, String autor, String genero, Integer paginas, String imagem, String sinopse){ //Insert!
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.paginas = paginas;
        this.imagem = imagem;
        this.sinopse = sinopse;
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getGenero() {
        return genero;
    }

    public Integer getPaginas() {
        return paginas;
    }

    public String getImagem() {
        return imagem;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setPaginas(Integer paginas) {
        this.paginas = paginas;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }


    public static Livro converter(Map<String, Object> registro){
        UUID id = (UUID) registro.get("id");
        String titulo = (String) registro.get("titulo");
        String autor = (String) registro.get("autor");
        String genero = (String) registro.get("genero");
        Integer paginas = (Integer) registro.get("paginas");
        String imagem = (String) registro.get("imagem");
        String sinopse = (String) registro.get("sinopse");

        return new Livro(id.toString(), titulo, autor, genero, paginas, imagem, sinopse);
    }

    public static ArrayList<Livro> converterTodos(List<Map<String, Object>> registros){
        ArrayList<Livro> lista = new ArrayList<>();

        for(Map<String, Object> registro : registros){
            lista.add(converter(registro));
        }
        return lista;
    }
}
