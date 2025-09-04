package controller;

import app.App;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
    private TableView<?> tablaUsuarios1;
    @FXML
    private TableColumn<?, ?> colCedula1;
    @FXML
    private TableColumn<?, ?> colNombre1;
    @FXML
    private TableColumn<?, ?> colTipo1;
    @FXML
    private TableColumn<?, ?> colSalario1;
    @FXML
    private TableColumn<?, ?> colBono1;
    @FXML
    private TableColumn<?, ?> colTotal1;
    @FXML
    private Button btnSalir;

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
