package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class VistaCuidadorPrincipalController {
	
	 @FXML
	 private TextField myTextField;
	 
	 //Esta función actualiza la VISTA de la segunda ventana
	 public void writeText(String mensaje) {		
	     myTextField.setText(mensaje);
	   }
}
