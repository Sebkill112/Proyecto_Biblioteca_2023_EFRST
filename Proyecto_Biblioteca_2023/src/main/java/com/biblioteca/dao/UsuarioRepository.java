package com.biblioteca.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.biblioteca.entity.Enlace;
import com.biblioteca.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	
	@Query("select u from Usuario u where u.correo=?1")
	public Usuario iniciarSesion(String vLogin);
	
	@Query("select e from RolEnlace re join re.enlace e where re.rol.codigo=?1")
	public List<Enlace> traerEnlaceDeUsuario(int codigoRol);
	
}
