package model;


import java.util.stream.IntStream;

public class Sensor1presion {
	private String dniPaciente;
	private String fecha;
	private int valor;
	private int intervalos[];



	public Sensor1presion(String dniPaciente, String fecha, int valor, int[] intervalos) {
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
