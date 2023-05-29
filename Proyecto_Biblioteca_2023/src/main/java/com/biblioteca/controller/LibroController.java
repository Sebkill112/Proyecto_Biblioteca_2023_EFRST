package com.biblioteca.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.biblioteca.entity.Editorial;
import com.biblioteca.entity.Genero;
import com.biblioteca.entity.Libro;
import com.biblioteca.entity.Rol;
import com.biblioteca.entity.Usuario;
import com.biblioteca.service.EditorialService;
import com.biblioteca.service.GeneroService;
import com.biblioteca.service.LibroService;

@Controller
@RequestMapping("/libro")
public class LibroController {

	// TEST ENVIO GIT 
	
	@Autowired
	private LibroService serLibro;
	
	@Autowired
	private EditorialService serEditorial;
	
	@Autowired
	private GeneroService serGenero;
	
	
	@RequestMapping("/lista")
	private String lista(Model model) {
		model.addAttribute("libros", serLibro.ListarTodos());
		model.addAttribute("editorial", serEditorial.listarTodos());
		model.addAttribute("genero", serGenero.ListarTodos());
		
		return "libro";
	}
	
	@RequestMapping("/grabar")
	public String grabar( 
			@RequestParam("codigo") Integer codigo,
			@RequestParam("nombre") String nom,
			@RequestParam("editorial") int edit,
			@RequestParam("autor") String aut,
			@RequestParam("genero") int gen,
			@RequestParam("anio") String ani,
			@RequestParam("stock") int stock,
			RedirectAttributes redirect
			) {
		try {
			//
			Libro l = new Libro();
			//
			l.setNombre(nom);
			
			Editorial e = new Editorial();
			e.setCodigo(edit);
			
			l.setEditorial(e);
			l.setAutor(aut);
			
			Genero g = new Genero();
			g.setCodigo(gen);
			
			l.setGenero(g);
			l.setEdicion(ani);
			l.setStock(stock);
			
			if(codigo==0) {
				serLibro.AgregarLibro(l);
				//
				redirect.addFlashAttribute("MENSAJE","Libro registrado");
			}
			else {
				l.setCodigo(codigo);
				serLibro.ActualizarLibro(l);
				//
				redirect.addFlashAttribute("MENSAJE","Libro actualizado");
			}			
		} catch (Exception e) {
			redirect.addFlashAttribute("MENSAJE","Error en el registrar");
			e.printStackTrace();
		}
		
		return "redirect:/libro/lista";		
	}
	
	@RequestMapping("/buscar")
	@ResponseBody
	private Libro buscarPorCodigo(
			@RequestParam("codigo") Integer cod) {
		return serLibro.BuscarPorID(cod);
	}
	
	@RequestMapping("/eliminar")
	private String eliminarPorCodigo(
			@RequestParam("codigo") Integer cod,
			RedirectAttributes redirect
			) {
		serLibro.EliminarLibro(cod);
		redirect.addFlashAttribute("MENSAJE","Libro eliminado");
		
		return "redirect:/libro/lista";

	}
	
	
	
}
