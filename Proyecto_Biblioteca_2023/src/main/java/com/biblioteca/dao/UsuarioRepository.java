package com.biblioteca.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

}
