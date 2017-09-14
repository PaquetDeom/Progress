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

@Entity
@Table(name = "PROFESSEUR")
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "PFPFID")),
		@AttributeOverride(name = "nom", column = @Column(name = "PFPFNOM", length = 20)),
		@AttributeOverride(name = "prenom", column = @Column(name = "PFPFPRENOM", length = 50)),
		@AttributeOverride(name = "dateEntree", column = @Column(name = "PFPFDTENTREE")),
		@AttributeOverride(name = "dateSortie", column = @Column(name = "PFPFDTSORTIE")),
		@AttributeOverride(name = "dateDeNaissance", column = @Column(name = "PFPFDTNAISSANCE")),
		@AttributeOverride(name = "masculin", column = @Column(name = "PFPFSEXE")) })
public class Professeur extends Personne implements ACoordonnee, AEmploiDuTemps {

	/**
	 * @author Nathanaël
	 * 
	 *         La class represente un professeur de l'education nationale<br/>
	 */

	@JoinColumn(name = "PFCOID")
	@OneToOne
	private Coordonnee coordonnee = null;

	@JoinColumn(name = "PFEMID")
	@OneToOne
	private EmploiDuTemps edt = null;

	@JoinColumn(name = "PFCLID")
	@OneToMany
	private List<Classe> classes = null;

	@Column(name = "PFPFCONCOURS", length = 10)
	private String concours = null;

	@Column(name = "PFPFGRADE", length = 10)
	private String grade = null;

	@Column(name = "PFPFSPE", length = 10)
	private String spe = null;

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
	public Professeur(String concours, String grade, String spe) throws Exception {
		this();
		setConcours(concours);
		setGrade(grade);
		setSpecialite(spe);
	}

	public Professeur(String spe) throws Exception {
		this(null, "Contractuel", spe);
		setSpecialite(spe);
	}

	public void setSpecialite(String spe) throws Exception {
		if (spe == null)
			throw new Exception("Manque specialite");
		this.spe = spe.trim().toUpperCase();
	}

	public void setGrade(String grade) {
		this.grade = grade.trim().toUpperCase();
	}

	public void setConcours(String concours) {
		this.concours = concours.trim().toUpperCase();
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
	 * @return Le concours passé par le professeur sans espace a droite et a
	 *         gauche et en majuscule<br/>
	 *         exemple : "PLP"; "CERTIFIE"<br/>
	 */
	public String getConcours() {
		return concours;
	}

	/**
	 * 
	 * @return Le grade du professeur sans espace a droite et a gouche et en
	 *         majuscule<br/>
	 *         exemple : "CN"; "HC"<br/>
	 */
	public String getGrade() {
		return grade;
	}

	/**
	 * 
	 * @return La specialite du professeur sans espace a droite et a gauche et
	 *         en majuscule<br/>
	 *         exemple : "GIB"
	 */
	public String getSpecialite() {
		return spe;
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

	@Override
	public Coordonnee getCoordonnee() {
		if (coordonnee == null)
			coordonnee = new Coordonnee();
		return coordonnee;
	}

}
