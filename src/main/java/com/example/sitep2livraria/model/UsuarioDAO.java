package com.example.sitep2livraria.model;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class UsuarioDAO {

    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbc;

    @PostConstruct
    private void initialize() {
        jdbc = new JdbcTemplate(dataSource);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public void inserirUsuario(Usuario usuario) {

        UUID uuid = UUID.randomUUID();

        String sql =
            "INSERT INTO usuario(id,nome,email,password) VALUES (?,?,?,?)";

        Object[] obj = new Object[4];

        obj[0] = uuid;
        obj[1] = usuario.getNome();
        obj[2] = usuario.getEmail();

        PasswordEncoder pe = passwordEncoder();
        obj[3] = pe.encode(usuario.getPassword());

        jdbc.update(sql, obj);

        inserirPerfil(uuid.toString());
    }

    public String obterUUID(String email){
        String sql = "SELECT id FROM usuario WHERE email=?";
        Map<String,Object> mp = jdbc.queryForMap(sql,email);

        UUID uuid = (UUID) mp.get("id");

        return uuid.toString();
    }

    public void inserirPerfil(String uuid){

        String sql =
            "INSERT INTO perfil(usuarioid, cargo) VALUES (?, ?)";

        Object[] obj = new Object[2];

        obj[0] = UUID.fromString(uuid);
        obj[1] = "cliente";

        jdbc.update(sql, obj);
    }

    public ArrayList<Usuario> listarUsuariosComDados() {

       String sql = """
        SELECT u.id, u.nome, u.email, p.cargo,
        COUNT(f.livroid) AS total_favoritos
        FROM usuario u
        LEFT JOIN perfil p ON p.usuarioid = u.id
        LEFT JOIN favorito f ON f.usuarioid = u.id
        GROUP BY u.id, p.cargo
    """;

    java.util.List<java.util.Map<String, Object>> registros =
            jdbc.queryForList(sql);

    ArrayList<Usuario> lista = new ArrayList<>();

    for (java.util.Map<String, Object> r : registros) {

        Usuario u = new Usuario();
        u.setId(r.get("id").toString());
        u.setNome((String) r.get("nome"));
        u.setEmail((String) r.get("email"));
        u.setCargo((String) r.get("cargo"));
        u.setTotalFavoritos(((Number) r.get("total_favoritos")).intValue());
        lista.add(u);
    }
    return lista;
}
}