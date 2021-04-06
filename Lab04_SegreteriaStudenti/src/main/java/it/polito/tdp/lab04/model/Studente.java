package it.polito.tdp.lab04.model;

public class Studente {
	
	private Integer matricola;
	private String nome;
	private String cognome;
	private String CDS;
	
	public Studente(Integer matricola, String nome, String cognome, String cDS) {
		super();
		this.matricola = matricola;
		this.nome = nome;
		this.cognome = cognome;
		CDS = cDS;
	}
	public Integer getMatricola() {
		return matricola;
	}
	public void setMatricola(Integer matricola) {
		this.matricola = matricola;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getCDS() {
		return CDS;
	}
	public void setCDS(String cDS) {
		CDS = cDS;
	}
	
	public String toString() {
		return matricola+"\t\t"+nome+"\t\t\t\t"+cognome+"\t\t"+CDS;
	}


}
