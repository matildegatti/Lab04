package it.polito.tdp.lab04;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> combobox;

    @FXML
    private TextField txtmatricola;
    
    @FXML
    private Button btncercaiscritti;

    @FXML
    private TextField txtnome;

    @FXML
    private TextField txtcognome;

    @FXML
    private TextArea txtrisultato;
    

    @FXML
    void doCheck(ActionEvent event) {
    	int matricola;
    	try {
    		matricola=Integer.parseInt(txtmatricola.getText());
    	}catch(NumberFormatException ne) {
    		txtrisultato.setText("Matricola non valida");
    		return;
    	}catch(NullPointerException e) {
    		txtrisultato.setText("Inserisci matricola");
    		return;
    	}
    
    	Studente s=this.model.getStudente(matricola);
    	if(s==null) {
    		txtrisultato.setText("Matricola non esistente");
    		return;
    	}
    	
    	this.txtcognome.setText(s.getCognome());
    	this.txtnome.setText(s.getNome());
    	
    }

    @FXML
    void doIscrivi(ActionEvent event) {

    }

    @FXML
    void doReset(ActionEvent event) {

    }

    @FXML
    void researchcorsi(ActionEvent event) {
    	int matricola;
    	try {
    		matricola=Integer.parseInt(txtmatricola.getText());
    	}catch(NumberFormatException ne) {
    		txtrisultato.setText("Matricola non valida");
    		return;
    	}catch(NullPointerException e) {
    		txtrisultato.setText("Inserisci matricola");
    		return;
    	}
    	
    	Studente s=this.model.getStudente(matricola);
    	if(s==null) {
    		txtrisultato.setText("Matricola non esistente");
    		return;
    	}
    	
    	if(this.model.getCorsiStudente(s).size()==0) {
    		this.txtrisultato.setText("Matricola non iscritta a nessun corso");
    		return;
    	}
    	for(Corso c:this.model.getCorsiStudente(s)) {
    		this.txtrisultato.appendText(c.getCodins()+"  "+c.getNumeroCrediti()+"  "+c.getNome()+"  "+c.getPeriodoDidattico()+"\n");
    	}
    }

    @FXML
    void researchiscritti(ActionEvent event) {
    	String nomecorso=this.combobox.getValue().getNome(); 	
    	if(nomecorso==null) {
    		txtrisultato.setText("Selezionare un corso");
    		return;
    	}
    	Corso c=this.model.getCorso(nomecorso);
    	if(c==null) {
    		txtrisultato.setText("Selezionare un corso");
    		return;
    	}
    	
    	List<Studente> studenti=this.model.getStudentiIscrittiAlCorso(c);
    	if(this.model.getStudentiIscrittiAlCorso(c).size()==0) {
    		this.txtrisultato.setText("Non ci sono studenti iscritti al corso");
    	}
    	for(Studente s:studenti) {
    		this.txtrisultato.appendText(""+s.getMatricola()+"  "+s.getNome()+"  "+s.getCognome()+"  "+s.getCDS()+"\n");
    	}
    
    }

    @FXML
    void initialize() {
        assert combobox != null : "fx:id=\"combobox\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtmatricola != null : "fx:id=\"txtmatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtnome != null : "fx:id=\"txtnome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtcognome != null : "fx:id=\"txtcognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtrisultato != null : "fx:id=\"txtrisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btncercaiscritti != null : "fx:id=\"btncercaiscritti\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Model model) {
		this.model=model;
		List<String> nomicorsi=new LinkedList<String>();
		for(Corso c:this.model.getTuttiICorsi())
			nomicorsi.add(c.getNome());
		
		combobox.getItems().addAll(this.model.getTuttiICorsi());
		//combobox.getItems().add("");
	}
}
