package com.davio.main;
import java.util.ArrayList;

public class Hospital {
	private String nome;
	private ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
	private ArrayList<Registro> registros = new ArrayList<Registro>();
	private ArrayList<Atendente> atendentes = new ArrayList<Atendente>();

	// Constructor:
	Hospital(String nome) {
		setNome(nome);
	}

	// Getters and Setters:
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (!nome.isEmpty()) {
			this.nome = nome;
		} else {
			throw new IllegalArgumentException("[Hospital] - Nome inv�lido!");
		}
	}

	public ArrayList<Paciente> getPacientes() {
		return pacientes;
	}

	public ArrayList<Registro> getRegistros() {
		return registros;
	}
	
	public Registro getRegistroPorId(int id) {
		return registros.get(id);
	}

	public ArrayList<Atendente> getAtendentes() {
		return atendentes;
	}

	// Methods:
	public Paciente criarPaciente(String nome, int idade, String sexo, String bairro, String rg, String comorbidades) throws IllegalArgumentException {
		for (Paciente paciente: pacientes) {
			if (paciente.getRg().equals(rg))
				throw new IllegalArgumentException("[Hospital] - Rg j� existente!");
		}
		Paciente paciente = new Paciente(nome, idade, sexo, bairro, rg, comorbidades);
		pacientes.add(paciente);
		return paciente;
	}
	
	public Paciente criarPaciente(String nome, int idade, String sexo, String bairro, String rg) throws IllegalArgumentException {
		for (Paciente paciente: pacientes) {
			if (paciente.getRg().equals(rg))
				throw new IllegalArgumentException("[Hospital] - Rg j� existente!");
		}
		Paciente paciente = new Paciente(nome, idade, sexo, bairro, rg, "Nenhuma");
		pacientes.add(paciente);
		return paciente;
	}

	public Registro criarRegistro(int id, Paciente paciente, String horario, String infeccao, String estado, Atendente atendente) throws IllegalArgumentException {
		Registro registro = new Registro(id, paciente, horario, infeccao, estado, atendente);
		registros.add(registro);
		return registro;
	}

	public Atendente criarAtendente(String nome, String rg, String matricula, String login, String senha) {
		for (Atendente atendente: atendentes) {
			if (atendente.getRg().equals(rg))
				throw new IllegalArgumentException("[Hospital] - Rg j� existente!");
			if (atendente.getMatricula().equals(matricula))
				throw new IllegalArgumentException("[Hospital] - Matr�cula j� existente!");
			if (atendente.getLogin().equals(login))
				throw new IllegalArgumentException("[Hospital] - Login j� existente!");
		}
		Atendente atendente = new Atendente(nome, rg, matricula, login, senha);
		atendentes.add(atendente);
		return atendente;
	}
}
