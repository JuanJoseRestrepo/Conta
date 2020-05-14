package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.AdministradorCuentas;



public class FileManager {
	/**
	 ** this method allows get a EPS class from a binary file
	 ** @param the name of the path with the binary file. path != null
	 ** @return a EPS class with all the data
	 **/
	public AdministradorCuentas loadMarketData(String path){
		
		AdministradorCuentas groce = null;
		File resourse = new File(path);
		if(resourse.exists()) {
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(resourse));
				groce = (AdministradorCuentas)ois.readObject();
				ois.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return groce;
	}
	/**
	 ** this method allows serialize the class EPS
	 ** @param the name of the path. nameOfPath != null
	 ** @param the Market to serialize market != null
	 ** <b>post:</b>a binary file was created</br>
	 **/
	public void saveMarketData(String nameOfPath, AdministradorCuentas market) throws FileNotFoundException, IOException{
		ObjectOutputStream lop = new ObjectOutputStream(new FileOutputStream(nameOfPath));
	
		lop.writeObject(market);
		lop.close();
	}
}