package com.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.biblioteca.entity.Enlace;
import com.biblioteca.entity.Usuario;
import com.biblioteca.service.UsuarioService;





@SessionAttributes({"datosUsuario","enlaces"})
@Controller
@RequestMapping("/validar")
public class ValidarController {
	 
	@Autowired
	private UsuarioService servicio;
	
	@RequestMapping("usuario")
	public String index() {
		return "Login";
	}
	
	
	@RequestMapping("/intranet")
	public String Intranet(Authentication auth,Model model) {
		
		String nomUsuario=auth.getName();
		Usuario bean = servicio.loginUsuario(nomUsuario);
		
		List<Enlace> lista = servicio.enlaceDelUsuario(bean.getTipoRol().getCodigo());
		
		model.addAttribute("datosUsuario",bean.getApellido()+" "+bean.getNombre());
		model.addAttribute("enlaces",lista);
		return "index";
	}

}
