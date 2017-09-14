package fr.paquet.etablissement;

import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COMMUNE")
public class Commune {

	/**
	 * @author Nathanaël
	 * 
	 *         La class gere les communes ainsi que leur code postaux<br/>
	 */

	@Id
	@GeneratedValue
	@Column(name = "CMCMID")
	private int id = 0;

	@Column(name = "CMCMCOMMUNE", length = 50)
	private String commune = null;

	@Column(name = "CMCMCODE_COMMUNE", length = 20)
	private String codeCommune = null;

	/**
	 * Constructeur vide pour la gestion de la DB<br/>
	 */
	public Commune() {
		super();
	}

	/**
	 * Constructeur de la class
	 * 
	 * @param codePostal
	 *            Saisi d'une String<br/>
	 * @param commune
	 *            Saisi d'une String<br/>
	 * @throws Exception
	 *             si le code postal n'est pas de type 31500<br/>
	 */
	public Commune(String codeCommune, String commune) throws Exception {
		this();
		setCodeCommune(codeCommune);
		setCommune(commune);
	}

	public void setCodeCommune(String codeCommune) throws Exception {

		boolean a = false;
		a = Pattern.matches("([0-9]([0-9]||[AB])[0-9][0-9][0-9])", codeCommune);

		if (a == false)
			throw new Exception("Saisi du code commune invalide");
		this.codeCommune = codeCommune;
	}

	public void setId(int id) {

		this.id = id;
	}

	public void setCommune(String commune) {
		this.commune = commune.trim().toUpperCase();
	}

	/**
	 * 
	 * @return Le code de la commune<br/>
	 */
	public String getCodeCommune() {
		return codeCommune;
	}

	/**
	 * 
	 * @return La commune sans espace a droite et a gauche et en majuscule<br/>
	 */
	public String getCommune() {
		return commune;
	}

	/**
	 * 
	 * @return L'id pour la gestion de la DB<br/>
	 */
	public int getId() {
		return id;
	}

}
