package fr.paquet.sequence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "INTRODUCTION")
public class Introduction {

	/**
	 * @author Nathanael
	 * 
	 *         La classe represente une introduction de sequence.
	 */

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id = 0;

	@Column(name = "TYPE", length = 140)
	private String type;

	@Column(name = "DESCRIPTION", length = 400)
	private String description;

	@OneToOne
	private Phase phase = null;

	/**
	 * Constructeur de la class Introduction<br/>
	 * 
	 * @param phase
	 *            Une introduction appartient � une phase<br/>
	 * 
	 * @param type
	 *            type et une String<br/>
	 *            ex : "Conflit cognitif"<br/>
	 * 
	 * @param description
	 *            description est de type String<br/>
	 *            ex : "eau � 30�C + eau � 30�C n'est pas �gale � de l'eau �
	 *            60�C" <br/>
	 * 
	 */

	public Introduction(Phase ph) {
		this();
		setPhase(ph);
	}

	public Introduction() {

		super();

	}

	private void setPhase(Phase ph) {
		this.phase = ph;
	}

	public void setType(String type) {
		this.type = type.trim().toUpperCase();
	}

	public void setDescription(String description) {
		this.description = description.trim();
	}

	/**
	 * 
	 * @return La phase a laquelle appartient introduction<br/>
	 */
	public Phase getPhase() {
		return phase;
	}

	/**
	 * 
	 * @return Le Type d'introduction en mjuscule sans espace a droite et a
	 *         gauche<br/>
	 */
	public String getType() {
		return type.trim().toUpperCase();
	}

	/**
	 * 
	 * @return La description sans espace a droite et a gauche<br/>
	 */
	public String getDescription() {
		return description.trim();
	}

}
