package it.polito.tdp.lab04.model;

public class Iscrizione {
	
	private String codins; //corso
	private Integer matricola; //studente
	
	public Iscrizione(String codins, Integer matricola) {
		super();
		this.codins = codins;
		this.matricola = matricola;
	}
	
	public String getCodins() {
		return codins;
	}
	
	public void setCodins(String codins) {
		this.codins = codins;
	}
	
	public Integer getMatricola() {
		return matricola;
	}
	
	public void setMatricola(Integer matricola) {
		this.matricola = matricola;
	}

}
