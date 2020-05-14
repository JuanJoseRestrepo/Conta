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
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ResultadosController implements Initializable,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@FXML
	private Text activosC;
	
	@FXML
	private Text activosNC;
	
	@FXML
	private Text pasivosC;
	
	@FXML
	private Text pasivosNC;
	
	@FXML
	private Text sumaActivos;
	
	@FXML
	private Text sumaPasivos;
	
	@FXML
	private Text sumaPasivosPatrimonio;
	
	@FXML
	private Button volver;
	
	void init() {
		
		if (Main.getAc().getBg().sumaActivosCorrientes() != 0) {
			activosC.setText("" +Main.getAc().getBg().sumaActivosCorrientes());
			activosC.setFill(Color.DARKSLATEGREY);
		}else {
			activosC.setText(""+0);
			activosC.setFill(Color.DARKSLATEGREY);
		}
		
		if (Main.getAc().getBg().sumaActivosNoCorrientes() != 0) {
			activosNC.setText("" +Main.getAc().getBg().sumaActivosNoCorrientes());
			activosNC.setFill(Color.DARKSLATEGREY);
		}else {
			activosNC.setText(""+0);
			activosNC.setFill(Color.DARKSLATEGREY);
		}
		
		if (Main.getAc().getBg().sumaPasivosCorrientes() != 0) {
			pasivosC.setText("" +Main.getAc().getBg().sumaPasivosCorrientes());
			pasivosC.setFill(Color.DARKSLATEGREY);
		}else {
			pasivosC.setText(""+0);
			pasivosC.setFill(Color.DARKSLATEGREY);
		}
		
		if (Main.getAc().getBg().sumaPasivosNoCorrientes() != 0) {
			pasivosNC.setText("" +Main.getAc().getBg().sumaPasivosNoCorrientes());
			pasivosNC.setFill(Color.DARKSLATEGREY);
		}else {
			pasivosNC.setText(""+0);
			pasivosNC.setFill(Color.DARKSLATEGREY);
		}
		
		if (Main.getAc().getBg().sumaActivos() != 0) {
			sumaActivos.setText("" +Main.getAc().getBg().sumaActivos());
		}else {
			sumaActivos.setText(""+0);
			sumaActivos.setFill(Color.DARKSLATEGREY);
		}
		
		if (Main.getAc().getBg().sumaPasivos() != 0) {
			sumaPasivos.setText("" +Main.getAc().getBg().sumaPasivos());
		}else {
			sumaPasivos.setText(""+0);
			sumaPasivos.setFill(Color.DARKSLATEGREY);
		}
		
		if (Main.getAc().getBg().sumaPasivos_Patrimonio() != 0) {
			sumaPasivosPatrimonio.setText("" +Main.getAc().getBg().sumaPasivos_Patrimonio());
		}else {
			sumaPasivosPatrimonio.setText(""+0);
			sumaPasivosPatrimonio.setFill(Color.DARKSLATEGREY);
		}
		
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
