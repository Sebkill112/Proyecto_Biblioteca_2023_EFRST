package com.biblioteca.entity;

import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cod_usuario")
	private Integer codigo;
	@Column(name="nombre")
	private String nombre;
	@Column(name="apellido")
	private String apellido;
	@Column(name="dni")
	private String dni;
	@Column(name="direccion")
	private String direccion;
	@Column(name="fecha_nac")
	private LocalDate fecha;
	@Column(name="correo")
	private String correo;
	@Column(name="clave")
	private String clave;
	
	//BI-DIRECCIONAL
	//MUCHOS A UNO
	@ManyToOne
	@JoinColumn(name = "cod_rol")
	private Rol tipoRol;// "tipoRol"---> ASOCIACION ENTRE ENTIDADES
	
	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
	private List<Prestamo> listaPrestamos;

	public List<Prestamo> getListaPrestamos() {
		return listaPrestamos;
	}

	public void setListaPrestamos(List<Prestamo> listaPrestamos) {
		this.listaPrestamos = listaPrestamos;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Rol getTipoRol() {
		return tipoRol;
	}

	public void setTipoRol(Rol tipoRol) {
		this.tipoRol = tipoRol;
	}
	
	
}
