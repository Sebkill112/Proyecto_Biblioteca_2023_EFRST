package com.biblioteca.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.biblioteca.entity.Usuario;
import com.biblioteca.service.UsuarioService;


@Service
public class Security implements UserDetailsService{

	@Autowired
	private UsuarioService servicio;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserDetails obj= null;
		
		Usuario bean = servicio.loginUsuario(username);
		
		//rol del usuario
		
		Set<GrantedAuthority> rol = new HashSet<GrantedAuthority>();
		//adicionar dentro del objeto rol en rol del usuario actual
		
		rol.add(new SimpleGrantedAuthority(bean.getTipoRol().getDescripcion()));
		//crear obj
		obj= new User(username,bean.getClave(),rol);
		
		
		return obj;
	}

}
