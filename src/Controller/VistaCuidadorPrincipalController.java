package Controller;

import java.awt.Button;
import java.awt.Label;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class VistaCuidadorPrincipalController {
	
	 @FXML
	 private JFXTextField myTextField;
	 
	 //Esta funci�n actualiza la VISTA de la segunda ventana
	 public void writeText(String mensaje) {		
	     myTextField.setText(mensaje);
	   }
}
