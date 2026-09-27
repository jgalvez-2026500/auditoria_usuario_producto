/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.samuelgalvez.system.utils;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.function.Supplier;

import java.util.function.Supplier;
import javafx.scene.control.Alert;

public class AlertInformation {

    /**
     * Constructor vacío público
     */
    public AlertInformation() {
        // Constructor por defecto
    }

    /**
     * Método público sin retorno que muestra una alerta de JavaFX.
     * 
     * @param titulo    El título de la ventana de la alerta.
     * @param cabecera  El texto de la cabecera (puede ser null).
     * @param mensaje   El contenido o mensaje principal de la alerta.
     * @param tipoAlerta El tipo de alerta como String (ej: "INFO", "WARNING", "ERROR").
     */
    public void viewAlert(String titulo, String cabecera, String mensaje, String tipoAlerta) {

        // Switch Expression (Java 14+) que devuelve una expresión lambda (Supplier<Alert>)
        Supplier<Alert> alertSupplier = switch (tipoAlerta.toUpperCase()) {
            case "INFO"        -> () -> new Alert(Alert.AlertType.INFORMATION);
            case "WARNING"     -> () -> new Alert(Alert.AlertType.WARNING);
            case "ERROR"       -> () -> new Alert(Alert.AlertType.ERROR);
            case "CONFIRM"     -> () -> new Alert(Alert.AlertType.CONFIRMATION);
            case "NONE"        -> () -> new Alert(Alert.AlertType.NONE);
            default            -> () -> new Alert(Alert.AlertType.INFORMATION);
        };

        // Obtenemos el Alert ejecutando la lambda devuelta por el switch
        Alert alert = alertSupplier.get();

        // Configuramos y mostramos la alerta
        alert.setTitle(titulo);
        alert.setHeaderText(cabecera);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}