package fr.paquet.commun;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DIPLOME")
public class Diplome {

	/**
	 * @author Nathanaël
	 * 
	 *         La classe represente un diplome de l'education nationale<br/>
	 */
	
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id = 0;

	@Column(name = "DIPLOME", length = 10)
	private String diplome = null;

	@Column(name = "TYPE", length = 50)
	private String type = null;

	/**
	 * Constructeur vide pour la gestion de la DB<br/>
	 */
	public Diplome() {
		super();
	}

	/**
	 * Constructeur de la class<br/>
	 * 
	 * @param diplome
	 * @param type
	 */
	public Diplome(String diplome, String type) throws Exception {
		this();
		setDiplome(diplome);
		setType(type);
	}

	private void setDiplome(String diplome) {

		this.diplome = diplome.trim().toUpperCase();
	}

	private void setType(String type) {
		this.type = type.trim().toUpperCase();
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return le diplome prepare sans espace a droite et a gauche et en
	 *         majuscule<br/>
	 */
	public String getDiplome() {
		return diplome;
	}

	/**
	 * 
	 * @return le type de diplome sans espace a droite et a gauche et en
	 *         majuscule<br/>
	 *         exemple : "PRO", "STI"...<br/>
	 */
	public String getType() {
		return type;
	}

	/**
	 * 
	 * @return Le niveau du diplome prepare<br/>
	 */
	public int getNiveau() {
		if (getDiplome().equals("CAP") || getDiplome().equals("BEP"))
			return 5;
		if (getDiplome().equals("BAC") || getDiplome().equals("BP") || getDiplome().equals("BAC PRO"))
			return 4;
		if (getDiplome().equals("BTS"))
			return 3;
		if (getDiplome().equals("LICENCE"))
			return 2;
		if (getDiplome().equals("MASTER"))
			return 1;
		else
			return 0;
	}

	/**
	 * 
	 * @return L'id pour la gestion de la DB<br/>
	 */
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return getDiplome() + " - " + getType();
	}
}
