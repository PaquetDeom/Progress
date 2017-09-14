package fr.paquet.referentiel;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TACHE")
public class Tache {

	/**
	 * 
	 * @author Nathanael
	 * 
	 *         La classe represente une tache d'un referentiel de l'education
	 *         nationale <br/>
	 * 
	 *         Mode d'utilisation : new Tache(Activite, "T1", "Mise en place..."
	 *         , 1, A)
	 * 
	 */

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;

	@ManyToOne()
	private Activite activite = null;

	@Column(name = "CODE", length = 10)
	private String code;

	@Column(name = "INTITULE", length = 200)
	private String intitule;

	@Column(name = "NIVEAU")
	private int niveau;

	@Column(name = "TYPE_RESPONSABILITE", length = 10)
	private String typeResponsabilite = null;

	@ManyToMany()
	private List<Competence> competences = null;

	/**
	 * Contructeur de la Tache :
	 * 
	 * @param uneActivite
	 *            Une tache appartient a une activite<br/>
	 * 
	 * @param unCode
	 *            Un code est compose de T + 1,2,3 exemple<br/>
	 *            "T1" "T2" "T33 ... <br/>
	 * 
	 * @param unIntitule
	 *            Intitule de la tache
	 * 
	 * @param unNiveau
	 *            Niveau de la tache
	 * 
	 * @param unTypeResponsabilite
	 *            Si le niveau de la tache est inferieur a 3 :<br/>
	 *            typeResponsabilite est null<br/>
	 *            Si le niveau de la tache est egale 3 :<br/>
	 *            typeResponsabilite peut etre :<br/>
	 *            "A" "B" "C" "AB" "BC" "AC" ou "ABC"<br/>
	 * 
	 * @throws Exception
	 *             Si le code est different de "T1" "T2" "T33 ...<br/>
	 *             "Code invalide"
	 */

	public Tache(Activite uneActivite, String unCode, String unIntitule, int unNiveau, String unTypeResponsabilite)
			throws Exception {
		this();
		setCode(unCode);
		setIntitule(unIntitule);
		setNiveau(unNiveau);
		setTypeResponsabilite(unTypeResponsabilite);
		uneActivite.addTache(this);
		setActivite(uneActivite);
	}

	public Tache() {
		super();
	}

	private void setActivite(Activite uneActivite) {
		this.activite = uneActivite;
	}

	private void setTypeResponsabilite(String unTypeResponsabilite) throws Exception {

		if (!unTypeResponsabilite.trim().toUpperCase().equals("A")
				&& !unTypeResponsabilite.trim().toUpperCase().equals("B")
				&& !unTypeResponsabilite.trim().toUpperCase().equals("C")
				&& !unTypeResponsabilite.trim().toUpperCase().equals("AB")
				&& !unTypeResponsabilite.trim().toUpperCase().equals("BC")
				&& !unTypeResponsabilite.trim().toUpperCase().equals("AC")
				&& !unTypeResponsabilite.trim().toUpperCase().equals("ABC"))
			throw new Exception("TYPE DE RESPONSABIBLITE INVALIDE");
		this.typeResponsabilite = unTypeResponsabilite.trim().toUpperCase();
	}

	private void setCode(String unCode) throws Exception {

		if (unCode == null || !unCode.trim().toUpperCase().substring(0, 1).equals("T"))
			throw new Exception("CODE INVALIDE");

		this.code = unCode.trim().toUpperCase();
	}

	private void setIntitule(String unIntitule) {
		this.intitule = unIntitule.trim();
	}

	private void setNiveau(int unNiveau) throws Exception {
		if (unNiveau != 1 && unNiveau != 2)
			throw new Exception("NIVEAU INVALIDE");

		this.niveau = unNiveau;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void addCompetence(Competence comp) {
		getCompetences().add(comp);
	}

	/**
	 * 
	 * @return L'activite a laquelle appartient la tache<br/>
	 */
	public Activite getActivite() {
		return activite;
	}

	/**
	 * 
	 * @return Le code de la Tache de type "T1"<br/>
	 * Si le code est different de "T" + 1, 2, 3...<br/>
	 * @throws "CODE INVALIDE"
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 
	 * @return "-" + l'intitule de la tache sans espace a droite et a gauche<br/>
	 */
	public String getIntitule() {
		return "- " + intitule;
	}

	/**
	 * 
	 * @return Le niveau de la tache 1 ou 2<br/>
	 * Si le niveau est different de 1 ou 2<br/>
	 * @throws "NIVEAU INVALIDE"<br/>
	 */
	public int getNiveau() {
		return niveau;
	}

	/**
	 * 
	 * @return type de responsabilite sous la forme : "A", "B", "C", "AB", "BC", "AC", "ABC"<br/>
	 * Si different de "A", "B", "C", "AB", "BC", "AC", "ABC"<br/>
	 * @throws "TYPE DE RESPONSABILITE INVALIDE"
	 */
	public String getTypeResponsabilite() {
		return typeResponsabilite;
	}

	/**
	 * 
	 * @return La liste de competence liees a la tache<br/>
	 */
	public List<Competence> getCompetences() {
		if (competences == null)
			competences = new ArrayList<Competence>();
		return competences;
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
