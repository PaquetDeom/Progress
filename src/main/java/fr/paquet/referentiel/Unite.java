package fr.paquet.referentiel;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @author Nathanael
 * 
 *         La classe Unite represente une unite de diplome d'un referentiel de
 *         l'education national<br/>
 * 
 *         Mode d'utilisation :<br/>
 *         new Unite("U.11", "ANALYSE TECHNIQUE D'UN OUVRAGE")
 *
 */

@Entity
@Table(name = "UNITE")
public class Unite {

	@Id
	@Column(name = "ID")
	private int id;

	@Column(name = "CODE", length = 20)
	private int code;

	@Column(name = "INTITULE", length = 200)
	private String intitule;

	@OneToMany(mappedBy = "unite", fetch = FetchType.EAGER)
	private List<Competence> competences = null;

	@ManyToOne
	private Referentiel ref = null;

	/**
	 * Constructeur :
	 * 
	 * @param unCode
	 *            unCode est un entier positif
	 * 
	 * @param unIntitule
	 *            unIntitule est une String "ANALYSE TECHNIQUE"
	 * 
	 * @throws "CODE
	 *             INVALIDE"<br/>
	 * 
	 */

	public Unite(int unCode, String unIntitule) throws Exception {
		this();
		setCode(unCode);
		setIntitule(unIntitule);
	}

	public Unite() {
		super();
	}

	private void setCode(int code) throws Exception {
		if (code == 0)
			throw new Exception("Code invalide");
		if (code < 0)
			this.code = -code;
		else
			this.code = code;
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
	 * @return La liste de competences de l'unite<br/>
	 */
	public List<Competence> getCompetences() {
		if (competences == null)
			competences = new ArrayList<Competence>();
		return competences;
	}

	public Referentiel getReferentiel() {
		return ref;
	}

	/**
	 * 
	 * @return le code de l'unite sous la forme "U.1"<br/>
	 *         Si le code est null<br/>
	 * @throws "CODE
	 *             INVALIDE"<br/>
	 */
	public String getCode() {
		return "U." + code;
	}

	/**
	 * 
	 * @return L'intitule de l'unite<br/>
	 */
	public String getIntitule() {
		return intitule;
	}

	/**
	 * 
	 * @return L'id pour la gestion de la Db<br/>
	 */
	public int getId() {
		return id;
	}

}
