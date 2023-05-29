package com.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.dao.GeneroRepository;
import com.biblioteca.entity.Genero;

@Service
public class GeneroService {

	@Autowired
	private GeneroRepository repo;
	
	public List<Genero> ListarTodos(){
		return repo.findAll();
	}
	
}
