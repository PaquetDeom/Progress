package fr.paquet.etablissement;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.w3c.dom.Element;

import fr.paquet.commun.Diplome;

@Entity
@Table(name = "CLASSE")
public class Classe extends XMLBean {

	/**
	 * @author Nathanaël
	 * 
	 *         La class represente une classe de l'education nationale<br/>
	 */

	@ManyToOne
	private Etablissement etab = null;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<Eleve> eleves = null;

	@ManyToMany
	private List<Professeur> professeurs = null;

	@JoinColumn(name = "CLDIID")
	@OneToOne
	private Diplome diplome = null;

	@Id
	@Column(name = "CLCLID", length = 25)
	private String code = null;

	@Column(name = "CLCLFO", length = 25)
	private String formation = null;

	@Column(name = "CLCLINTITULE", length = 100)
	private String intitule = null;

	@JoinColumn(name = "CLEMID")
	@OneToOne
	private EmploiDuTemps edt = null;

	/**
	 * Constructeur de la class pour la gestion de la DB<br/>
	 */
	public Classe() {
		super(null, null);
	}
	
	public Classe(Element elt, Etablissement etab) throws Exception {
		super(elt, null);
		setCode(elt.getAttribute("CODE_MEF"));
		setFormation(getStringFromXml("FORMATION"));
		setIntitule(getStringFromXml("LIBELLE_LONG"));
		setEtablissement(etab);
	}

	/**
	 * Constructeur de la class<br/>
	 * 
	 * @param code
	 * @throws Exception
	 *             Le code est manquant<br/>
	 */
	public Classe(Etablissement etab, String intitule, String code) throws Exception {
		this();
		setCode(code);
		setEtablissement(etab);
		setIntitule(intitule);
	}

	public void setEtablissement(Etablissement etab) {
		this.etab = etab;
	}

	public void setCode(String code) throws Exception {
		if (code == null)
			throw new Exception("Code manquant");
		this.code = code.trim().toUpperCase();
	}

	public void setDiplome(Diplome diplome) {
		this.diplome = diplome;
	}

	public void setEdt(EmploiDuTemps edt) {
		this.edt = edt;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public void setFormation(String formation) {
		this.formation = formation.trim();
	}

	public void addEleve(Eleve eleve) {
		getEleves().add(eleve);
	}

	public void addProfesseur(Professeur prof) {
		getProfesseurs().add(prof);
	}

	public Etablissement getEtablissement() throws Exception {
		if (etab == null)
			throw new Exception("Une classe doit faire partie d'un établissement");
		return etab;
	}

	/**
	 * 
	 * @return Le code de la classe sans espace a droite et a gauche et en
	 *         majuscule<br/>
	 * 
	 *         correspond a l'id de la DB<br/>
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 
	 * @return La liste des eleves d'une classe<br/>
	 */
	public List<Eleve> getEleves() {
		if (eleves == null)
			eleves = new ArrayList<Eleve>();
		return eleves;
	}

	/**
	 * 
	 * @return La liste des professeurs d'une classe<br/>
	 */
	public List<Professeur> getProfesseurs() {
		if (professeurs == null)
			professeurs = new ArrayList<Professeur>();
		return professeurs;
	}

	/**
	 * 
	 * @return L'intitule de la classe<br/>
	 */
	public String getIntitule() {
		return intitule;
	}

	/**
	 * 
	 * @return Le diplome prepare par la classe<br/>
	 */
	public Diplome getDiplome() {
		return diplome;
	}

	/**
	 * 
	 * @return L'emploi du temps de la classe<br/>
	 */
	public EmploiDuTemps getEdt() {
		return edt;
	}

	public String getFormation() {
		return formation;
	}

}
