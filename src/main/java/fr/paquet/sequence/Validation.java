package fr.paquet.sequence;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import fr.paquet.evaluation.Evaluation;
import fr.paquet.progression.ConfigCompInt;
import fr.paquet.referentiel.Competence;
import fr.paquet.referentiel.CompetenceIntermediaire;
import fr.paquet.referentiel.Condition;
import fr.paquet.referentiel.CritereEvaluation;

/**
 * 
 * @author Nathanael
 *
 *         Validation est une phase d'une sequence. Elle est composee
 *         d'evaluation.
 */

@Entity
@Table(name = "VALIDATION")
public class Validation {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id = 0;

	@Column(name = "TITRE", length = 140)
	private String titre;

	@OneToOne
	private Phase phase;

	// @OneToMany(mappedBy = "valid", fetch = FetchType.LAZY)
	private List<Evaluation> eval;

	/**
	 * Constructeur de la class<br/>
	 * 
	 * @param phase
	 *            phase est de type Phase
	 * 
	 * @param titre
	 *            titre est de type Titre.
	 */

	public Validation() {
		super();

	}

	public Validation(Phase ph) {
		this();
		setPhase(ph);
	}

	private void setPhase(Phase ph) {
		this.phase = ph;
	}

	public void setTitre(String titre) {
		this.titre = titre.trim();

	}

	public void setId(int id) {
		this.id = id;
	}

	public void addEvaluation(Evaluation evaluation) {
		getEvaluations().add(evaluation);
	}

	public String getTitre() {
		return titre;
	}

	public Phase getPhase() {
		return phase;
	}

	public List<Evaluation> getEvaluations() {
		if (eval == null)
			eval = new ArrayList<Evaluation>();
		return eval;
	}

	public List<CompetenceIntermediaire> getCompetenceIntermediaires() {

		List<CompetenceIntermediaire> compInts = new ArrayList<CompetenceIntermediaire>();

		if (getPhase().getSequence().getProgression() != null) {

			for (int i = 0; i < getPhase().getSequence().getConfigCompInts().size(); i++) {
				ConfigCompInt cci = getPhase().getSequence().getConfigCompInts().get(i);
				if (cci.getFin() <= getPhase().getSequence().getFin()) {
					compInts.add(cci.getCompetenceIntermediaire());
				} else
					cci = null;
			}

			return compInts;
		} else
			return getPhase().getSequence().getCompetenceIntermediaires();

	}

	public List<Competence> getCompetences() {

		List<Competence> lComp = new ArrayList<Competence>();

		for (int i = 0; i < getCompetenceIntermediaires().size(); i++) {
			Competence comp = getCompetenceIntermediaires().get(i).getCompetence();
			if (lComp.contains(comp))
				comp = null;
			else
				lComp.add(comp);
		}
		return lComp;
	}

	public List<Condition> getConditions() {

		List<Condition> lCond = new ArrayList<Condition>();

		for (int i = 0; i < getCompetences().size(); i++) {
			Competence comp = getCompetences().get(i);
			for (int n = 0; n < comp.getConditions().size(); n++) {
				Condition cond = comp.getConditions().get(n);
				if (lCond.contains(cond))
					cond = null;
				else
					lCond.add(cond);
			}
		}

		for (int i = 0; i < getCompetenceIntermediaires().size(); i++) {
			CompetenceIntermediaire compInt = getCompetenceIntermediaires().get(i);
			for (int n = 0; n < compInt.getConditions().size(); n++) {
				Condition cond1 = compInt.getConditions().get(n);
				if (lCond.contains(cond1))
					cond1 = null;
				else
					lCond.add(cond1);
			}
		}

		return lCond;
	}

	public List<CritereEvaluation> getCritereEvaluations() {

		List<CritereEvaluation> lCrit = new ArrayList<CritereEvaluation>();

		for (int i = 0; i < getCompetences().size(); i++) {
			Competence comp = getCompetences().get(i);
			for (int n = 0; n < comp.getCritereEvaluations().size(); n++) {
				CritereEvaluation crit = comp.getCritereEvaluations().get(n);
				if (lCrit.contains(crit))
					crit = null;
				else
					lCrit.add(crit);
			}
		}

		for (int i = 0; i < getCompetenceIntermediaires().size(); i++) {
			CompetenceIntermediaire compInt = getCompetenceIntermediaires().get(1);
			for (int n = 0; n < compInt.getCritereEvaluations().size(); n++) {
				CritereEvaluation crit1 = compInt.getCritereEvaluations().get(n);
				if (lCrit.contains(crit1))
					crit1 = null;
				else
					lCrit.add(crit1);
			}

		}
		return lCrit;
	}

	public int getId() {
		return id;
	}

}
