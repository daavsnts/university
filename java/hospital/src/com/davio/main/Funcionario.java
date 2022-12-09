package com.davio.main;

abstract public class Funcionario {
	private String nome;
	private String rg;
	private String matricula;
	private String login;
	private String senha;
	
	// Constructor:
	Funcionario(String nome, String rg, String matricula, String login, String senha) {
		setNome(nome);
		setRg(rg);
		setMatricula(matricula);
		setLogin(login);
		setSenha(senha);
	}
	
	// Getters and Setters:
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		if (!nome.isEmpty()) {
			this.nome = nome;
		} else {
			throw new IllegalArgumentException("[Funcionario] - Nome inv�lido!");
		}
	}
	
	public String getRg() {
		return rg;
	}
	
	public void setRg(String rg) {
		if (!rg.isEmpty()) {
			this.rg = rg;
		} else {
			throw new IllegalArgumentException("[Funcionario] - RG inv�lido!");
		}
	}
	
	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String matricula) {
		if (!matricula.isEmpty()) {
			this.matricula = matricula;
		} else {
			throw new IllegalArgumentException("[Funcionario] - Matr�cula inv�lida!");
		}
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		if (!login.isEmpty()) {
			this.login = login;
		} else {
			throw new IllegalArgumentException("[Funcionario] - Login inv�lido!");
		}
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		if (!senha.isEmpty()) {
			this.senha = senha;
		} else {
			throw new IllegalArgumentException("[Funcionario] - Senha inv�lida!");
		}
	}
	
	@Override
	public String toString() {
		return this.nome;
	}
	
}
