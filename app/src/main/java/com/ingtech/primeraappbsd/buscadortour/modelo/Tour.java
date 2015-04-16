package com.ingtech.primeraappbsd.buscadortour.modelo;

import java.text.NumberFormat;

public class Tour {
	private int id;
	private String titulo;
	private String descripcion;
	private double precio;
	private String imagen;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo2) {
		this.titulo= titulo2;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion= descripcion;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio= precio;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		return titulo + "\n(" + nf.format(precio) + ")";
	}
}
