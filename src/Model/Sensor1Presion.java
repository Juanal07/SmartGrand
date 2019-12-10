package Model;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Sensor1Presion {
	private String dniPaciente;
	private String fecha;
	private int valor;
	private int intervalos[];



	public Sensor1Presion(String dniPaciente, String fecha, int valor, int[] intervalos) {
		super();
		this.dniPaciente = dniPaciente;
		this.fecha = fecha;
		this.valor = valor;
		this.intervalos = intervalos;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public int[] getIntervalo() {
		return intervalos;
	}

	public int getSumIntervalos() {
		int intervalo = (IntStream.of(intervalos).sum() / 3600);

		return intervalo;
	}

	public void setIntervalo(int intervalo[]) {
		this.intervalos = intervalo;
	}

	public String getDniPaciente() {
		return dniPaciente;
	}

	public void setDniPaciente(String dniPaciente) {
		this.dniPaciente = dniPaciente;
	}

}
