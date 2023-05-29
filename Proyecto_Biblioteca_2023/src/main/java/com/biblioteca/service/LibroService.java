package com.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.dao.LibroRepository;
import com.biblioteca.entity.Libro;

@Service
public class LibroService {

	@Autowired
	private LibroRepository repo;
	
	public void AgregarLibro(Libro l) {
		repo.save(l);
	}
	
	public void ActualizarLibro(Libro l) {
		repo.save(l);
	}
	
	public void EliminarLibro(Integer cod) {
		repo.deleteById(cod);
	}
	
	public Libro BuscarPorID(Integer cod) {
		return repo.findById(cod).orElse(null);
	}
	
	public List<Libro> ListarTodos(){
		return repo.findAll();
	}
	
}
