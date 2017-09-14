package fr.paquet.referentiel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Nathanael
 * 
 *         La classe represente une condition d'evaluation d'une competence
 *         et/ou competence intermediaire.<br/>
 * 
 *         Mode d'utilisation : La methode getCondition() de la classe
 *         Competence instancie la classe.
 * 
 */

@Entity
@Table(name = "CONDITION")
public class Condition {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;

	@Column(name = "CONDITION", length = 300)
	private String condition = null;

	public Condition(String cond) {
		this();
		setCondition(cond);
	}

	public Condition() {
		super();
	}

	public void setId(int id) {
		this.id = id;
	}

	private void setCondition(String cond) {
		this.condition = cond;
	}

	/**
	 * 
	 * @return La condition sans espace a droite et a gauche<br/>
	 */
	public String getCondition() {
		return condition.trim();
	}

	/**
	 * 
	 * @return L'Id pour la gestion de la Db<br/>
	 */
	public int getId() {
		return id;
	}

}
