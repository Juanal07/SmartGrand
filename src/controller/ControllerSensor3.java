package controller;

import java.util.ArrayList;

import DataBase.Conexion;
import javafx.fxml.FXML;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import model.Sensor1New;
import model.Sensor3caidas;

public class ControllerSensor3 {
	@FXML
	BarChart<String, Number> graf3;
	private Label lbOculto = new Label();

	public void cargarGrafica() {
		Conexion conexion = new Conexion();
		ArrayList<Sensor1New> reposoData = conexion.cargarSensor(Integer.parseInt(lbOculto.getText()), "luz");

		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
		series.setName("cantidad luz");

		for (int i = 0; i < reposoData.size(); i++) {
			String fecha = reposoData.get(i).getFecha();
			int valor = reposoData.get(i).getDato();
			series.getData().add(new XYChart.Data<String, Number>(fecha, valor));
		}
		graf3.getData().add(series);
	}

	public void escibirDniPaciente(String id_paciente) {
		lbOculto.setText(id_paciente);
	}

}
