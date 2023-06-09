package com.biblioteca.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biblioteca.entity.Devolucion;
import com.biblioteca.service.LibroService;
import com.biblioteca.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/devolucion")
public class DevolucionController {

	
	@Autowired
	private UsuarioService serUsuario;
	
	@Autowired
	private LibroService serLibro;
	
	
	@RequestMapping("/lista")
	public String lista(Model model) {
		model.addAttribute("listaLibro", serLibro.ListarTodos());
		return "devoluciones";
	}
	
	
	
	
	
	@RequestMapping("/eliminar")
	@ResponseBody
	public List<Devolucion> eliminar(@RequestParam("cod") int cod,HttpSession session) {

		List<Devolucion> lista = (List<Devolucion>) session.getAttribute("data");

		for (Devolucion d : lista) {
			if (d.getCodigo() == cod) {
				lista.remove(d);
				break;
			}
		}

		return lista;
	}
}
