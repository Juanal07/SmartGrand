package Controller;

import java.util.ArrayList;
import Model.Sensor1Presion;
import javafx.fxml.FXML;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

public class ControllerSensor1Presion {
	@FXML
	BarChart<String, Number> graf;
	private Label lbOculto = new Label();

	public void cargarGrafica() {
		ArrayList<Sensor1Presion> reposoData = GsonGeneral.desserializarJsonAArraySensor1();
		ArrayList<Sensor1Presion> misSensores = new ArrayList<Sensor1Presion>();

		String dniP = lbOculto.getText().substring(lbOculto.getText().indexOf(" ")+1, lbOculto.getText().length());
		
		for (int i = 0; i < reposoData.size(); i++) {
			if (reposoData.get(i).getDniPaciente().equals(dniP)) {
				misSensores.add(reposoData.get(i));
			}
		}

		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
		XYChart.Series<String, Number> minuteSeries = new XYChart.Series<String, Number>();
		series.setName("numero de veces");
		minuteSeries.setName("numero de horas");

		for (int i = 0; i < reposoData .size(); i++) {
			String fecha = reposoData .get(i).getFecha();
			int valor = reposoData .get(i).getValor();
			double descansos = reposoData .get(i).getSumIntervalos();

			minuteSeries.getData().add(new XYChart.Data<String, Number>(fecha, descansos));
			series.getData().add(new XYChart.Data<String, Number>(fecha, valor));
		}

		graf.getData().add(series);
		graf.getData().add(minuteSeries);
	}

	public void escibirDniPaciente(String text) {
		lbOculto.setText(text);
	}

}
