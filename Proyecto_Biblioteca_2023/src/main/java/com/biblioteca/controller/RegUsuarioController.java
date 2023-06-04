package com.biblioteca.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.biblioteca.entity.Rol;
import com.biblioteca.entity.Usuario;
import com.biblioteca.service.UsuarioService;

@Controller
@RequestMapping("/RegUsuario") // NOMBRE DE LA PAGINA
public class RegUsuarioController {
	
	// TRAEMOS EL SERVICE USUARIO
	@Autowired
	private UsuarioService serUsu;
	
	// CARGAR LA PAGINA
	@RequestMapping("/lista")
	private String index(Model model) {
		
		return "RegUsuario";
	}
	
	// CREAMOS EL METODO REGISTRAR USUARIO
	
	@RequestMapping("/grabar")
	public String grabar( 
			@RequestParam("codigo") Integer codUsu,
			@RequestParam("nombre") String nom,
			@RequestParam("apellido") String ape,
			@RequestParam("dni") String dni,
			@RequestParam("direccion") String direc,
			@RequestParam("fecha") String fec,
			@RequestParam("correo") String corrreo,
			@RequestParam("clave") String clave,
			//@RequestParam("tipoRol") int codRol,
			RedirectAttributes redirect
			) {
		try {
			//
			Usuario u = new Usuario();
			//
			u.setNombre(nom);
			u.setApellido(ape);
			u.setDni(dni);
			u.setDireccion(direc);
			u.setFecha(LocalDate.parse(fec));
			u.setCorreo(corrreo);
			u.setClave(clave);
			//
			Rol r = new Rol();
			r.setCodigo(2);
			//
			u.setTipoRol(r);
			//
			serUsu.registrar(u);
			//
			redirect.addFlashAttribute("MENSAJE","Usuario registrado");
			
		} catch (Exception e) {
			redirect.addFlashAttribute("MENSAJE","Error en el registrar");
			e.printStackTrace();
		}
		
		return "redirect:/RegUsuario/lista";
		
	}

}
