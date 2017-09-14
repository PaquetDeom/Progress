package fr.paquet.sequence;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import fr.paquet.progression.ConfigCompInt;
import fr.paquet.referentiel.Activite;
import fr.paquet.referentiel.Competence;
import fr.paquet.referentiel.CompetenceIntermediaire;
import fr.paquet.referentiel.Tache;

public class Approfondissement extends Execution {

	/**
	 * @author Nathanael
	 * 
	 *         La class represente une phase d'une sequence.
	 */

	
	private List<Tache> taches = null;

	/**
	 * La classe herite de la class Execution.
	 */

	public Approfondissement() {

		super();

	}

	/**
	 * Constructeur de la class<br/>
	 * Approfondissement appartient a une seule phase<br/>
	 * 
	 * @param ph
	 */
	public Approfondissement(Phase ph) {
		this();
		setPhase(ph);
	}

	public void addTaches(Tache tac) throws Exception {

		if (getTachePossibles().contains(tac))
			taches.add(tac);
		else
			throw new Exception("Tache invalide");

	}

	/**
	 * 
	 * @return La liste des competences intermediaires qui ne debutent pas
	 *         pendant la sequence<br/>
	 */
	public List<CompetenceIntermediaire> getCompetenceIntermediaires() {

		List<CompetenceIntermediaire> compInts = new ArrayList<CompetenceIntermediaire>();
		compInts = getPhase().getValidation().getCompetenceIntermediaires();

		if (getPhase().getSequence().getProgression() != null) {
			for (int i = 0; i < getPhase().getSequence().getConfigCompInts().size(); i++) {
				ConfigCompInt cci = getPhase().getSequence().getConfigCompInts().get(i);
				if (cci.getDebut() < getPhase().getSequence().getDebut()
						&& cci.getFin() > getPhase().getSequence().getFin())
					compInts.add(cci.getCompetenceIntermediaire());
				else
					cci = null;
			}

			return compInts;
		} else

			return getPhase().getSequence().getCompetenceIntermediaires();

	}

	/**
	 * 
	 * @return La liste des competence de approfondissement<br/>
	 */
	public List<Competence> getCompetences() {

		List<Competence> comps = new ArrayList<Competence>();

		for (int i = 0; i < getCompetenceIntermediaires().size(); i++) {
			Competence comp = getCompetenceIntermediaires().get(i).getCompetence();
			if (comps.contains(comp))
				comp = null;
			else
				comps.add(comp);

		}

		return comps;
	}

	/**
	 * 
	 * @return La liste des taches posibles de approfondissemnt<br/>
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

	/**
	 * 
	 * @return La liste des activites possibles de approfondissement<br/>
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
	 * @return La liste des taches de approfondissement<br/>
	 */
	public List<Tache> getTaches() {
		if (taches == null)
			taches = new ArrayList<Tache>();
		return taches;
	}

}
