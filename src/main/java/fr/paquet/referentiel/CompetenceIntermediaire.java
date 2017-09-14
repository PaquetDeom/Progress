package fr.paquet.referentiel;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author Nathanael
 * 
 *         La classe CompeteceIntermediaire represente une competence
 *         intermediaire qui construit une competence d'un referentiel de
 *         l'education national<br/>
 *
 *         Mode d'utilisation : new CompetenceIntermediaire(Competence, 1,
 *         "Evaluer le cout", 3).
 */

@Entity
@Table(name = "COMPETENCE_INTERMEDIAIRE")
public class CompetenceIntermediaire {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;

	@ManyToOne()
	private Competence comp = null;

	@Column(name = "CODE")
	private int code;

	@Column(name = "INTITULE", length = 400)
	private String intitule;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<Condition> conditions = null;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<CritereEvaluation> critereEvaluations = null;

	/**
	 * Le constructeur de la classe :
	 * 
	 * @param comp
	 *            comp est de type Competence<br/>
	 * 
	 * @param unCode
	 *            Le code est un entier<br/>
	 *            La competence renvoie le code de la capacite + entier exemple
	 *            :<br/>
	 *            "C1.1" "C2.1"...<br/>
	 * 
	 * @param unIntitule
	 *            L'intitule de la competence intermediaire commence par un
	 *            verbe.<br/>
	 * 
	 * 
	 * @throws Exception
	 *             si le code de la competence intermediaire n'est pas un entier
	 *             positif.<br/>
	 *             renvoie "Code invalide".
	 */

	public CompetenceIntermediaire(Competence comp, int unCode, String unIntitule) throws Exception {
		this();
		setCode(unCode);
		setIntitule(unIntitule);
		comp.addCompetenceIntermediaire(this);
		setCompetence(comp);
	}

	public CompetenceIntermediaire() {
		super();
	}

	private void setCompetence(Competence comp) {
		this.comp = comp;
	}

	private void setCode(int code) throws Exception {

		if (code == 0)
			throw new Exception("Code Invalide");
		if (code < 0)
			this.code = -code;
		else

			this.code = code;
	}

	private void setIntitule(String intitule) {

		String[] tab = intitule.trim().split(" ");

		intitule = tab[0].trim().toUpperCase();

		for (int i = 0; i < tab.length; i++) {
			if (intitule.equals(tab[i].toUpperCase()))
				intitule = tab[i].toUpperCase();
			else
				intitule = intitule.concat(" ") + tab[i].toLowerCase();
		}

		this.intitule = intitule;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void addCritereEvaluation(CritereEvaluation crit) {
		if (critereEvaluations == null)
			critereEvaluations = new ArrayList<CritereEvaluation>();
		getCritereEvaluations().add(crit);
	}

	public void addCondition(Condition cond) {
		if (conditions == null)
			conditions = new ArrayList<Condition>();
		getConditions().add(cond);
	}

	/**
	 * Si isCritereGeneraux() est vrai<br/>
	 * 
	 * @return Les criteres d'evaluation de la competence<br/>
	 * 
	 *         autrement<br/>
	 * 
	 * @return Les criteres d'evaluation de la competence intermediaire<br/>
	 * 
	 */
	public List<CritereEvaluation> getCritereEvaluations() {
		if (isCriteresGeneraux() && critereEvaluations == null)
			return getCompetence().getCritereEvaluations();
		else
			return critereEvaluations;
	}

	/**
	 * Si critereEvaluations est null ou vide<br/>
	 * 
	 * @return true<br/>
	 */
	public boolean isCriteresGeneraux() {
		return critereEvaluations == null || critereEvaluations.size() == 0;
	}

	/**
	 * Si isConditionGenerale() est vrai<br/>
	 * 
	 * @return Les conditions generales de la competence<br/>
	 * 
	 *         autrement<br/>
	 * 
	 * @return Les conditions generales de la competence intermediaire<br/>
	 * 
	 */
	public List<Condition> getConditions() {
		if (isConditionsGenerale() && conditions == null)
			return getCompetence().getConditions();
		else
			return conditions;
	}

	/**
	 * Si conditions est null ou vide<br/>
	 * 
	 * @return true<br/>
	 */
	public boolean isConditionsGenerale() {
		return conditions == null || conditions.size() == 0;
	}

	public String getVerbe() {

		String[] tab = this.intitule.split(" ");

		return tab[0].toUpperCase();
	}

	/**
	 * 
	 * @return La competence de la competence intermediaire<br/>
	 */
	public Competence getCompetence() {
		return comp;
	}

	/**
	 * 
	 * @return Le code de la competence + "." + le code de la competence
	 *         intermediaire<br/>
	 *         exemple : C2.2.5...
	 */
	public String getCode() {

		return getCompetence().getCode() + "." + code;
	}

	/**
	 * 
	 * @return l'intitule avec le premier mot en majuscule<br/>
	 */
	public String getIntitule() {

		return intitule;
	}

	/**
	 * 
	 * @return L'Id pour la gestion de la Db<br/>
	 */
	public int getId() {
		return id;
	}

}
