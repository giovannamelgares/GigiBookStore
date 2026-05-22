package com.example.sitep2livraria.model;

import javax.sql.DataSource;

import java.util.Map;
import java.util.UUID;
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
        String sql =
            "INSERT INTO usuario(nome,email,password) " + "VALUES (?,?,?)";
        Object[] obj = new Object[3];
        obj[0] = usuario.getNome();
        obj[1] = usuario.getEmail();
        PasswordEncoder pe = passwordEncoder();
        obj[2] = pe.encode(usuario.getPassword());
        jdbc.update(sql, obj);
    }

    public String obterUUID(String email){
        String sql = "SELECT id FROM usuario WHERE email=?";
        Map<String,Object> mp = jdbc.queryForMap(sql,email);
        UUID uuid = (UUID) mp.get("id");
        return uuid.toString();
    }

    public void inserirPerfil(String uuid){
        String sql = "INSERT INTO perfil(usuarioid,cargo) " + "VALUES (?,?)";
        Object[] obj = new Object[2];
        obj[0] = uuid;
        obj[1] = "cliente";
        jdbc.update(sql,obj);
    }
}
