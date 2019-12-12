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
		
		System.out.println("tamaño misSensores: " + misSensores.size());
		for (Sensor1Presion sensor1Presion : misSensores) {
			System.out.println(sensor1Presion.getDniPaciente() + "  valor:  " + sensor1Presion.getValor());
		}

		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
		XYChart.Series<String, Number> minuteSeries = new XYChart.Series<String, Number>();
		series.setName("numero de veces");
		minuteSeries.setName("numero de horas");

		for (int i = 0; i < misSensores.size(); i++) {
			String fecha = misSensores.get(i).getFecha();

			int valor = misSensores.get(i).getValor();
			double descansos = misSensores.get(i).getSumIntervalos();

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
