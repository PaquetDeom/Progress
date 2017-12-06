package fr.paquet.etablissement;

import java.util.Date;

import javax.annotation.Nullable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.text.WordUtils;
import org.w3c.dom.Element;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Personne extends XMLBean {

	/**
	 * @author Nathanaël
	 * 
	 *         La class est mere de Eleve ; Proviseur ; Professeur ;
	 *         ResponsableEleve<br/>
	 */

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private long id = 0;

	@Column(name = "NOM")
	private String nom = null;

	@Column(name = "PRENOM")
	private String prenom = null;

	@Column(name = "DATE_DE_NAISSANCE")
	@Temporal(TemporalType.DATE)
	private Date dateDeNaissance = null;

	@Column(name = "DATE_ENTREE")
	@Temporal(TemporalType.DATE)
	private Date dateEntree = null;

	@Column(name = "DATE_SORTIE")
	@Temporal(TemporalType.DATE)
	private Date dateSortie = null;

	@Column(name = "SEXE")
	@Nullable
	private boolean masculin = true;

	/**
	 * Constructeur vide pour la data base<br/>
	 */
	protected Personne() {
		super(null, null);
	}
	
	protected Personne(Element elt, String rne) {
		super(elt, rne);
	}

	/**
	 * Constructeur de la class <br/>
	 * 
	 * @param nom
	 *            String nom de la personne<br/>
	 * @param prenoms
	 *            List<String> prenoms de la personne<br/>
	 * @param masculin
	 *            sexe de la personne<br/>
	 * @throws Exception
	 *             nom vide<br/>
	 *             prenoms vide<br/>
	 */
	public Personne(boolean masculin, String nom, String prenom) throws Exception {
		this();
		setNom(nom);
		setPrenom(prenom);
		setSexe(masculin);
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setSexe(boolean masculin) {
		this.masculin = masculin;
	}

	public void setDateDeNaissance(Date dateDeNaissance) {

		this.dateDeNaissance = dateDeNaissance;
	}

	public void setDateEntree(Date entree) throws Exception {

		if (entree == null || (getDateSortie() != null && entree.after(getDateSortie())))
			throw new Exception("La date d'entree est invalide");
		this.dateEntree = entree;
	}

	public void setDateSortie(Date sortie) throws Exception {

		if (sortie.before(getDateEntree()))
			throw new Exception("La date de sortie est invalide");
		this.dateSortie = sortie;
	}

	public void setNom(String nom) throws Exception {

		if (nom == null)
			throw new Exception("Nom ne peut pas être vide");
		this.nom = nom.trim().toUpperCase();
	}

	public void setPrenom(String prenom) {
		this.prenom = WordUtils.capitalize(prenom.trim());
	}

	/**
	 * 
	 * @return true = "homme"<br/>
	 * @return false = "femme"<br/>
	 */
	private boolean isSexe() {
		return masculin;
	}

	/**
	 * 
	 * @return Le nom de la personne sans espace a droite et a gauche et en
	 *         majuscule<br/>
	 */
	protected String getNom() {
		return nom;
	}

	/**
	 * 
	 * @return Le prenom de la personne sans espace a droite et a gauche<br/>
	 *         et avec la premiere lettre en majuscule<br/>
	 */
	protected String getPrenom() {

		return prenom;
	}

	/**
	 * 
	 * @return La date de naissance de la personne<br/>
	 */
	protected Date getDateDeNaissance() {
		return dateDeNaissance;
	}

	/**
	 * 
	 * @return La date d'entree de la personne<br/>
	 */
	protected Date getDateEntree() {
		return dateEntree;
	}

	/**
	 * 
	 * @return La date de sortie de la personne<br/>
	 */
	protected Date getDateSortie() {
		return dateSortie;
	}

	/**
	 * 
	 * @return Le sexe de la personne<br/>
	 */
	protected String getSexe() {
		if (isSexe())
			return "masculin";
		return "feminin";
	}

	/**
	 * 
	 * @return l'Id pour la gestion de la DB<br/>
	 */
	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return getNom() + " " + getPrenom();
	}

}
