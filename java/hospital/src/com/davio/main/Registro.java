package com.davio.main;

public class Registro {
	private int id;
	private Paciente paciente;
	private String horario;
	private boolean infeccao;
	private String estado;
	private Atendente atendente;
	
	// Constructor:
	Registro(int id, Paciente paciente, String horario, String infeccao, String estado, Atendente atendente) {
		setId(id);
		setPaciente(paciente);
		setHorario(horario);
		setInfeccao(infeccao);
		setEstado(estado);
		setAtendente(atendente);
	}

	// Getters and Setters:
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		// Validar hor�rio at� 24hrs.
		if (!horario.isEmpty()) {
			this.horario = horario;
		} else {
			throw new IllegalArgumentException("[Registro] - Hor�rio vazio!");
		}
		
	}

	public boolean isInfeccao() {
		return infeccao;
	}

	public void setInfeccao(String infeccao) {
		if (!infeccao.isEmpty()) {
			if (infeccao.equals("S")) {
				this.infeccao = true;
			} else if (infeccao.equals("N")) {
				this.infeccao = false;
			} else {
				throw new IllegalArgumentException("[Infec��o] - Infec��o inv�lida!");
			}	
		} else {
			throw new IllegalArgumentException("[Infec��o] - Infec��o vazia!");
		}
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		if (!estado.isEmpty()) {
			if (estado.equals("Leve") || estado.equals("Medio") || estado.equals("Grave")) {
				this.estado = estado;
			} else {
				throw new IllegalArgumentException("[Registro] - Estado inv�lido!");
			}
		} else {
			throw new IllegalArgumentException("[Registro] - Estado vazio!");
		}
	}

	public Atendente getAtendente() {
		return atendente;
	}

	public void setAtendente(Atendente atendente) {
		this.atendente = atendente;
	}
}
