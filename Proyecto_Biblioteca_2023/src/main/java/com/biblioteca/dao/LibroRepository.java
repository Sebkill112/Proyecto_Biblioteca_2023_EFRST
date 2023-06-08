package com.biblioteca.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.biblioteca.entity.Libro;

public interface LibroRepository extends JpaRepository<Libro, Integer>{

	@Query("select l from Libro l where l.genero.codigo =?1 and l.editorial.codigo=?2")
	public List<Libro> consultaLibro(int gen, int edi);
	
}
