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
 *         La classe represente un critere d'evaluation d'une competence et/ou
 *         competence intermediaire.<br/>
 *
 *         Mode d'utilisation : La methode getCritereEvaluation() de la classe
 *         Competence instancie la classe.
 */

@Entity
@Table(name = "CRITERE_EVALUATION")
public class CritereEvaluation {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;

	@Column(name = "CRITERE", length = 300)
	private String criteresEvaluations;

	public CritereEvaluation(String crit) {
		this();
		setCritere(crit);
	}

	public CritereEvaluation() {
		super();
	}

	public void setId(int id) {
		this.id = id;
	}

	private void setCritere(String crit) {
		this.criteresEvaluations = crit;
	}

	/**
	 * 
	 * @return L'Id pour la gestion de la Db<br/>
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @return Le critere sans espace a droite et a gauche<br/>
	 */
	public String getCritere() {
		return criteresEvaluations.trim();
	}

}
