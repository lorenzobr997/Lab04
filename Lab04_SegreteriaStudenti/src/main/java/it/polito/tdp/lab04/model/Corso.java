package it.polito.tdp.lab04.model;

public class Corso {
	
	private String codins;
	private int numeroCredit;
	private String nome;
	private int periodoDidattico;
	
	public Corso(String codins, int numeroCredit, String nome, int periodoDidattico) {
		super();
		this.codins = codins;
		this.numeroCredit = numeroCredit;
		this.nome = nome;
		this.periodoDidattico = periodoDidattico;
	}
	public String getCodins() {
		return codins;
	}
	public void setCodins(String codins) {
		this.codins = codins;
	}
	public int getNumeroCredit() {
		return numeroCredit;
	}
	public void setNumeroCredit(int numeroCredit) {
		this.numeroCredit = numeroCredit;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getPeriodoDidattico() {
		return periodoDidattico;
	}
	public void setPeriodoDidattico(int periodoDidattico) {
		this.periodoDidattico = periodoDidattico;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codins == null) ? 0 : codins.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + numeroCredit;
		result = prime * result + periodoDidattico;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Corso other = (Corso) obj;
		if (codins == null) {
			if (other.codins != null)
				return false;
		} else if (!codins.equals(other.codins))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (numeroCredit != other.numeroCredit)
			return false;
		if (periodoDidattico != other.periodoDidattico)
			return false;
		return true;
	}
	
	public String toString() {
		return codins+"	"+numeroCredit+"		"+nome+"	"+periodoDidattico;
	}

}
