package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Iscrizione;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	

	/*
	 * Ottengo tutti i corsi salvati nel DB
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT DISTINCT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Corso c = new Corso (rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				corsi.add(c);
			}

			conn.close();
			
			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore DB", e);
		}
	}
	
	/**
	 * Data la matricola = un intero "i" mi dà nome e cognome 
	 * per compilare automaticamente 
	 * @param corso
	 */
	public String[] getNC (int i) {
		String [] NC = new String[2];
		String nome = "";
		String cognome = "";
		
		final String sql = "SELECT nome,cognome FROM studente WHERE matricola = ?";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, i); //passo il numero di matricola alla query
			ResultSet rs = st.executeQuery();

			nome = rs.getString("nome");
			cognome = rs.getString("cognome");

			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException("Errore DB", e);
		}
		NC[0]=nome;
		NC[1]=cognome;
		return NC;
	}
	
	
	/**
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public void getCorso(Corso corso) {
		// TODO
	}
	

	/**
	 * Ottengo tutti gli studenti iscritti al Corso con questo codice
	 */
	public List <Studente> getStudentiIscrittiAlCorso(String nomeCorso) {
		List <Studente> iscritti = new LinkedList<Studente> ();
		
		final String sql = "SELECT * "
				+ "FROM studente AS s, iscrizione AS i, corso AS c "
				+ "WHERE ?=c.nome AND c.codins=i.codins AND s.matricola=i.matricola ";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, nomeCorso); //passo il codice corso alla query
			ResultSet rs = st.executeQuery();

			while(rs.next()) {
				Studente s = new Studente(rs.getInt("matricola"), rs.getString("cognome"), 
						rs.getString("nome"), rs.getString("CDS"));
				iscritti.add(s);
			}

			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException("Errore DB", e);
		}
	
		return iscritti;
	}

	
	/**
	 * Ottengo tutti gi corsi dello studente in questione
	 */
	public List <Corso> getCorsiDelloStudente(Integer matricola) {
		List <Corso> corsi = new LinkedList<Corso> ();
		
		final String sql = "SELECT c.codins,c.crediti,c.nome,c.pd "
				+ "FROM iscrizione AS i, corso AS c "
				+ "WHERE ?=i.matricola AND c.codins=i.codins ";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola); //passo il codice corso alla query
			ResultSet rs = st.executeQuery();

			while(rs.next()) {
				Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"), 
						rs.getString("nome"), rs.getInt("pd"));
				corsi.add(c);
			}

			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException("Errore DB", e);
		}
	
		return corsi;
	}
	
	/**
	 * Controllo se studente è già iscitto
	 * @param studente
	 * @param corso
	 * @return
	 */
	 
	public boolean inscriviStudenteACorso(Integer matricola, String nomeCorso) {
		final String sql = "select i.matricola, i.codins "
				+ "from iscrizione as i, corso as c "
				+ "where ?=c.nome AND c.codins=i.codins AND ?=i.matricola ";
		
		Iscrizione i;
		boolean iscritto=false;

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, nomeCorso); //passo il nome corso alla query
			st.setInt(2, matricola); //passo la matricola
			ResultSet rs = st.executeQuery();

			while(rs.next()) {
				i = new Iscrizione(rs.getString("codins"), rs.getInt("matricola"));
				if(i.getCodins()!=null)
					iscritto=true; //era già iscritto
			}

			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException("Errore DB", e);
		}
		
		if(iscritto==false) { //non e' ancora iscritto
			/********************* AGGIUNGERE UN RECORD DI ISCRIZIONE **********/
			final String sql2 = "INSERT INTO iscrizione VALUES ('?','(SELECT codins FROM corso WHERE nome=?)') ";
			
			try {
				Connection conn = ConnectDB.getConnection();
				PreparedStatement st = conn.prepareStatement(sql2);
				st.setString(2, nomeCorso); //passo il nome corso alla query
				st.setInt(1, matricola); //passo la matricola

				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException("Errore DB", e);
			}
			return false;
		} else
			return true;
			
	}


}
