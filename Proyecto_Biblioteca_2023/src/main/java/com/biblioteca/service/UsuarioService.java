 package com.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.dao.UsuarioRepository;
import com.biblioteca.entity.Enlace;
import com.biblioteca.entity.Usuario;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repo;
	
	public void registrar(Usuario u) {
		repo.save(u);
	}
	
	public void actualizar(Usuario u) {
		repo.save(u);
	}
	
	public void eliminarPorID(Integer cod) {
		repo.deleteById(cod);
	}
	
	public Usuario buscarPorID(Integer cod) {
		return repo.findById(cod).orElse(null);
	}
	
	public List<Usuario> listarTodos() {
		return repo.findAll();
	}
	
	
	public Usuario loginUsuario(String vLogin) {

		return repo.iniciarSesion(vLogin);
	}

	public List<Enlace> enlaceDelUsuario(int rol) {
		
		
		return repo.traerEnlaceDeUsuario(rol);
	}
}
