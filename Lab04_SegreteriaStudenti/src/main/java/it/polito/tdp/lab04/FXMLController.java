package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class FXMLController {
	
	Model model = new Model();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> boxCorsi;

    @FXML
    private TextField txtMatricola;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextArea txtResult;
    
    @FXML //quando premo bottone --> compila automaticamente NC
    void giveNC(ActionEvent event) {
    	txtResult.clear();
    	
	    	// CONTROLLO DELL'INPUT
	    	String matricolaInserita = txtMatricola.getText();
	    	int matricola;
	    	try {
	    		matricola = Integer.parseInt(matricolaInserita);
	    	}catch (NumberFormatException ne) {
	    		txtResult.setText("Numero di MATRICOLA non valido");
	    		return;
	    	}catch (NullPointerException npe) { //se campo è nullo
	    		txtResult.setText("Devi inserire un numero di MATRICOLA");
	    		return;
	    	}
	    	String [] NC = this.model.compila(matricola);
	    	
	    	//matricola non trovata
	    	if(NC[0]==null)
	    		txtResult.setText("Non esiste uno studente con la matricola inserita");
	    	txtNome.setText(NC[0]);
	    	txtNome.setText(NC[1]);
	   
    }

    
    @FXML
    /**
     * Data LA MATRICOLA dello studente si cerca i CORSI
     * a cui è iscritto schiacciando il bottone apposito
     * @param event
     */
    void cercaCorsi(ActionEvent event) {
    	txtResult.clear();
    	
    	//controllo INPUT
    	String matricolaInserita = txtMatricola.getText();
    	Integer matricola;
    	try {
    		matricola = Integer.parseInt(matricolaInserita);
    	}catch (NumberFormatException ne) {
    		txtResult.setText("Numero di MATRICOLA non valido");
    		return;
    	}catch (NullPointerException npe) { //se campo è nullo
    		txtResult.setText("Devi inserire un numero di MATRICOLA");
    		return;
    	}
    	
    	//CONTROLLO CHE IL NUMERO DI MATRICOLA sia presente nel DB
    	String [] NC = this.model.compila(matricola);
    	//matricola non trovata
    	if(NC[0]==null)
    		txtResult.setText("Non esiste uno studente con la matricola inserita");
    	
    	//se studente esiste stampo i suoi CORSI
    	txtResult.setText(model.getCorsi(matricola)); 
    	
    }
    

    @FXML
    /**
     * Cerca ISCRITTI AL CORSO scelto in BOX
     * @param event
     */
    void cercaIscritti(ActionEvent event) {
    	txtResult.clear();
    	
    	// CONTROLLO DELL'INPUT
    	String corsoScelto;
  
    	try {
    		corsoScelto=boxCorsi.getValue(); //NOME del corso
    		if(corsoScelto.equals(""))
    			throw new NullPointerException();
    	}catch (NullPointerException npe) { //se campo è nullo
    		txtResult.setText("Devi selezionare un corso tra quelli proposti");
    		return;
    	}
    	
    	txtResult.setText(model.getIscritti(corsoScelto));
    }

    @FXML
    void iscrivi(ActionEvent event) {
    	txtResult.clear();
    	
    	// 1. CONTROLLO DELL'INPUT del CORSO
    	String corsoScelto;
    	try {
    		corsoScelto=boxCorsi.getValue(); //NOME del corso
    	}catch (NullPointerException npe) { //se campo è nullo
    		txtResult.setText("Devi selezionare un corso tra quelli proposti");
    		return;
    	}
    	// 2. CONTROLLO INPUT MATRICOLA
    	String matricolaInserita = txtMatricola.getText();
    	Integer matricola;
    	try {
    		matricola = Integer.parseInt(matricolaInserita);
    	}catch (NumberFormatException ne) {
    		txtResult.setText("Numero di MATRICOLA non valido");
    		return;
    	}catch (NullPointerException npe) { //se campo è nullo
    		txtResult.setText("Devi inserire un numero di MATRICOLA");
    		return;
    	}
    	//CONTROLLO CHE IL NUMERO DI MATRICOLA sia presente nel DB
    	String [] NC = this.model.compila(matricola);
    	//matricola non trovata
    	if(NC[0]==null)
    		txtResult.setText("Non esiste uno studente con la matricola inserita");
    	
    	//DA IMPLEMENTARE
    	boolean isIscritto = model.getIsIscritto(corsoScelto,  matricola);
    	if(isIscritto==true)
    		txtResult.setText("Lo studente risulta già iscritto a questo corso");
    	//non era iscritto, l'ho fatto adesso
    	else
    		txtResult.setText("Lo studente non era ancora iscritto al corso, "
    				+ "la procedura di iscrizione risulta ora completata");
    }

    @FXML
    void reset(ActionEvent event) {
    	txtMatricola.setText("");
    	txtNome.setText("");
    	txtCognome.setText("");
    }
    
    public void setModel (Model mod) {
    	this.model=mod;
    	boxCorsi.getItems().addAll(this.model.getNomiCorsi());
    }

    @FXML
    void initialize() {
        assert boxCorsi != null : "fx:id=\"boxCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}