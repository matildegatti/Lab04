package it.polito.tdp.lab04.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {

	private CorsoDAO corsodao;
	private StudenteDAO studdao;
	
	public Model() {
		corsodao=new CorsoDAO();
		studdao=new StudenteDAO();
	}
	
	public List<Corso> getTuttiICorsi(){
		return corsodao.getTuttiICorsi();
	}
	
	public List<Studente> getTuttiStudenti(){
		return studdao.getTuttiStudenti();
	}

	public Studente getStudente(int matricola) {
		for(Studente s:this.getTuttiStudenti()) {
			if(matricola==s.getMatricola())
				return s;
		}
		return null;
	}
	
	public Corso getCorso(String nomecorso) {
		for(Corso c:this.getTuttiICorsi()) {
			if(nomecorso.equals(c.getNome()))
				return c;
		}
		return null;
	}
	
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso){
		List<Studente> studenti=new LinkedList<Studente>();
		for(Integer i:this.corsodao.getStudentiIscrittiAlCorso(corso)) {
			for(Studente s:this.getTuttiStudenti()) {
				if(i==s.getMatricola()) {
					studenti.add(s);
				}
			}
		}
		return studenti;
	}
	
	public List<Corso> getCorsiStudente(Studente studente){
		List<Corso> corsi=new LinkedList<Corso>();
		for(String s:this.corsodao.getCorsiStudente(studente)){
			for(Corso c:this.getTuttiICorsi())
				if(s.equals(c.getCodins()))
					corsi.add(c);
		}
		return corsi;
	}
}
