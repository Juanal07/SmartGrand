package controller;

import java.util.ArrayList;

import javafx.fxml.FXML;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import model.Sensor3caidas;

public class ControllerSensor3Caidas {
	@FXML
	BarChart<String, Number> graf3;
	private Label lbOculto = new Label();

	public void cargarGrafica() {
		ArrayList<Sensor3caidas> datos2 = GsonGeneral.desserializarJsonAArraySensor3();

		String dniP = lbOculto.getText().substring(lbOculto.getText().indexOf(" ")+1, lbOculto.getText().length());
		
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
		series.setName("Numero de caidas");
		
		for (int i = 0; i < datos2.size(); i++) {
			if (datos2.get(i).getIdPaciente().equals(dniP)) {
				for (int j = 0; j < datos2.get(i).getCaidas().length; j++) {
					String dia ="Dia" + (j+1);
					int valor = datos2.get(i).getCaidas()[j];
					series.getData().add(new XYChart.Data<String, Number>(dia, valor));
				
			}
		}
		
		}
		graf3.getData().add(series);
	}

	public void escibirDniPaciente(String text) {
		lbOculto.setText(text);
	}

}
