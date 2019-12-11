package Controller;


import java.net.URL;
import java.util.ArrayList;

import java.util.ResourceBundle;




import Model.Sensor1Presion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;


public class ControllerSensor1Presion implements Initializable {
	@FXML
	BarChart<String, Number> graf;
	private Label lbOculto = new Label();

	@Override

	public void initialize(URL url, ResourceBundle rb) {
		ArrayList<Sensor1Presion> reposoData = GsonGeneral.desserializarJsonAArraySensor1();
		ArrayList<Sensor1Presion> misSensores = new ArrayList<Sensor1Presion>();
		int aux = 0;
		int tamañoreposoData = reposoData.size();
		while (aux <  tamañoreposoData) {
			if (reposoData.get(aux).getDniPaciente().equals(lbOculto.getText())) {
				
			}
		}
		
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
		XYChart.Series<String, Number> minuteSeries = new XYChart.Series<String, Number>();
		series.setName("numero de veces");
		minuteSeries.setName("numero de horas");

		for (int i = 0; i < reposoData.size(); i++) {
			String fecha = reposoData.get(i).getFecha();

			int valor = reposoData.get(i).getValor();
			double descansos = reposoData.get(i).getSumIntervalos();

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
