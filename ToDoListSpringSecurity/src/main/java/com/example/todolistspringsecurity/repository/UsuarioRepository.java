package com.example.todolistspringsecurity.repository;

import com.example.todolistspringsecurity.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    UserDetails findByLogin(String login);

    boolean existsByLogin(String login);
}
