package com.biblioteca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menuprincipal")
public class Menuprincipal {
@RequestMapping("/pagina")
	public String index() {
		return "index";
	}
}
