package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Cuenta;

public class BalanceGeneralController implements Initializable,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ObservableList<Cuenta> listA = FXCollections.observableArrayList();
	
	ObservableList<String> listB = FXCollections.observableArrayList();
	
	private TableView<Cuenta> toShow;
	
	@FXML
	private ChoiceBox<String> documentos;
 	
	@FXML
	private BorderPane panel;
	
	@FXML
	private Button agregarCuenta;
	
	@FXML
	private Button balance;
	
	@FXML
	private Button ir;
	
	@FXML
	private Button resultado;
	
	@FXML
	private Label nombre;
	
	@FXML
	private Label inicio;
	
	@FXML 
	private Label fin;
	

	
	@SuppressWarnings("unchecked")
	void init() {
		toShow = new TableView<Cuenta>();
		toShow.setMaxWidth(400);
		toShow.setMaxHeight(400);
		
		TableColumn<Cuenta, Integer> colCode = new TableColumn<>("Codigo");
		colCode.setCellValueFactory(new PropertyValueFactory<Cuenta, Integer>("codigo_c"));
		colCode.setPrefWidth(toShow.getMaxWidth()/4);
		
		TableColumn<Cuenta, String> colName = new TableColumn<>("Cuenta");
		colName.setCellValueFactory(new PropertyValueFactory<Cuenta, String>("nombre_c"));
		colName.setPrefWidth(toShow.getMaxWidth()/4);
		
		TableColumn<Cuenta, String> colTipo = new TableColumn<>("Tipo");
		colTipo.setCellValueFactory(new PropertyValueFactory<Cuenta, String>("estado_m"));
		colTipo.setPrefWidth(toShow.getMaxWidth()/4);
		
		TableColumn<Cuenta, Integer> colVal = new TableColumn<>("Valor");
		colVal.setCellValueFactory(new PropertyValueFactory<Cuenta, Integer>("valor_c"));
		colVal.setPrefWidth(toShow.getMaxWidth()/4);
		toShow.getColumns().addAll(colCode, colName, colVal,colTipo);
		panel.setCenter(toShow);
		
		listA = FXCollections.observableArrayList(Main.getAc().getCuentas());
		
		toShow.setItems(listA);
		
		
		
		
		agregarCuenta.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {

				Parent root;
				try {
					root = FXMLLoader.load(getClass().getResource("/application/agregarCuenta.fxml"));
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
		
		balance.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {

				Parent root;
				try {
					root = FXMLLoader.load(getClass().getResource("/application/resultados.fxml"));
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
		
		resultado.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {

				Parent root;
				try {
					root = FXMLLoader.load(getClass().getResource("/application/estadoResultados.fxml"));
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
		
		ir.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {
				

				Parent root;
				try {
					if (documentos.getValue().equals("Factura de Compra")) {
						root = FXMLLoader.load(getClass().getResource("/application/facturaCompra.fxml"));
						Scene scene = new Scene(root);
						Stage stage = (Stage) ((Node) t.getSource()).getScene().getWindow();
						root.getStylesheets().add("/application/application.css");
						stage.getIcons().add(new Image("/application/horseHipodromo.jpg"));
						stage.setScene(scene);
						stage.centerOnScreen();
						stage.show();
					} else if(documentos.getValue().equals("Factura de Venta")) {
						root = FXMLLoader.load(getClass().getResource("/application/FacturaDeVentas.fxml"));
						Scene scene = new Scene(root);
						Stage stage = (Stage) ((Node) t.getSource()).getScene().getWindow();
						root.getStylesheets().add("/application/application.css");
						stage.getIcons().add(new Image("/application/horseHipodromo.jpg"));
						stage.setScene(scene);
						stage.centerOnScreen();
						stage.show();
					}
					
				} catch (IOException e) {
					e.printStackTrace();
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
	
	@SuppressWarnings("unlikely-arg-type")
	void createBox() {
		listB.remove(listB);
		listB.addAll("Factura de Compra","Factura de Venta");
		documentos.getItems().addAll(listB);
	}	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		init();
		createBox();
		nombre.setText(Main.getAc().getEmpresa());
		inicio.setText(Main.getAc().getInicio());
		fin.setText(Main.getAc().getFin());
	}

}
