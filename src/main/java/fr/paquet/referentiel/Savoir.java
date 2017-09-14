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
 *         La classe savoir represente un savoir d'un referentiel de l'education
 *         nationale<br/>
 *
 *         Mode d'utilisation : new Savoir("S1", "L'ENTREPRISE ET SON
 *         ENVIRONNEMENT").
 *
 */

@Entity
@Table(name = "SAVOIR")
public class Savoir {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	@OneToMany()
	private int id;

	@Column(name = "CODE", length = 20)
	private String code;

	@Column(name = "INTITULE", length = 200)
	private String intitule;

	@ManyToOne()
	private Referentiel referentiel = null;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<Competence> competences = null;

	@OneToMany(mappedBy = "sav", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<SavoirAssocie> savoirsAssocies;

	/**
	 * Contructeur de la classe Savoirs<br/>
	 * 
	 * @param ref
	 *            Un savoir est attache a un referentiel de l'education national
	 *            <br/>
	 * @param UnCode
	 *            code du savoir est compose de "S" et d'un Chiffre<br/>
	 *            exemple : "S1", "S2", etc...
	 * @param UnIntitule
	 *            le libelle du savoir : <br/>
	 *            exemple : "L'ENTREPRISE ET SON ENVIRONNEMENT" <br/>
	 * 
	 * @exception Code
	 *                Si le code est null ou ne commence pas par un "S"
	 */

	public Savoir(Referentiel ref, String unCode, String unIntitule) throws Exception {
		this();
		setCode(unCode);
		setIntitule(unIntitule);
		ref.addSavoir(this);
		setReferentiel(ref);
	}

	public Savoir() {
		super();
	}

	private void setReferentiel(Referentiel ref) {
		this.referentiel = ref;
	}

	private void setIntitule(String unIntitule) {

		this.intitule = unIntitule.trim().toUpperCase();

	}

	private void setCode(String unCode) throws Exception {
		if (unCode == null || !unCode.trim().toUpperCase().substring(0, 1).equals("S"))
			throw new Exception("CODE INVALIDE");

		this.code = unCode.trim().toUpperCase();

	}

	public void setId(int id) {
		this.id = id;
	}

	public void addSavoirAssocie(SavoirAssocie savAss) {
		getSavoirsAssocies().add(savAss);
	}

	public SavoirAssocie addSavoirAssocie(int unCode, String unIntitule) throws Exception {
		return new SavoirAssocie(this, unCode, unIntitule);
	}

	public void addCompetence(Competence comp) {
		getCompetences().add(comp);
	}

	/**
	 * 
	 * @return la lisye des savoirs associes au savoir<br/>
	 */
	public List<SavoirAssocie> getSavoirsAssocies() {
		if (savoirsAssocies == null)
			savoirsAssocies = new ArrayList<SavoirAssocie>();
		return savoirsAssocies;
	}

	/**
	 * 
	 * @return L'intitule du savoir en majuscule sans espace a droite et a
	 *         gauche<br/>
	 */
	public String getIntitule() {
		return intitule;
	}

	/**
	 * 
	 * @return Le code du savoir sous la forme "S" + 1, 2, 3...<br/>
	 */
	public String getCode() {
		return code;
	}

	// public SavoirAssocie getSavoirAssocie(String code) {
	// for (SavoirAssocie ci : getSavoirsAssocies()) {
	// if (ci.getCode().equals(code)) {
	// return ci;
	// }
	// }
	// return null;
	// }

	/**
	 * 
	 * @return La liste de competences lies au savoir<br/>
	 */
	public List<Competence> getCompetences() {
		if (competences == null)
			competences = new ArrayList<Competence>();
		return competences;
	}

	/**
	 * 
	 * @return Le referentiel auquel appertient savoir<br/>
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
