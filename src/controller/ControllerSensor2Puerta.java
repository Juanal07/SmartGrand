package controller;

import java.util.ArrayList;

import javafx.fxml.FXML;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import model.GsonGeneral;
import model.Sensor2puerta;

public class ControllerSensor2Puerta {
	@FXML
	BarChart<String, Number> graf2;
	private Label lbOculto = new Label();

	public void cargarGrafica() {
		ArrayList<Sensor2puerta> datos = GsonGeneral.desserializarJsonAArraySensor2();

		String dniP = lbOculto.getText().substring(lbOculto.getText().indexOf(" ")+1, lbOculto.getText().length());
		
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
		series.setName("Puerta abierta/cerrada");
		
		for (int i = 0; i < datos.size(); i++) {
			if (datos.get(i).getIdPaciente().equals(dniP)) {
				for (int j = 0; j < datos.get(i).getEstadoPuerta().length; j++) {
					String hora = j+":00";
					int valor = datos.get(i).getEstadoPuerta()[j];
					series.getData().add(new XYChart.Data<String, Number>(hora, valor));
				
			}
		}
		
		}
		graf2.getData().add(series);
	}

	public void escibirDniPaciente(String text) {
		lbOculto.setText(text);
	}

}
