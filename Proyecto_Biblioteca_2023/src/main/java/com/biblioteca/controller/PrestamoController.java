package com.biblioteca.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biblioteca.entity.DetallePrestamo;
import com.biblioteca.entity.Editorial;
import com.biblioteca.entity.Genero;
import com.biblioteca.entity.Libro;
import com.biblioteca.service.EditorialService;
import com.biblioteca.service.GeneroService;
import com.biblioteca.service.LibroService;
import com.biblioteca.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/prestamo")
public class PrestamoController {

	
	@Autowired
	private UsuarioService serUsuario;
	
	@Autowired
	private LibroService serLibro;
	
	@Autowired
	private GeneroService serGenero;
	
	@Autowired
	private EditorialService serEditorial;
	
	@RequestMapping("/registro")
	public String lista(Model model) {
		model.addAttribute("listaLibro", serLibro.ListarTodos());
		model.addAttribute("listaGenero", serGenero.ListarTodos());
		model.addAttribute("listaEditorial", serEditorial.listarTodos());
		return "prestamo";
	}
	
	@RequestMapping("/consultaLibro")
	@ResponseBody
	public List<Libro> consultaLibros(@RequestParam("genero") int gen ,@RequestParam("editorial") int edi ){
		return serLibro.consultarLibrosFiltro(gen,edi);
	}
	
	@RequestMapping("/adicionar")
	@ResponseBody
	public List<DetallePrestamo> adicionar(@RequestParam("cod") int cod, @RequestParam("nom") String nom,
			@RequestParam("edi") String edi, @RequestParam("autor") String autor,@RequestParam("gen") String gen,
			@RequestParam("anio") String anio,HttpSession session) {
		
		List<DetallePrestamo> lista = null;
		
		try {

			if (session.getAttribute("data") == null) // no existe
				// crear arreglo lista
				lista = new ArrayList<DetallePrestamo>();
			else // existe
				// recuperar el atributo data y guardar su contenido en lista
				lista = (List<DetallePrestamo>) session.getAttribute("data");
			DetallePrestamo d = new DetallePrestamo();
		    d.setCodigo(cod);
		    d.setNombre(nom);
		    d.setEditorial(edi);
		    d.setAutor(autor);
		    d.setGenero(gen);
		    d.setAnio(anio);

		    lista.add(d);
			// crear atributo de tipo session data
			session.setAttribute("data", lista);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}
}
