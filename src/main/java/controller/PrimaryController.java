package controller;

import app.App;
import java.io.IOException;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import modelo.Empleado;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author marco
 */
public class PrimaryController {
  
    @FXML
    private Button btnCargar1;
    @FXML
    private Button btnCalcular1;
    @FXML
    private Button btnExportar1;
    @FXML
    private Label lblTitulo1;
    @FXML
    private TableView<Empleado> tablaUsuarios1;
    @FXML
    private TableColumn<Empleado, String> colCedula1;
    @FXML
    private TableColumn<Empleado, String> colNombre1;
    @FXML
    private TableColumn<Empleado, String> colTipo1;
    @FXML
    private TableColumn<Empleado, Double> colSalario1;
    @FXML
    private TableColumn<Empleado, Double> colBono1;
    @FXML
    private TableColumn<Empleado, Double> colTotal1;
    @FXML
    private Button btnSalir;
    
    private ObservableList<Empleado> empleados = FXCollections.observableArrayList();
    
    @FXML
    public void intitialize() {
        colCedula1.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCedula()));
        colNombre1.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNombre()));
        colTipo1.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTipo()));
        colSalario1.setCellValueFactory(cell -> new SimpleDoubleProperty(cell.getValue().salarioQuincena()).asObject());
        //colBono1.setCellValueFactory(cell -> new SimpleDoubleProperty(cell.getValue().getBono()).asObject());
       // colTotal1.setCellValueFactory(cell -> new SimpleDoubleProperty(cell.getValue().getTotalAPagar()).asObject());

        tablaUsuarios1.setItems(empleados);
        
    }

    @FXML
    private void onCargar(ActionEvent event) {
    }

    @FXML
    private void onCalcular(ActionEvent event) {
    }

    @FXML
    private void OnExportar(ActionEvent event) {
    }

    @FXML
    private void onSalir(ActionEvent event) {
    }
    
}
