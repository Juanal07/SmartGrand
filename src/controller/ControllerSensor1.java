package controller;

import java.util.ArrayList;

import DataBase.Conexion;
import javafx.fxml.FXML;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import model.Sensor1New;

public class ControllerSensor1 {
	@FXML
	BarChart<String, Number> graf;
	private Label lbOculto = new Label();

	public void cargarGrafica() {
//		ArrayList<Sensor1New> reposoData = GsonGeneral.desserializarJsonAArraySensor1();
		
		Conexion conexion = new Conexion();
		ArrayList<Sensor1New> reposoData = conexion.cargarSensor(Integer.parseInt(lbOculto.getText()),"temperatura");


//		String dniP = lbOculto.getText().substring(lbOculto.getText().indexOf(" ")+1, lbOculto.getText().length());
		
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
		XYChart.Series<String, Number> minuteSeries = new XYChart.Series<String, Number>();
		series.setName("numero de veces");
		minuteSeries.setName("numero de horas");
		
		for (int i = 0; i < reposoData.size(); i++) {
			
			String fecha = reposoData.get(i).getFecha();
			int valor = reposoData.get(i).getDato();			
//			double descansos = reposoData.get(i).getSumIntervalos();
			
//			minuteSeries.getData().add(new XYChart.Data<String, Number>(fecha, descansos));
			series.getData().add(new XYChart.Data<String, Number>(fecha, valor));

		}
		graf.getData().add(series);
//		graf.getData().add(minuteSeries);
		
	}

	public void escibirDniPaciente(String id_paciente) {
		lbOculto.setText(id_paciente);
	}

}
