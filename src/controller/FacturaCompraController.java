package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
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
					
					Parent root;
					root = FXMLLoader.load(getClass().getResource("/application/balance.fxml"));
					Scene scene = new Scene(root);
					Stage stage = (Stage) ((Node) t.getSource()).getScene().getWindow();
					root.getStylesheets().add("/application/application.css");
					stage.getIcons().add(new Image("/application/horseHipodromo.jpg"));
					stage.setScene(scene);
					stage.centerOnScreen();
					stage.show();

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
