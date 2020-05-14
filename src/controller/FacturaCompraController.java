package controller;

import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.Button;

public class FacturaCompraController implements Initializable,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@FXML
	private TextField abono;

	@FXML
	private TextField codigoP;

	@FXML
	private TextField codigoA;

	@FXML
	private Button guardar;

	void init() {

		guardar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {
				try {
					int codigoPasivo = Integer.parseInt(codigoP.getText());
					int codigoActivo = Integer.parseInt(codigoA.getText());
					int valorNuevo = Integer.parseInt(abono.getText());

					Main.getAc().getBg().buscarPasivosC(codigoPasivo, valorNuevo);
					Main.getAc().getBg().buscarActivosC(codigoActivo, valorNuevo * -1);

				} catch (NumberFormatException e) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Entrada no valida");
					alert.setHeaderText(null);
					alert.setContentText("La entrada de valor fue tipada incorrectamente");
					alert.showAndWait();
				} catch (NullPointerException e) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Entrada no valida");
					alert.setHeaderText(null);
					alert.setContentText("Seleccione un valor");
					alert.showAndWait();
				}

			}
		});

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		init();
	}

}
