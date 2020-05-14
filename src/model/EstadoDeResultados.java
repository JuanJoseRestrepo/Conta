package model;

import java.io.Serializable;
import java.util.ArrayList;
import exceptions.NotFoundIngresosGastos;

public class EstadoDeResultados implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<Cuenta> ingresosOperacionales;
	
	private ArrayList<Cuenta> ingresosNoOperacionales;
	
	private ArrayList<Cuenta> gastosOperacionales;
	
	private ArrayList<Cuenta> gastosNoOperacionales;
	
	
	private Cuenta utilidadBruta;
	
	private Cuenta utilidadNeta;
	
	private Cuenta costoDeVentas;
	
	private Cuenta VentasNetas;
	
	public EstadoDeResultados() {
		ingresosOperacionales = new ArrayList<Cuenta>();
		ingresosNoOperacionales = new ArrayList<Cuenta>();
		gastosOperacionales = new ArrayList<Cuenta>();
		gastosNoOperacionales = new ArrayList<Cuenta>();
		
		this.utilidadBruta = new Cuenta("Utilidad Bruta",3705, "Utilidad", 0);
		this.utilidadNeta = new Cuenta("Utilidad Neta",3605, "Utilidad", 0);
		this.costoDeVentas = new Cuenta("Costo de Ventas",6, "Costo de ventas", 0);
	}
	
	public Cuenta getVentasNetas() {
		return VentasNetas;
	}

	public void setVentasNetas(Cuenta ventasNetas) {
		VentasNetas = ventasNetas;
	}

	public ArrayList<Cuenta> getIngresosOperacionales() {
		return ingresosOperacionales;
	}

	public void setIngresosOperacionales(ArrayList<Cuenta> ingresosOperacionales) {
		this.ingresosOperacionales = ingresosOperacionales;
	}

	public ArrayList<Cuenta> getIngresosNoOperacionales() {
		return ingresosNoOperacionales;
	}

	public void setIngresosNoOperacionales(ArrayList<Cuenta> ingresosNoOperacionales) {
		this.ingresosNoOperacionales = ingresosNoOperacionales;
	}

	public ArrayList<Cuenta> getGastosOperacionales() {
		return gastosOperacionales;
	}

	public void setGastosOperacionales(ArrayList<Cuenta> gastosOperacionales) {
		this.gastosOperacionales = gastosOperacionales;
	}

	public ArrayList<Cuenta> getGastosNoOperacionales() {
		return gastosNoOperacionales;
	}

	public void setGastosNoOperacionales(ArrayList<Cuenta> gastosNoOperacionales) {
		this.gastosNoOperacionales = gastosNoOperacionales;
	}

	public Cuenta getUtilidadBruta() {
		return utilidadBruta;
	}

	public void setUtilidadBruta(Cuenta utilidadBruta) {
		this.utilidadBruta = utilidadBruta;
	}

	public Cuenta getUtilidadNeta() {
		return utilidadNeta;
	}

	public void setUtilidadNeta(Cuenta utilidadNeta) {
		this.utilidadNeta = utilidadNeta;
	}

	public Cuenta getCostosDeVentas() {
		return costoDeVentas;
	}
	
	public int getValorCostoVentas() {
		return costoDeVentas.getValor_c();
	}

	public void setCostosDeVentas(Cuenta costosDeVentas) {
		this.costoDeVentas = costosDeVentas;
	}
	
	public int calcularIOperacionales() {
		int valor = 0;
		for (int i = 0; i < ingresosOperacionales.size(); i++) {
			valor += ingresosOperacionales.get(i).getValor_c();
		}
		return valor;
	}
	
	
	public int calcularINoOperacionales() {
		int valor = 0;
		for (int i = 0; i < ingresosNoOperacionales.size(); i++) {
			valor += ingresosNoOperacionales.get(i).getValor_c();
		}
		return valor;
	}
	
	public int calcularGOperacionales() {
		int valor = 0;
		for (int i = 0; i < gastosOperacionales.size(); i++) {
			valor += gastosOperacionales.get(i).getValor_c();
		}
		return valor;
	}
	
	public int calcularGNoOperacionales() {
		int valor = 0;
		for (int i = 0; i < gastosNoOperacionales.size(); i++) {
			valor += gastosNoOperacionales.get(i).getValor_c();
		}
		return valor;
	}

	public void setCostoDeVenta(Cuenta costosDeVentas){
		
		setCostosDeVentas(costosDeVentas);
		
	}

	public Cuenta utilidadBruta()  {
		
		Cuenta objeto = null;
		
		
		int m = 0;
		int resultados = 0;
		int m2 = 0;
		
		for(int i = 0; i < ingresosOperacionales.size();i++) {
			m += ingresosOperacionales.get(i).getValor_c();
		}
		
		for(int o = 0; o < ingresosNoOperacionales.size();o++) {
			m += ingresosNoOperacionales.get(o).getValor_c();
		}
		
		for(int u = 0; u < gastosOperacionales.size();u++) {
			m2 += gastosOperacionales.get(u).getValor_c();
		}
		
		int m1 = getCostosDeVentas().getValor_c();
		
		resultados = (m - m1)-m2;
		
		objeto = new Cuenta("Utilidad Bruta",3705,AdministradorCuentas.UTILIDAD,resultados);
		
		
		
		return objeto;
		
	}
	
	public int calcularUtilidadNeta()  {
		Cuenta m = null;
		Cuenta m1 = null;
		int resultados = 0;
		int ingreNop = 0;
		int gastosNop = 0;
		int utilidad = 0;
		
		m1 = utilidadBruta();
		utilidad = m1.getValor_c();
		
		for(int i = 0; i < ingresosNoOperacionales.size();i++) {
			ingreNop += ingresosNoOperacionales.get(i).getCodigo_c();
		}
		
		for(int j = 0; j < gastosNoOperacionales.size();j++) {
			gastosNop += gastosNoOperacionales.get(j).getCodigo_c();
		}
		
		resultados = (utilidad+ingreNop)-gastosNop;
		
		m = new Cuenta("Utilidad Neta",3606,AdministradorCuentas.UTILIDAD,resultados);
		
		setUtilidadNeta(m);
		return resultados;
		
	}
	
	public boolean buscarRepetidos(int cod) {
		boolean t = false;
		
		for(int i = 0; i < ingresosOperacionales.size();i++) {
			
			if(cod == ingresosOperacionales.get(i).getCodigo_c()) {
				t = true;
			}
		}
		
		return t;
	}

}
