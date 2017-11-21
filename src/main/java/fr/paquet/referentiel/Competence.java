package fr.paquet.referentiel;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @author Nathanael
 * 
 *         La classe competence represente une competence du referentiel de
 *         l'education <br/>
 *         mode d'utilisation : new Competence("C2.5", "Etablir et representer")
 *         
 * 
 */
@Entity
@Table(name = "COMPETENCE")
public class Competence {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	@OneToMany
	private int id;

	@ManyToOne()
	private Capacite cap = null;

	@ManyToOne()
	private Unite unite = null;

	@Column(name = "CODE")
	private int code = 0;

	@Column(name = "INTITULE", length = 200)
	private String intitule;

	@OneToMany(mappedBy = "comp", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CompetenceIntermediaire> competencesIntermediaires;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<Condition> conditions;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<CritereEvaluation> critereEvaluations;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<Savoir> savoirs;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<Tache> taches;

	/**
	 * Contructeur de la classe Competence<br/>
	 * 
	 * @param Capacite
	 *            La classe competence peut charger une liste de competences
	 *            dans une capacite<br/>
	 * 
	 * @param Unite
	 *            La classe competence charge une liste de competences dans
	 *            Unite<br/>
	 * 
	 * @param UnCode
	 *            code de la comp�tence compos� du code Capacite . et d'un
	 *            Chiffre<br/>
	 *            exemple : C1.1, C3.1, etc...
	 * @param UnIntitule
	 *            le libell� de la comp�tence : <br/>
	 *            exemple : "Etablir les docuements de suivi de r�alisation"
	 *            <br/>
	 *            ATTENTION : l'intitul� doit commencer par un verbe
	 * @throws Exception
	 *             si le code est �gale � 0
	 */

	public Competence(Capacite cap, Unite unt, int UnCode, String UnIntitule) throws Exception  {

		this();
		setCode(UnCode);
		setIntitule(UnIntitule);
		setCapacite(cap);
		unt.addCompetence(this);
		setUnite(unt);
		if (getCapacite() != null)
			cap.addCompetence(this);
	}

	public Competence(Unite unt, int UnCode, String UnIntitule) throws Exception {

		this(null, unt, UnCode, UnIntitule);
		// TODO Competence sans capacite
	}

	public Competence() {
		super();
	}

	private void setCapacite(Capacite cap) {
		this.cap = cap;
	}

	private void setUnite(Unite unt) {
		this.unite = unt;
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

		this.intitule = intitule.trim();
	}

	public void setId(int id) {
		this.id = id;
	}

	public void addCritereEvaluation(CritereEvaluation crit) {
		getCritereEvaluations().add(crit);
	}

	/**
	 * public Condition addCondition(String uneCondition) {
	 * getConditions().add(uneCondition); return getConditions(); }
	 */

	public void addCondition(Condition condition) {
		getConditions().add(condition);
	}

	public void addCompetenceIntermediaire(CompetenceIntermediaire compint) {
		getCompetencesIntermediaires().add(compint);
	}

	public void addSavoir(Savoir sav) throws Exception {
		
		if(sav.getReferentiel()!=getCapacite().getReferentiel())
			throw new Exception("Ce savoir ne fait pas parti du referentiel de la competence");
		getSavoirs().add(sav);
	}

	public void addTache(Tache tac) throws Exception {
		
		if(tac.getActivite().getFonction().getRap().getReferentiel()!=getCapacite().getReferentiel())
			throw new Exception("La tache ne fait pas partie du même referentiel que la competence");
		getTaches().add(tac);
	}

	public CompetenceIntermediaire addCompetenceIntermediaire(int unCode, String unIntitule, int unNbEvaluation)
			throws Exception {
		return new CompetenceIntermediaire(this, unCode, unIntitule);
	}

	/**
	 * 
	 * @return Les Critere d'evaluation de la competence<br/>
	 */
	public List<CritereEvaluation> getCritereEvaluations() {
		if (critereEvaluations == null)
			critereEvaluations = new ArrayList<CritereEvaluation>();
		return critereEvaluations;
	}

	/**
	 * 
	 * @return Les conditions de la competence<br/>
	 */
	public List<Condition> getConditions() {
		if (conditions == null)
			conditions = new ArrayList<Condition>();
		return conditions;
	}

	/**
	 * 
	 * @return La liste de competences intermediaires de la competence<br/>
	 */
	public List<CompetenceIntermediaire> getCompetencesIntermediaires() {
		if (competencesIntermediaires == null)
			competencesIntermediaires = new ArrayList<CompetenceIntermediaire>();
		return competencesIntermediaires;
	}

	/**
	 * 
	 * @return La capacite a laquelle appartient la competence<br/>
	 */
	public Capacite getCapacite() {
		return cap;
	}

	/**
	 * 
	 * @return L'unite diplomante de la competence<br/>
	 */
	public Unite getUnite() {
		return unite;
	}

	/**
	 * 
	 * @return Si capacite est non null. Le code de la capacite + le code de la
	 *         competence ex (C1.2)<br/>
	 * @return Autrement. "C" + le code de la competence ex (C1, C2...)<br/>
	 */
	public String getCode() {

		if (getCapacite() != null)
			return getCapacite().getCode() + "." + code;
		else
			return "C" + getCode();
	}

	/**
	 * 
	 * @return L'intitule sans espace a gauche et a droite<br/>
	 */
	public String getIntitule() {
		return intitule;
	}

	/**
	 * 
	 * @return La liste des savoirs associes a la competence<br/>
	 * @throws Verifie que les savoirs et les competences font partis du mçeme referentiel<br/>
	 */
	public List<Savoir> getSavoirs() {
		if (savoirs == null)
			savoirs = new ArrayList<Savoir>();
		return savoirs;
	}

	/**
	 * 
	 * @return LA liste des taches associees a la competence<br/>
	 * @throws Verifie que les taches et les competences font partis du meme referentiel<br/>
	 */
	public List<Tache> getTaches() {
		if (taches == null)
			taches = new ArrayList<Tache>();
		return taches;
	}

	/**
	 * 
	 * @return L'Id pour la gestion de la Db<br/>
	 */
	public int getId() {
		return id;
	}

	public String toString() {
		return getCode() + ":" + getIntitule();
	}
}
