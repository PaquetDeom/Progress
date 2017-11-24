package fr.paquet.etablissement;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.w3c.dom.Element;

@Entity
@Table(name = "PROFESSEUR")
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "PFPFID")),
		@AttributeOverride(name = "nom", column = @Column(name = "PFPFNOM", length = 20)),
		@AttributeOverride(name = "prenom", column = @Column(name = "PFPFPRENOM", length = 50)),
		@AttributeOverride(name = "masculin", column = @Column(name = "PFPFSEXE")) })
public class Professeur extends Personne implements AEmploiDuTemps {

	/**
	 * @author Nathanaël
	 * 
	 *         La class represente un professeur de l'education nationale<br/>
	 */

	@JoinColumn(name = "PFEMID")
	@OneToOne
	private EmploiDuTemps edt = null;

	@JoinColumn(name = "PFCLID")
	@OneToMany
	private List<Classe> classes = null;

	@Column(name = "PFPFRE", length = 50)
	private String recrutement = null;

	@Column(name = "PFPFDI", length = 50)
	private String discipline = null;

	@Column(name = "PFPFLO", length = 50)
	private String login = null;

	@JoinColumn(name = "PFCLID")
	@OneToOne
	private Classe classePrincipale = null;

	/**
	 * Constructeur pour la gestion de la DB<br/>
	 */
	public Professeur() {
		super();
	}

	/**
	 * Constructeur creation de l'objet a partir d'un*.xml<br/>
	 * 
	 * @param elt
	 * @throws Exception
	 */
	public Professeur(Element elt) throws Exception {
		super(elt);
		setNom(getStringFromXml("NOM").trim().toUpperCase());
		setPrenom(getStringFromXml("PRENOM").trim().toUpperCase());
		setDiscipline(getStringFromXml("DISCIPLINE"));
		setRecrutement(getStringFromXml("DISC_RECRU"));

		if (getStringFromXml("CIVILITE").equals("M."))
			setSexe(true);
		else
			setSexe(false);
	}

	/**
	 * Constructeur de la class
	 * 
	 * @param concours
	 *            concours de l'education nationale<br/>
	 * @param grade
	 *            grade du prof<br/>
	 * @param spe
	 *            specialite d'enseignement<br/>
	 * @throws Exception
	 *             si la specialite est manquante<br/>
	 * 
	 */
	public Professeur(String recrutement) throws Exception {
		this();
		setRecrutement(recrutement);

	}

	public void setRecrutement(String recrutement) {
		this.recrutement = recrutement.trim().toUpperCase();
	}

	public void setDiscipline(String discipline) {
		this.discipline = discipline.trim().toUpperCase();
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setClassePrincipale(Classe classePrincipale) {
		this.classePrincipale = classePrincipale;
	}

	public void addClasse(Classe classe) {
		getClasses().add(classe);
	}

	/**
	 * 
	 * @return Les classes d'un professeur<br/>
	 */
	public List<Classe> getClasses() {
		if (classes == null)
			classes = new ArrayList<Classe>();
		return classes;
	}

	/**
	 * 
	 * @return La classe si elle est unique d'un prof<br/>
	 */
	public Classe getClasse() {
		if (getClasses().size() == 1)
			return getClasses().get(0);
		return null;
	}

	/**
	 * 
	 * @return Le concours passé par le professeur sans espace a droite et a gauche
	 *         et en majuscule<br/>
	 *         exemple : "PLP"; "CERTIFIE"<br/>
	 */
	public String getRecrutement() {
		return recrutement;
	}

	/**
	 * 
	 * @return La discipline enseignée<br/>
	 */
	public String getDiscipline() {
		return discipline;
	}

	/**
	 * 
	 * @return Le login pour se connecter a Progress<br/>
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * 
	 * @return La classe dont le prof est prof principal<br/>
	 */
	public Classe getClassePrincipale() {
		return classePrincipale;
	}

	@Override
	public String toString() {
		return getNom() + getPrenom();
	}

	@Override
	public EmploiDuTemps getEdt() {
		if (edt == null)
			edt = new EmploiDuTemps();
		return edt;
	}

}
