package fr.paquet.sequence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author Nathanael
 * 
 *         La class definie un pretexte d'enseignement de type TP ; TD...
 *
 */

@Entity
@Table(name = "PRETEXTE")
public class Pretexte {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id = 0;

	@Column(name = "TYPE", length = 140)
	private String type;

	@Column(name = "DESRIPTION", length = 400)
	private String description;

	@ManyToOne
	private Execution exe;

	/**
	 * Constructeur de la class
	 * 
	 * @param exe
	 *            exe et de type Execution<br/>
	 * 
	 * @param type
	 *            type est de type String<br/>
	 *            ex : TP, TD, Chantier �cole...<br/>
	 * 
	 * @param description
	 *            description est de type String.
	 */

	public Pretexte(Execution exe, String type, String description) {

		this();
		setType(type);
		setDescription(description);
		setExecution(exe);
		exe.addPretexte(this);
	}

	public Pretexte() {
		super();
	}

	private void setExecution(Execution exe) {
		this.exe = exe;
	}

	private void setDescription(String description2) {
		this.description = description2.trim();
	}

	private void setType(String type2) {
		this.type = type2.trim().toUpperCase();
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return La phase d'execution du pretexte<br/>
	 */
	public Execution getExecution() {
		return exe;
	}

	/**
	 * 
	 * @return La description du pretexte<br/>
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @return Le type de pretexte en majuscule sans espace a droite et a gauche
	 *         (TP, TD...)<br/>
	 */
	public String getType() {
		return type;
	}

	/**
	 * 
	 * @return L'id pour la gestion de la base de donnee<br/>
	 */
	public int getId() {
		return id;
	}

}
