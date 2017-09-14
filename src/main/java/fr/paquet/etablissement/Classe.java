package fr.paquet.etablissement;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import fr.paquet.commun.Diplome;

@Entity
@Table(name = "CLASSE")
public class Classe {

	/**
	 * @author Nathanaël
	 * 
	 *         La class represente une classe de l'education nationale<br/>
	 */

	@ManyToMany(fetch = FetchType.LAZY)
	private List<Eleve> eleves = null;

	@ManyToMany
	private List<Professeur> professeurs = null;

	@JoinColumn(name = "CLPFID")
	@OneToOne
	private Professeur profPrincipal = null;

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
		super();
	}

	/**
	 * Constructeur de la class<br/>
	 * 
	 * @param code
	 * @throws Exception
	 *             Le code est manquant<br/>
	 */
	public Classe(String intitule, String code) throws Exception {
		this();
		setCode(code);

		setIntitule(intitule);
	}

	public void setCode(String code) throws Exception {
		if (code == null)
			throw new Exception("Code manquant");
		this.code = code.trim().toUpperCase();
	}

	public void setProfPrincipal(Professeur profPrincipal) {
		this.profPrincipal = profPrincipal;
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
	 * @return Le professeur principal de la classe<br/>
	 */
	public Professeur getProfPrincipal() {
		return profPrincipal;
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
