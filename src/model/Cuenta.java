package model;

import java.io.Serializable;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Cuenta implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre_c;
	private int codigo_c;
	private String estado_m;
	private int valor_c;
	
	

	public Cuenta(String nombre_c, int codigo_c, String estado_m, int valor_c) {
		
		this.nombre_c = nombre_c;
		this.codigo_c = codigo_c;
		this.estado_m = estado_m;
		this.valor_c = valor_c;
	}

	public String getNombre_c() {
		return nombre_c;
	}

	public void setNombre_c(String nombre) {
		this.nombre_c = nombre;
	}

	public int getCodigo_c() {
		return codigo_c;
	}

	public void setCodigo_c(int codigo) {
		this.codigo_c =codigo;
	}
	

	public String getEstado_m() {
		return this.estado_m;
	}
	
	public void setTipo_m(String tipo) {
		this.estado_m =tipo;
	}

	public int getValor_c() {
		return valor_c;
	}

	public void setValor_c(int valor) {
		this.valor_c = valor;
	}
	
	
}
