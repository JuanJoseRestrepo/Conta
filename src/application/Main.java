package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.AdministradorCuentas;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class Main extends Application implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static AdministradorCuentas ac;
	
	private static FileManager file;

	@Override
	public void start(Stage arg0) throws Exception {
		Parent root;
		init();
		if(ac.getEmpresa() == null) {
			root = FXMLLoader.load(getClass().getResource("registro.fxml"));
			
		}else {
			root = FXMLLoader.load(getClass().getResource("balance.fxml"));
		}
		
		root.getStylesheets().add("/application/application.css");
		arg0.getIcons().add(new Image("/application/horseHipodromo.jpg"));
		root.getStyleClass().add("pane");
		arg0.setScene(new Scene(root));
		arg0.setResizable(true);
		arg0.show();
		arg0.setOnCloseRequest(e -> closeProgram());

	}

	public static void main(String[] args) {
		ac = new AdministradorCuentas();
		file = new FileManager();
		launch(args);

	}
	
	public void init(){
		File market = new File("files\\saves.dat");
		if(market.exists()){
			Main.setAc(file.loadMarketData("files\\saves.dat"));
		}
		
	}
	
	public void closeProgram(){
		try {
			file.saveMarketData("files\\saves.dat",Main.getAc());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static AdministradorCuentas getAc() {
		return ac;
	}

	public static void setAc(AdministradorCuentas ac) {
		
		Main.ac = ac;
	}

	

}
