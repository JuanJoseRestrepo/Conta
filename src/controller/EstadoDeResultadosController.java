package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import exceptions.NotFoundIngresosGastos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class EstadoDeResultadosController implements Initializable,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@FXML
	private Label ingresosOperacionales;

	@FXML
	private Label ingresosNoOperacionales;

	@FXML
	private Label costoDeVenta;

	@FXML
	private Label utilidadBruta;

	@FXML
	private Label gastosOperacionales;

	@FXML
	private Label gastosNoOperacionales;

	@FXML
	private Label utilidadNeta;
	
	@FXML
	private Button volver;
	

	void init() {

		ingresosOperacionales.setText(String.valueOf(Main.getAc().getEr().calcularIOperacionales()));
		ingresosNoOperacionales.setText(String.valueOf(Main.getAc().getEr().calcularINoOperacionales()));
		costoDeVenta.setText(String.valueOf(Main.getAc().getEr().getValorCostoVentas()));
		gastosOperacionales.setText(String.valueOf(Main.getAc().getEr().calcularGOperacionales()));
		gastosNoOperacionales.setText(String.valueOf(Main.getAc().getEr().calcularGNoOperacionales()));

		utilidadBruta.setText(String.valueOf((Main.getAc().getEr().utilidadBruta()).getValor_c()));
		utilidadNeta.setText(String.valueOf(Main.getAc().getEr().calcularUtilidadNeta()));
		
		volver.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				
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
