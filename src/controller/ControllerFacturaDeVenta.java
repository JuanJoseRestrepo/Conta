package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
import exceptions.NotFoundIngresosGastos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import model.AdministradorCuentas;
import model.Cuenta;

public class ControllerFacturaDeVenta implements Initializable {

	@FXML
	private Label totalCuenta;
	@FXML
	private TextField CostoDeVenta;
	@FXML
	private TextField Monto;
	@FXML
	private TextField nombre;
	@FXML
	private TextField codigo;
	@FXML
	private Button guardar;
	@FXML
	private VBox boxes;
	@FXML
	private Button volver;

	private ArrayList<Cuenta> cuentasIngresosTemp;
	
	public ControllerFacturaDeVenta() {
		cuentasIngresosTemp = new ArrayList<Cuenta>();
	}

	void init(){
		
		guardar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
			try {
				
				boxes = new VBox();
				String name = nombre.getText();
				int monto = Integer.parseInt(Monto.getText());
				int cod = Integer.parseInt(codigo.getText());
				int costoDeventa = Integer.parseInt(CostoDeVenta.getText());
				
					if(Main.getAc().getEr().buscarRepetidos(cod)) {
						throw new NotFoundIngresosGastos("");
					}else {
						int totalGeneral = 0;
						Cuenta m1 = new Cuenta(name,cod,AdministradorCuentas.INGRESO_O,monto);
						int costo = Main.getAc().getEr().getCostosDeVentas().getValor_c() + costoDeventa;
						Main.getAc().getCuentas().add(m1);
						Main.getAc().getEr().getCostosDeVentas().setValor_c(costo);
						Main.getAc().getEr().getIngresosOperacionales().add(m1);
						cuentasIngresosTemp.add(m1);
						boxes.setSpacing(30); 
						for(int i = 0;i < cuentasIngresosTemp.size();i++) {
							Label lavel = new Label(cuentasIngresosTemp.get(i).getNombre_c()+ " " + cuentasIngresosTemp.get(i).getValor_c());
							totalGeneral += cuentasIngresosTemp.get(i).getValor_c();
							boxes.getChildren().add(lavel);
						}
						
						totalCuenta.setText(String.valueOf(totalGeneral));
						
					}
					
					
				
				
			} catch (NumberFormatException e) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Entrada no valida");
				alert.setHeaderText(null);
				alert.setContentText("La entrada de valor fue tipada incorrectamente");
				alert.showAndWait();
			
			} catch (NotFoundIngresosGastos e) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Entrada no valida");
				alert.setHeaderText(null);
				alert.setContentText("Hay una venta con ese codigo");
				alert.showAndWait();
			}
		  }
			
			
		
	});
		
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
	public void initialize(URL arg0, ResourceBundle arg1) {
		init();

	}

}
