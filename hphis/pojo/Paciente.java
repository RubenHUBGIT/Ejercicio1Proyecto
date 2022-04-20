package com.hphis.pojo;

public class Paciente {
	
	private Integer nhc;
    private String  nombre;
    private String  apellidos;
    private String  centro;
    private String  ubicacion;
    
	public Integer getNhc() {
		return nhc;
	}
	public void setNhc(Integer nhc) {
		this.nhc = nhc;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getCentro() {
		return centro;
	}
	public void setCentro(String centro) {
		this.centro = centro;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

}
