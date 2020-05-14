package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;



public class AdministradorCuentas implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static String ACORRIENTE = "Activo Corriente";
	public final static String ANO_CORRIENTE = "Activo No corriente";
	public final static String PCORRIENTE = "Pasivo Corriente";
	public final static String PNO_CORRIENTE = "Pasivo No corriente";
	public final static String PATRIMONIO ="Patrimonio";
	public final static String GASTO_O = "Gasto Operacional";
	public final static String INGRESO_O = "Ingreso Operacional";
	public final static String GASTO_NO = "Gasto No Operacional";
	public final static String INGRESO_NO= "Ingreso No Operacional";
	public final static String COSTO = "Costo de Ventas";
	public final static String UTILIDAD = "Utilidad";
	
	
	private BalanceGeneral bg;
	
	private EstadoDeResultados er;
	
	private ArrayList<Cuenta> cuentasPersistentes;
	
	private ArrayList<Cuenta> cuentas;
	
	private String inicio;
	
	private String fin;
	
	private String empresa;
	
	public AdministradorCuentas() {
		bg = new BalanceGeneral();
		er = new EstadoDeResultados();
		cuentas = new ArrayList<Cuenta>();
		cuentasPersistentes = new ArrayList<Cuenta>();
		readCounts();
		
		this.inicio = null;
		this.fin = null;
		this.empresa = null;
		
	}
	
	void readCounts() {
		
		File file = new File("files\\Activos.txt");
		if (file.exists()) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String linea;
		        while((linea=br.readLine())!=null) {
		        	String[] write = linea.split(",");
		        	Cuenta cuenta = new Cuenta(write[0],Integer.parseInt(write[1]), write[2], Integer.parseInt(write[3]));
		        	cuentasPersistentes.add(cuenta);
					actualizar();
		        }
				br.close();

			} catch (IOException e) {
				e.getStackTrace();
			}
		}
		
	}
	
	
	public String getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	public String getFin() {
		return fin;
	}

	public void setFin(String fin) {
		this.fin = fin;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	

	public BalanceGeneral getBg() {
		return bg;
	}


	public EstadoDeResultados getEr() {
		return er;
	}
	
	public ArrayList<Cuenta> getCuentas() {
		return cuentas;
	}
	
	public ArrayList<Cuenta> getCuentasP() {
		return cuentasPersistentes;
	}
	
	public void añadirActivosCorrientes() {
		for (int i = 0; i < cuentas.size(); i++) {
			if (cuentas.get(i).getEstado_m().equals(ACORRIENTE) == true) {
				bg.getActivosCorrientes().add(cuentas.get(i));
			}
		}
	}
	
	public void añadirActivosNoCorrientes() {
		for (int i = 0; i < cuentas.size(); i++) {
			if (cuentas.get(i).getEstado_m().equals(ANO_CORRIENTE)== true) {
				bg.getActivosNoCorrientes().add(cuentas.get(i));
			}
		}
	}
	
	public void añadirPasivosCorrientes() {
		for (int i = 0; i < cuentas.size(); i++) {
			if (cuentas.get(i).getEstado_m().equals(PCORRIENTE)== true) {
				bg.getPasivosCorrientes().add(cuentas.get(i));
			}
		}
	}
	
	public void añadirPasivosNoCorrientes() {
		for (int i = 0; i < cuentas.size(); i++) {
			if (cuentas.get(i).getEstado_m().equals(PNO_CORRIENTE)== true) {
				bg.getPasivosNoCorrientes().add(cuentas.get(i));
			}
		}
	}
	
	public void añadirPatrimonio() {
		for (int i = 0; i < cuentas.size(); i++) {
			if (cuentas.get(i).getEstado_m().equals(PATRIMONIO)== true) {
				bg.getCuentasPatrimonio().add(cuentas.get(i));
			}
		}
	}
	
	public void añadirIngresosOperacionales() {
		for (int i = 0; i < cuentas.size(); i++) {
			System.out.println("Entre");
			if (cuentas.get(i).getEstado_m().equals(INGRESO_O)== true) {
				er.getIngresosOperacionales().add(cuentas.get(i));
			}
		}
	}
	
	public void añadirIngresosNoOperacionales() {
		for (int i = 0; i < cuentas.size(); i++) {
			if (cuentas.get(i).getEstado_m().equals(INGRESO_NO)== true) {
				er.getIngresosNoOperacionales().add(cuentas.get(i));
			}
		}
	}
	
	public void añadirGastosOperacionales() {
		for (int i = 0; i < cuentas.size(); i++) {
			if (cuentas.get(i).getEstado_m().equals(GASTO_O)== true) {
				er.getGastosOperacionales().add(cuentas.get(i));
			}
		}
	}
	
	public void añadirGastosNoOperacionales() {
		for (int i = 0; i < cuentas.size(); i++) {
			if (cuentas.get(i).getEstado_m().equals(GASTO_NO)== true) {
				er.getGastosNoOperacionales().add(cuentas.get(i));
			}
		}
	}
	
	public void actualizar() {
		añadirActivosCorrientes();
		añadirActivosNoCorrientes();
		añadirPasivosCorrientes();
		añadirPasivosNoCorrientes();
		añadirPatrimonio();
		añadirIngresosOperacionales();
		añadirIngresosNoOperacionales();
		añadirGastosOperacionales();
		añadirGastosNoOperacionales();
		

		er.calcularUtilidadNeta();
		
	}

	
	

}
