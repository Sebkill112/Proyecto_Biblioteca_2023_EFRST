package com.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.dao.EditorialRepository;
import com.biblioteca.entity.Editorial;

@Service
public class EditorialService {

	@Autowired
	private EditorialRepository repo;
	
	public List<Editorial> listarTodos(){
		return repo.findAll();
	}
	
}
