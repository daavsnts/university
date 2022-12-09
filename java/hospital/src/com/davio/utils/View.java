package com.davio.utils;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.davio.main.Atendente;
import com.davio.main.Hospital;
import com.davio.main.Paciente;
import com.davio.main.Registro;

abstract public class View {
	
	// Mensagens:
	public static void mostrarMensagem(String message, String title) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	}

	public static void mostrarErro(String error) {
		JOptionPane.showMessageDialog(null, error, "[Hospital] - Erro!", JOptionPane.ERROR_MESSAGE);
	}
	
	public static String selecionarDaLista(Object[] choices, String msg, String title) {
		return (String) JOptionPane.showInputDialog(null, msg, title, JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
	}

	// Telas:

	public static void telaInicial(Hospital hospital) {
		Object[] options = {"Efetuar Login", "Registrar Atendente", "Sair"};
		boolean exit = false;
		do {
			int option = JOptionPane.showOptionDialog(null, "Bem vindo ao Sistema Hospitalar!", "[Hospital] - In�cio", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			switch (option) {
			case 0:
				efetuarLogin(hospital);
				break;
			case 1:
				registrarAtendente(hospital);
				break;
			case 2:
				exit = true;
				break;
			}
		} while (!exit);

	}

	public static void telaAtendente(Atendente atendente, Hospital hospital) {
		Object[] options = {"Registrar Paciente", "Gerenciar Registros", "Verificar Relat�rio", "Sair"};
		boolean exit = false;
		do {
			int option = JOptionPane.showOptionDialog(null, "Bem vindo ao Sistema Hospitalar!", "[Hospital] - In�cio", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			switch (option) {
			case 0:
				registrarPaciente(hospital, atendente);
				break;
			case 1:
				gerenciarRegistros(hospital);
				break;
			case 2:
				telaRelatorio(hospital);
				break;
			case 3:
				exit = true;
				break;
			}
		} while (!exit);
	}
	
	public static void telaRelatorio(Hospital hospital) {
		float mediaInfectados = Utils.checkMediaInfectados(hospital.getRegistros());
		float[] mediaEstados = {0, 0, 0}; // Leve / Medio / Grave
		float[] mediaSexo = {0, 0}; // Masculino, Feminino
		Utils.checkMediaEstadosInfectados(mediaEstados, hospital.getRegistros());
		Utils.checkMediaSexoInfectados(mediaSexo, hospital.getRegistros());
		mostrarMensagem("Relat�rio de casos de infec��o por Covid-19!\n\n"
					  + "M�dia de infectados: " + mediaInfectados * 100 + "%\n"
					  + "M�dia de estado dos pacientes: \n" 
					  + "Leve: " + mediaEstados[0] * 100 + "%\n"
					  + "Medio: " + mediaEstados[1] * 100 + "%\n"
					  + "Grave: " + mediaEstados[2] * 100 + "%\n"
					  + "Homens: " + mediaSexo[0] * 100 + "% | Mulheres: " + mediaSexo[1] * 100 + "%\n"
					  , "[Hospital] - Relat�rio");
	}

	public static void efetuarLogin(Hospital hospital) {
		final JTextField nomeCampo = new JTextField(10);
		final JPasswordField senhaCampo = new JPasswordField(10);
		JPanel pane = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0);
		pane.add(new JLabel("Login:"), gbc);
		gbc.gridy = 1;
		pane.add(new JLabel("Senha:"), gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.EAST;
		pane.add(nomeCampo, gbc);
		gbc.gridy = 1;
		pane.add(senhaCampo, gbc);

		boolean sucessfull = false;
		do {
			int reply = JOptionPane.showConfirmDialog(null, pane, "[Sistema] - Fazer login:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			if (reply == JOptionPane.OK_OPTION) {
				String login = nomeCampo.getText();
				String senha = new String(senhaCampo.getPassword());
				try {
					Atendente atendente = Utils.efetuarLogin(hospital.getAtendentes(), login, senha);
					telaAtendente(atendente, hospital);
				} catch (IllegalArgumentException e) {
					mostrarErro(e.getMessage());
				}
			}
			if (reply == JOptionPane.CANCEL_OPTION)
				sucessfull = true;
		} while(!sucessfull);
	}

	// M�todos de registros:

	public static void registrarAtendente(Hospital hospital) {
		final JTextField nomeCampo = new JTextField(20);
		nomeCampo.setToolTipText("Testeee");
		final JTextField rgCampo = new JTextField(20);
		final JTextField matriculaCampo = new JTextField(20);
		final JTextField loginCampo = new JTextField(20);
		final JPasswordField senhaCampo = new JPasswordField(20);
		JPanel pane = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0);
		pane.add(new JLabel("Nome:"), gbc);
		gbc.gridy = 1;
		pane.add(new JLabel("Rg:"), gbc);
		gbc.gridy = 2;
		pane.add(new JLabel("Matr�cula:"), gbc);
		gbc.gridy = 3;
		pane.add(new JLabel("Login:"), gbc);
		gbc.gridy = 4;
		pane.add(new JLabel("Senha:"), gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.EAST;
		pane.add(nomeCampo, gbc);
		gbc.gridy = 1;
		pane.add(rgCampo, gbc);
		gbc.gridy = 2;
		pane.add(matriculaCampo, gbc);
		gbc.gridy = 3;
		pane.add(loginCampo, gbc);
		gbc.gridy = 4;
		pane.add(senhaCampo, gbc);

		boolean sucessfull = false;
		do {
			int reply = JOptionPane.showConfirmDialog(null, pane, "[Hospital] - Registrar atendente:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			if (reply == JOptionPane.OK_OPTION) {

				try {
					String nome = nomeCampo.getText();
					String rg = rgCampo.getText();
					String matricula = matriculaCampo.getText();
					String login = loginCampo.getText();
					String senha = new String(senhaCampo.getPassword());

					hospital.criarAtendente(nome, rg, matricula, login, senha);
					mostrarMensagem("Registro de atendente efetuado com sucesso, fa�a o login para continuar!", "[Hospital] - Registro de Paciente");
					sucessfull = true;
				} catch (IllegalArgumentException e) {
					mostrarErro(e.getMessage());
					continue;
				}

			}
			if (reply == JOptionPane.CANCEL_OPTION)
				sucessfull = true;
		} while(!sucessfull);
	}

	public static void registrarPaciente(Hospital hospital, Atendente atendente) {
		final JTextField nomeCampo = new JTextField(30);
		final JTextField idadeCampo = new JTextField(30);
		final JTextField sexoCampo = new JTextField(30);
		final JTextField rgCampo = new JTextField(30);
		final JTextField bairroCampo = new JTextField(30);
		final JTextField comorbidadesCampo = new JTextField(30);
		final JTextField horarioCampo = new JTextField(30);
		final JTextField infeccaoCampo = new JTextField(30);
		final JTextField estadoCampo = new JTextField(30);
		JPanel pane = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0);
		pane.add(new JLabel("Nome:"), gbc);
		gbc.gridy = 1;
		pane.add(new JLabel("Idade (1~120):"), gbc);
		gbc.gridy = 2;
		pane.add(new JLabel("Sexo (M/F):"), gbc);
		gbc.gridy = 3;
		pane.add(new JLabel("Rg:"), gbc);
		gbc.gridy = 4;
		pane.add(new JLabel("Bairro:"), gbc);
		gbc.gridy = 5;
		pane.add(new JLabel("Comorbidades (Nenhum/Alguma):"), gbc);
		gbc.gridy = 6;
		pane.add(new JLabel("Hor�rio (09:00~18:00):"), gbc);
		gbc.gridy = 7;
		pane.add(new JLabel("Infectado (S/N):"), gbc);
		gbc.gridy = 8;
		pane.add(new JLabel("Estado (Leve/Medio/Grave):"), gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.EAST;
		pane.add(nomeCampo, gbc);
		gbc.gridy = 1;
		pane.add(idadeCampo, gbc);
		gbc.gridy = 2;
		pane.add(sexoCampo, gbc);
		gbc.gridy = 3;
		pane.add(rgCampo, gbc);
		gbc.gridy = 4;
		pane.add(bairroCampo, gbc);
		gbc.gridy = 5;
		pane.add(comorbidadesCampo, gbc);
		gbc.gridy = 6;
		pane.add(horarioCampo, gbc);
		gbc.gridy = 7;
		pane.add(infeccaoCampo, gbc);
		gbc.gridy = 8;
		pane.add(estadoCampo, gbc);

		boolean sucessfull = false;
		do {
			int reply = JOptionPane.showConfirmDialog(null, pane, "[Hospital] - Registrar paciente:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			if (reply == JOptionPane.OK_OPTION) {

				try {
					String nome = nomeCampo.getText();
					String idade = idadeCampo.getText();
					String sexo = sexoCampo.getText();
					String rg = rgCampo.getText();
					String bairro = bairroCampo.getText();
					String comorbidades = comorbidadesCampo.getText();
					String infeccao = infeccaoCampo.getText();
					String horario = horarioCampo.getText();
					String estado = estadoCampo.getText();

					Paciente paciente = hospital.criarPaciente(nome, Integer.parseInt(idade), sexo, bairro, rg, comorbidades);
					hospital.criarRegistro(Utils.setarIdRegistro(hospital.getRegistros()), paciente, horario, infeccao, estado, atendente);
					mostrarMensagem("Registro de paciente efetuado com sucesso!", "[Hospital] - Registro de Paciente");
					sucessfull = true;
				} catch (IllegalArgumentException e) {
					mostrarErro(e.getMessage());
					continue;
				}

			}
			if (reply == JOptionPane.CANCEL_OPTION)
				sucessfull = true;
		} while(!sucessfull);
	}
	
	public static void gerenciarRegistros(Hospital hospital) {
		Object[] listaNomePacientes = Utils.getListaNomePacientesRegistros(hospital).toArray();
		String registroDesejado = selecionarDaLista(listaNomePacientes, "Selecione o registro desejado: ", "[Hospital] - Gerenciar Registros");
		String numeroRegistro = "" + registroDesejado.charAt(0);
		Registro registro = hospital.getRegistroPorId(Integer.parseInt(numeroRegistro));
		
		final JTextField nomeCampo = new JTextField(registro.getPaciente().getNome(), 20);
		final JTextField idadeCampo = new JTextField(Integer.toString(registro.getPaciente().getIdade()));
		final JTextField sexoCampo = new JTextField(registro.getPaciente().getSexo());
		final JTextField rgCampo = new JTextField(registro.getPaciente().getRg());
		final JTextField bairroCampo = new JTextField(registro.getPaciente().getBairro());
		final JTextField comorbidadesCampo = new JTextField(registro.getPaciente().getComorbidades());
		final JTextField horarioCampo = new JTextField(registro.getHorario());
		String checkInfeccao;
		if (registro.isInfeccao()) {
			checkInfeccao = "S";
		} else {
			checkInfeccao = "N";
		}
		final JTextField infeccaoCampo = new JTextField(checkInfeccao);
		final JTextField estadoCampo = new JTextField(registro.getEstado());
		JPanel pane = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0);
		pane.add(new JLabel("Nome:"), gbc);
		gbc.gridy = 1;
		pane.add(new JLabel("Idade (1~120):"), gbc);
		gbc.gridy = 2;
		pane.add(new JLabel("Sexo (M/F):"), gbc);
		gbc.gridy = 3;
		pane.add(new JLabel("Rg:"), gbc);
		gbc.gridy = 4;
		pane.add(new JLabel("Bairro:"), gbc);
		gbc.gridy = 5;
		pane.add(new JLabel("Comorbidades (Nenhum/Alguma):"), gbc);
		gbc.gridy = 6;
		pane.add(new JLabel("Hor�rio (09:00~18:00):"), gbc);
		gbc.gridy = 7;
		pane.add(new JLabel("Infectado (S/N):"), gbc);
		gbc.gridy = 8;
		pane.add(new JLabel("Estado (Leve/Medio/Grave):"), gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.EAST;
		pane.add(nomeCampo, gbc);
		gbc.gridy = 1;
		pane.add(idadeCampo, gbc);
		gbc.gridy = 2;
		pane.add(sexoCampo, gbc);
		gbc.gridy = 3;
		pane.add(rgCampo, gbc);
		gbc.gridy = 4;
		pane.add(bairroCampo, gbc);
		gbc.gridy = 5;
		pane.add(comorbidadesCampo, gbc);
		gbc.gridy = 6;
		pane.add(horarioCampo, gbc);
		gbc.gridy = 7;
		pane.add(infeccaoCampo, gbc);
		gbc.gridy = 8;
		pane.add(estadoCampo, gbc);
		gbc.gridy = 9;
		pane.add(new JLabel(""), gbc);
		gbc.gridy = 10;
		gbc.gridx = 0;
		pane.add(new JLabel("\nRegistrado por: " + registro.getAtendente().getNome()), gbc);

		boolean sucessfull = false;
		do {
			int reply = JOptionPane.showConfirmDialog(null, pane, "[Hospital] - Gerenciar registro:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			if (reply == JOptionPane.OK_OPTION) {

				try {
					String nome = nomeCampo.getText();
					String idade = idadeCampo.getText();
					String sexo = sexoCampo.getText();
					String rg = rgCampo.getText();
					String bairro = bairroCampo.getText();
					String comorbidades = comorbidadesCampo.getText();
					String infeccao = infeccaoCampo.getText();
					String horario = horarioCampo.getText();
					String estado = estadoCampo.getText();

					Paciente paciente = new Paciente(nome, Integer.parseInt(idade), sexo, bairro, rg, comorbidades);
					registro.setEstado(estado);
					registro.setHorario(horario);
					registro.setInfeccao(infeccao);
					registro.setPaciente(paciente);
					mostrarMensagem("Gerenciamento de registro efetuado com sucesso!", "[Hospital] - Gerenciamento de Registro");
					sucessfull = true;
				} catch (IllegalArgumentException e) {
					mostrarErro(e.getMessage());
					continue;
				}

			}
			if (reply == JOptionPane.CANCEL_OPTION)
				sucessfull = true;
		} while(!sucessfull);
	}
}
