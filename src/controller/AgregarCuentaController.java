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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import model.Cuenta;

public class AgregarCuentaController implements Initializable, Serializable{
	
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
	
	ObservableList<String> listA = FXCollections.observableArrayList();
	
	ObservableList<Cuenta> listB = FXCollections.observableArrayList();
	
	private TableView<Cuenta> toShow;
	
	@FXML
	private BorderPane panel;
	
	@FXML
	private TextField codigo;
	
	@FXML
	private TextField nombre;
	
	@FXML
	private TextField cantidad;
	
	@FXML
	private ChoiceBox<String> tipos;
	
	@FXML
	private Button volver;
	
	@FXML
	private Button guardar;
	
	@FXML
	private Button table;
	
	@SuppressWarnings("unchecked")
	void init(){
		//
		toShow = new TableView<Cuenta>();
		toShow.setMaxWidth(400);
		toShow.setMaxHeight(300);
		
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
		
		listB = FXCollections.observableArrayList(Main.getAc().getCuentasP());
		
		toShow.setItems(listB);
		
		
		guardar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				
				if (codigo.getText().equals("") || nombre.getText().equals("")) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Entrada no valida");
					alert.setHeaderText(null);
					alert.setContentText("Los campos no deben estar vacios");
					alert.showAndWait();
				}else {
					agregarCuenta();
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Exito");
					alert.setHeaderText(null);
					alert.setContentText("Su registro ha sido guardado");
					alert.showAndWait();
					
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
		
		
		table.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				
				if (toShow.getSelectionModel().isEmpty()) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText(null);
					alert.setContentText("Seleccione un elemento de la tabla");
					alert.showAndWait();
				} else {
					
					String nombre_m = listB.get(toShow.getSelectionModel().getSelectedIndex()).getNombre_c();
					int codigo_m = listB.get(toShow.getSelectionModel().getSelectedIndex()).getCodigo_c();
					String estado_m = listB.get(toShow.getSelectionModel().getSelectedIndex()).getEstado_m();
					
					tipos.setValue(estado_m);
					nombre.setText(nombre_m);
					codigo.setText(String.valueOf(codigo_m));
					
					nombre.setEditable(false);
					codigo.setEditable(false);
					tipos.setVisible(false);
					
				}

			}
		});
		
	}
	
	void agregarCuenta() {
		try {
			Cuenta cuenta = new Cuenta(nombre.getText(),Integer.parseInt(codigo.getText()), tipos.getValue(), Integer.parseInt(cantidad.getText()));
			Main.getAc().getCuentas().add(cuenta);
			Main.getAc().actualizar();
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
			alert.setContentText("El tipo seleccionado es nulo");
			alert.showAndWait();
		}
		
		
	}
	
	@SuppressWarnings("unlikely-arg-type")
	void createBox() {
		listA.remove(listA);
		listA.addAll(ACORRIENTE,ANO_CORRIENTE,PCORRIENTE,PNO_CORRIENTE,PATRIMONIO,INGRESO_O,GASTO_O,INGRESO_NO,INGRESO_O,COSTO);
		tipos.getItems().addAll(listA);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		createBox();
		init();
	}



}
