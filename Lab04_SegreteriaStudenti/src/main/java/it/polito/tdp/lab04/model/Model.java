package it.polito.tdp.lab04.model;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;

public class Model {
	
	
	private CorsoDAO DAO;
	
	public Model() {
		DAO=new CorsoDAO();
	}

	public List <String> getNomiCorsi() {
		List <String> corsiString = new LinkedList<String>();
		for( Corso ci: DAO.getTuttiICorsi())
			corsiString.add(ci.getNome());
		//aggiungo riga vuota
		corsiString.add("");
		return corsiString;
	}

	public String[] compila(int matricola) {
		return this.DAO.getNC(matricola);
	}

	/**
	 * Ottengo gli studenti iscritti in String 
	 * avendo il NOME del corso scelto dalla box
	 * @param corsoScelto
	 * @return
	 */
	public String getIscritti(String corsoScelto) { 
		//passo il codins del corso
		List <Studente> iscritti= DAO.getStudentiIscrittiAlCorso(corsoScelto);
		String iscrittiString ="";
		for(Studente si: iscritti)
			iscrittiString+=si.toString()+"\n";
		return iscrittiString;
	}

	/**
	 * Ottengo i corsi stampati in String
	 * dello studente la cui matricola viene scritta nell'interfaccia
	 * @param matricola
	 * @return
	 */
	public String getCorsi(Integer matricola) {
		List <Corso> corsi= DAO.getCorsiDelloStudente(matricola);
		String corsiString ="";
		for(Corso ci: corsi)
			corsiString+=ci.toString()+"\n";
		return corsiString;
	}
	
	/**
	 * Mi dice se studente è già iscritto oppure no
	 * Se non lo è, DAO lo iscrive
	 */
	public boolean getIsIscritto(String nomeCorso, Integer matricola) {
		
		boolean iscritto = DAO.inscriviStudenteACorso(matricola, nomeCorso);
		
		return iscritto;
		
	}

}
