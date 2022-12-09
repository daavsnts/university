package com.davio.utils;
import java.util.concurrent.ThreadLocalRandom;

import com.davio.main.Atendente;
import com.davio.main.Hospital;
import com.davio.main.Paciente;

public class FakeFactory implements SystemFactory{
	
	public void popularSistema(Hospital hospital) {
		String[] nomesAleatoriosMasculinos = {
			"Jo�o Silva", 
			"Fernando Mendes", 
			"Josefino Santos", 
			"Enzo Lopes", 
			"Rog�rio Albuquerque", 
			"Lucas Macedo", 
			"Marcio Pereira", 
			"Augusto Carvalho", 
			"Gustavo Fernandes", 
			"Michael Ventura"};
		
		String[] nomesAleatoriosFemininos = {
				"Bia Silva", 
				"Fernanda Mendes", 
				"Josefina Santos", 
				"Rafaela Lopes", 
				"Bruna Albuquerque", 
				"Renata Macedo", 
				"Marcia Pereira", 
				"Maria Carvalho", 
				"Leticia Fernandes", 
				"Brenda Ventura"};
		
		String[] bairrosAleatorios = {
				"P Norte", 
				"Setor O", 
				"P Sul", 
				"Ceil�ndia Norte", 
				"Ceil�ndia Sul", 
				"M Norte", 
				"Sol Nascente", 
				"Expans�o Setor O", 
				"Ceil�ndia Centro", 
				"Por do Sol"};
		
		String[] comorbidadesAleatorias = {
				"Diabete", 
				"Hipertens�o", 
				"Obesidade", 
				"Tuberculose", 
				"Asma", 
				"Hipotireoidismo", 
				"Doen�a de chagas", 
				"Insufici�ncia card�aca", 
				"Insufici�ncia renal", 
				"Cirrose"};
		
		String[] horariosAleatorios = {
				"12:34",
				"13:55",
				"09:16",
				"11:15",
				"11:37",
				"14:02",
				"16:30",
				"14:22",
				"16:20",
				"10:12"};
		
		// Atendente ficticio:
		Atendente atendente = hospital.criarAtendente("Davi Iorgers", "558579", "AT-447857", "diorgers", "123");
		
		// Cria��o de Pacientes masculinos:
		for (int i = 0; i < 10; i++) {
			String nome = nomesAleatoriosMasculinos[i];
			int idade = ThreadLocalRandom.current().nextInt(1, 120);
			String sexo = "M";
			StringBuilder rg = new StringBuilder();
			rg.append("35353");
			rg.append(Integer.toString(i));
			String bairro = bairrosAleatorios[i];
			String comorbidades = comorbidadesAleatorias[i];
			String infeccao = "S";
			String estado;
			if (i % 2 == 1) {
				estado = "Medio";
			} else {
				estado = "Leve";
			}
			String horario = horariosAleatorios[i];
			Paciente paciente = hospital.criarPaciente(nome, idade, sexo, bairro, rg.toString(), comorbidades);
			hospital.criarRegistro(Utils.setarIdRegistro(hospital.getRegistros()), paciente, horario, infeccao, estado, atendente);
		}
		
		for (int i = 0; i < 10; i++) {
			String nome = nomesAleatoriosFemininos[i];
			int idade = ThreadLocalRandom.current().nextInt(1, 120);
			String sexo = "F";
			StringBuilder rg = new StringBuilder();
			rg.append("35354");
			rg.append(Integer.toString(i));
			String bairro = bairrosAleatorios[i];
			String comorbidades = comorbidadesAleatorias[i];
			String infeccao = "S";
			String estado;
			if (i % 2 == 1) {
				estado = "Grave";
			} else {
				estado = "Leve";
			}
			String horario = horariosAleatorios[i];
			Paciente paciente = hospital.criarPaciente(nome, idade, sexo, bairro, rg.toString(), comorbidades);
			hospital.criarRegistro(Utils.setarIdRegistro(hospital.getRegistros()), paciente, horario, infeccao, estado, atendente);
		}
	}
}
