package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class RegistroController implements Initializable,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@FXML
	private TextField empresa;
	@FXML
	private DatePicker inicio;
	@FXML
	private DatePicker fin;
	@FXML
	private Button guardar;
	
	void init() {
		guardar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				String nombre = empresa.getText();
				LocalDate i = inicio.getValue();
				LocalDate f = fin.getValue();
				
				if (nombre.equals("") || i == null || f == null ) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Entrada no valida");
					alert.setHeaderText(null);
					alert.setContentText("Los campos no deben estar vacios");
					alert.showAndWait();
				}else {
					Main.getAc().setEmpresa(nombre);
					Main.getAc().setInicio(i.toString());
					Main.getAc().setFin(f.toString());
				}
				Parent root;
				try {
					root = FXMLLoader.load(getClass().getResource("/application/balance.fxml"));
					Scene scene = new Scene(root);
					Stage stage = (Stage) ((Node) t.getSource()).getScene().getWindow();
					root.getStylesheets().add("/application/application.css");
					stage.getIcons().add(new Image("/application/horseHipodromo.jpg"));
					stage.setScene(scene);
					stage.centerOnScreen();
					stage.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
				

			}
		});
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		init();
		
	}
	
	
	

}
