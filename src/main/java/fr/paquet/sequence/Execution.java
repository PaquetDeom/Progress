package fr.paquet.sequence;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EXECUTION")
public class Execution {

	/**
	 * @author Nathanael
	 * 
	 *         La classe est mere de Application et Approfondissement.
	 * 
	 */

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id = 0;

	@OneToOne
	protected Phase phase;

	@Column(name = "DESRIPTION", length = 400)
	protected String description;

	@OneToMany(mappedBy = "exe", fetch = FetchType.LAZY)
	protected List<Pretexte> pretextes;

	/**
	 * Constructeur de la classe<br/>
	 * 
	 * 
	 */

	public Execution() {
		super();
	}

	public void addPretexte(Pretexte pretexte) {
		getPretextes().add(pretexte);
	}

	protected void setDescription(String description) {
		this.description = description.trim();
	}

	protected void setPhase(Phase phase) {
		this.phase = phase;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return La phase de l'execution<br/>
	 */
	public Phase getPhase() {
		return phase;
	}

	/**
	 * 
	 * @return La description de l'execution sans espace a droite et a
	 *         gauche<br/>
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @return La liste de pretextes de l'execution<br/>
	 */
	public List<Pretexte> getPretextes() {
		if (pretextes == null)
			pretextes = new ArrayList<Pretexte>();
		return pretextes;
	}

	/**
	 * 
	 * @return L'Id pour la gestion de la base de donnee<br/>
	 */
	public int getId() {
		return id;
	}

}
