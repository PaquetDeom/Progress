package fr.paquet.referentiel;

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

/**
 * 
 * @author Nathanael
 * 
 *         La classe capacite represente une capacite d'un referentiel de
 *         l'education <br/>
 *         national.
 * 
 *         Mode d'utilisation : new Capacite("C1", "INFORMER S'INFORMER").
 * 
 */

@Entity
@Table(name = "CAPACITE")
public class Capacite {

	@Id
	@GeneratedValue
	@OneToMany
	@Column(name = "ID")
	private int id;

	@Column(name = "CODE", length = 20)
	private String code;

	@Column(name = "INTITULE", length = 200)
	private String intitule;

	private Referentiel referentiel = null;

	@OneToMany(mappedBy = "cap", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Competence> competences;

	/**
	 * Contructeur de la classe Capacite<br/>
	 * 
	 * @param ref
	 *            Referentiel au quel est attach� la capacite<br/>
	 * 
	 * @param UnCode
	 *            code de la capacite compose de "C" et d'un Chiffre<br/>
	 *            exemple : C1, C3, etc...
	 * @param UnIntitule
	 *            le libelle de la capacite : <br/>
	 *            exemple : "INFORMER S'INFORMER" <br/>
	 * 
	 * @exception Code
	 *                Si le code est null ou ne commence pas par un C
	 */

	public Capacite(Referentiel ref, String unCode, String unIntitule) throws Exception {

		this();
		setCode(unCode);
		setIntitule(unIntitule);
		setReferentiel(ref);
		ref.addCapacite(this);
	}

	public Capacite() {
		super();
	}

	private void setReferentiel(Referentiel ref) {
		this.referentiel = ref;
	}

	private void setCode(String code) throws Exception {
		if (code == null || !code.trim().toUpperCase().substring(0, 1).equals("C"))
			throw new Exception("Code Invalide");

		this.code = code.trim().toUpperCase();
	}

	private void setIntitule(String intitule) {

		this.intitule = intitule.trim().toUpperCase();
	}

	public void setId(int id) {
		this.id = id;
	}

	public void addCompetence(Competence competence) {
		getCompetences().add(competence);
	}

	/**
	 * 
	 * @return La liste de competences d'une capacite<br/>
	 */
	public List<Competence> getCompetences() {
		if (competences == null)
			competences = new ArrayList<Competence>();
		return competences;
	}

	/**
	 * 
	 * @return Le code d'une capacite exempl : (C1 ; C2...)<br/>
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 
	 * @return L'intitule d'une capacite exemple (INFORMER ; S4INFORMER...)<br/>
	 */
	public String getIntitule() {
		return intitule;
	}

	/**
	 * 
	 * @return Le referentiel auquen appartient la capacite<br/>
	 */
	public Referentiel getReferentiel() {
		return referentiel;
	}

	/**
	 * 
	 * @return L'Id pour la gestion de la Db<br/>
	 */
	public int getId() {
		return id;
	}

}
