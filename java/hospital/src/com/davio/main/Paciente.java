package com.davio.main;
public class Paciente {
	private String nome;
	private int idade;
	private String sexo;
	private String bairro;
	private String rg;
	private String comorbidades;
	
	// Constructor:
	public Paciente(String nome, int idade, String sexo, String bairro, String rg, String comorbidades) {
		setNome(nome);
		setIdade(idade);
		setSexo(sexo);
		setBairro(bairro);
		setRg(rg);
		setComorbidades(comorbidades);
	}

	// Getters and Setters:
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		if (!nome.isEmpty()) {
			this.nome = nome;
		} else {
			throw new IllegalArgumentException("[Paciente] - Nome vazio!");
		}
	}
	
	public int getIdade() {
		return idade;
	}
	
	public void setIdade(int idade) {
		if (idade > 0 && idade < 120) {
			this.idade = idade;
		} else {
			throw new IllegalArgumentException("[Paciente] - Idade inv�lida!");
		}
	}
	
	public String getSexo() {
		return sexo;
	}
	
	public void setSexo(String sexo) {
		if (!sexo.isEmpty()) {
			if (sexo.equals("M") || sexo.equals("F")) {
				this.sexo = sexo;
			} else {
				throw new IllegalArgumentException("[Paciente] - Sexo inv�lida!");
			}	
		} else {
			throw new IllegalArgumentException("[Paciente] - Sexo vazio!");
		}
	}
	
	public String getBairro() {
		return bairro;
	}
	
	public void setBairro(String bairro) {
		if (!bairro.isEmpty()) {
			this.bairro = bairro;
		} else {
			throw new IllegalArgumentException("[Paciente] - Bairro vazio!");
		}
	}
	
	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		if (!rg.isEmpty()) {
			this.rg = rg;
		} else {
			throw new IllegalArgumentException("[Paciente] - RG vazio!");
		}
		
	}
	
	public String getComorbidades() {
		return this.comorbidades;
	}
	
	public void setComorbidades(String comorbidades) {
		if (!comorbidades.isEmpty()) {
			this.comorbidades = comorbidades;
		} else {
			throw new IllegalArgumentException("[Paciente] - Comorbidade vazia!");
		}
	}
}
