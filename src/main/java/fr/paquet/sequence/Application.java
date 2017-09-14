package fr.paquet.sequence;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import fr.paquet.progression.ConfigCompInt;
import fr.paquet.referentiel.Activite;
import fr.paquet.referentiel.Competence;
import fr.paquet.referentiel.CompetenceIntermediaire;
import fr.paquet.referentiel.Tache;

public class Application extends Execution {
		
	private List<Tache> taches = null;

	/**
	 * @author Nathanael
	 * 
	 *         La class represente une phase d'une sequence.
	 */

	/**
	 * La class herite de la class Execution.
	 */

	public Application() {

		super();
	}

	/**
	 * Constructeur de la class<br/>
	 * Application appartient a une seule phase<br/>
	 * 
	 * @param ph
	 */
	public Application(Phase ph) {
		this();
		setPhase(ph);
	}

	public void addTaches(Tache tac) throws Exception {

		if (getTachePossibles().contains(tac))
			taches.add(tac);
		else
			throw new Exception("Tâche invalid");

	}
	

	/**
	 * 
	 * @return La liste des competences intermediaires qui debutent pendant la
	 *         sequence<br/>
	 */
	public List<CompetenceIntermediaire> getCompInts() {

		Sequence seq = getPhase().getSequence();

		List<CompetenceIntermediaire> compInts = new ArrayList<CompetenceIntermediaire>();

		if (seq.getProgression() != null) {
			for (int i = 0; i < seq.getConfigCompInts().size(); i++) {
				ConfigCompInt cCInt = seq.getConfigCompInts().get(i);
				if (cCInt.getDebut() > seq.getDebut() || cCInt.getDebut() == seq.getDebut())
					compInts.add(cCInt.getCompetenceIntermediaire());
				else
					cCInt = null;

			}
			return compInts;
		}

		else
			return seq.getCompetenceIntermediaires();
	}

	/**
	 * 
	 * @return La liste des competences qui debutent pendant la sequence<br/>
	 */
	public List<Competence> getCompetences() {

		List<Competence> comps = new ArrayList<Competence>();

		for (int i = 0; i < getCompInts().size(); i++) {
			Competence comp = getCompInts().get(i).getCompetence();
			if (comps.contains(comp))
				comp = null;
			else
				comps.add(comp);
		}

		return comps;
	}

	/**
	 * 
	 * @return La liste des taches de l'application<br/>
	 */
	public List<Tache> getTaches() {
		if (taches == null)
			taches = new ArrayList<Tache>();
		return taches;
	}

	/**
	 * 
	 * @return La liste des activites possibles de application<br/>
	 */
	public List<Activite> getActivitePossibles() {

		List<Activite> acts = new ArrayList<Activite>();

		for (int i = 0; i < getTachePossibles().size(); i++) {
			Activite act = getTachePossibles().get(i).getActivite();
			if (acts.contains(act))
				act = null;
			else
				acts.add(act);
		}

		return acts;
	}

	/**
	 * 
	 * @return La liste des taches possibles de application<br/>
	 */
	public List<Tache> getTachePossibles() {

		List<Tache> tacs = new ArrayList<Tache>();

		for (int i = 0; i < getCompetences().size(); i++) {
			Competence comp = getCompetences().get(i);
			for (int n = 0; n < comp.getTaches().size(); n++) {
				Tache tac = comp.getTaches().get(n);
				if (tacs.contains(tac))
					tac = null;
				else
					tacs.add(tac);
			}
		}

		return tacs;
	}
	
	
}
