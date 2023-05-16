package com.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.dao.RolRepository;
import com.biblioteca.entity.Rol;

@Service
public class RolService {
	@Autowired
	private RolRepository repo;
	
	public List<Rol> listarRoles() {
		return repo.findAll();
	}
	
}
