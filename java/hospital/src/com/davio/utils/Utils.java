package com.davio.utils;
import java.util.ArrayList;

import com.davio.main.Atendente;
import com.davio.main.Hospital;
import com.davio.main.Registro;

abstract public class Utils {
	public static Atendente efetuarLogin(ArrayList<Atendente> atendentes, String login, String senha) {
		for (Atendente atendente:atendentes) {
			if (atendente.getLogin().equals(login)) {
				if (atendente.getSenha().equals(senha)) {
					return atendente;
				}
			}
		}
		throw new IllegalArgumentException("[Login] - Conta n�o existente!");
	}
	
	public static int setarIdRegistro(ArrayList<Registro> registros) {
		if (registros.isEmpty())
			return 0;
		return registros.size() + 1;
	}
	
	public static float checkMediaInfectados(ArrayList<Registro> registros) {
		float infectados = 0;
		for (Registro registro: registros) {
			if (registro.isInfeccao())
			infectados++;
		}
		return infectados/registros.size();
	}
	
	public static void checkMediaEstadosInfectados(float[] mediaEstados, ArrayList<Registro> registros) {
		for (Registro registro: registros) {
			if (registro.isInfeccao()) {
				if (registro.getEstado().equals("Leve"))
					mediaEstados[0]++;
				if (registro.getEstado().equals("Medio"))
					mediaEstados[1]++;
				if (registro.getEstado().equals("Grave"))
					mediaEstados[2]++;
			}
		}
		mediaEstados[0] /= registros.size();
		mediaEstados[1] /= registros.size();
		mediaEstados[2] /= registros.size();
	}
	
	public static void checkMediaSexoInfectados(float[] mediaSexo, ArrayList<Registro> registros) {
		for (Registro registro: registros) {
			if (registro.isInfeccao()) {
				if (registro.getPaciente().getSexo().equals("M"))
					mediaSexo[0]++;
				if (registro.getPaciente().getSexo().equals("F"))
					mediaSexo[1]++;
			}
		}
		mediaSexo[0] /= registros.size();
		mediaSexo[1] /= registros.size();
	}
	
	public static ArrayList<String> getListaNomePacientesRegistros(Hospital hospital) {
		ArrayList<String> nomePacientes = new ArrayList<String>();
		for (Registro registro: hospital.getRegistros()) {
			StringBuilder registroPaciente = new StringBuilder();
			nomePacientes.add(registroPaciente.append(registro.getId()).append(" - ").append(registro.getPaciente().getNome()).toString());
		}
		return nomePacientes;
	}
}
