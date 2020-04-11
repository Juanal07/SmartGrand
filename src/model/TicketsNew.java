package model;

import java.sql.Date;

public class TicketsNew {

	private int id_tic, id_medico, id_paciente;
	private String texto_Paciente, texto_Medico;
	private Date fecha_Paciente, fecha_Medico;

	public TicketsNew() {
		super();
	}

	public TicketsNew(int id_medico, int id_paciente, String texto_Paciente, String texto_Medico, Date fecha_Paciente,
			Date fecha_Medico) {
		super();
		this.id_medico = id_medico;
		this.id_paciente = id_paciente;
		this.texto_Paciente = texto_Paciente;
		this.texto_Medico = texto_Medico;
		this.fecha_Paciente = fecha_Paciente;
		this.fecha_Medico = fecha_Medico;
	}

	public TicketsNew(int id_tic, int id_medico, int id_paciente, String texto_Paciente, String texto_Medico,
			Date fecha_Paciente, Date fecha_Medico) {
		super();
		this.id_tic = id_tic;
		this.id_medico = id_medico;
		this.id_paciente = id_paciente;
		this.texto_Paciente = texto_Paciente;
		this.texto_Medico = texto_Medico;
		this.fecha_Paciente = fecha_Paciente;
		this.fecha_Medico = fecha_Medico;
	}

	public int getId_tic() {
		return id_tic;
	}

	public void setId_tic(int id_tic) {
		this.id_tic = id_tic;
	}

	public int getId_medico() {
		return id_medico;
	}

	public void setId_medico(int id_medico) {
		this.id_medico = id_medico;
	}

	public int getId_paciente() {
		return id_paciente;
	}

	public void setId_paciente(int id_paciente) {
		this.id_paciente = id_paciente;
	}

	public String getTexto_Paciente() {
		return texto_Paciente;
	}

	public void setTexto_Paciente(String texto_Paciente) {
		this.texto_Paciente = texto_Paciente;
	}

	public String getTexto_Medico() {
		return texto_Medico;
	}

	public void setTexto_Medico(String fexto_Medico) {
		this.texto_Medico = fexto_Medico;
	}

	public Date getFecha_Paciente() {
		return fecha_Paciente;
	}

	public void setFecha_Paciente(Date fecha_Paciente) {
		this.fecha_Paciente = fecha_Paciente;
	}

	public Date getFecha_Medico() {
		return fecha_Medico;
	}

	public void setFecha_Medico(Date fecha_Medico) {
		this.fecha_Medico = fecha_Medico;
	}

}
