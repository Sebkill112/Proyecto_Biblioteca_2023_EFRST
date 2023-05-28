 package com.biblioteca.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.biblioteca.entity.Rol;
import com.biblioteca.entity.Usuario;
import com.biblioteca.service.RolService;
import com.biblioteca.service.UsuarioService;

@Controller


@RequestMapping("/CrudUsuario")
public class UsuarioController {
	@Autowired
	private UsuarioService serUsu;
	
	@Autowired
	private RolService serRol;
	
	@RequestMapping("/lista")
	private String index(Model model) {
		model.addAttribute("Usuarios", serUsu.listarTodos());
		model.addAttribute("Roles", serRol.listarRoles());
		
		return "CrudUsuario";
	}
	
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
			@RequestParam("tipoRol") int codRol,
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
			r.setCodigo(codRol);
			//
			u.setTipoRol(r);
			
			if(codUsu==0) {
				serUsu.registrar(u);
				//
				redirect.addFlashAttribute("MENSAJE","Usuario registrado");
			}
			else {
				u.setCodigo(codUsu);
				serUsu.actualizar(u);
				//
				redirect.addFlashAttribute("MENSAJE","Usuario actualizado");
			}
			
		} catch (Exception e) {
			redirect.addFlashAttribute("MENSAJE","Error en el registrar");
			e.printStackTrace();
		}
		
		return "redirect:/CrudUsuario/lista";
		
	}
	
	@RequestMapping("/buscar")
	@ResponseBody
	private Usuario buscarPorCodigo(
			@RequestParam("codigo") Integer cod
			) {
		return serUsu.buscarPorID(cod);
	}
	
	@RequestMapping("/eliminar")
	private String eliminarPorCodigo(
			@RequestParam("codigo") Integer cod,
			RedirectAttributes redirect
			) {
		serUsu.eliminarPorID(cod);
		redirect.addFlashAttribute("MENSAJE","Usuario eliminado");
		
		return "redirect:/CrudUsuario/lista";

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
