package fr.paquet.sequence;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PHASE")
public class Phase {

	/**
	 * @author Nathanael
	 * 
	 *         La classe Phase represente les quatres phases qui composent une
	 *         sequnece<br/>
	 * 
	 *         Mode d'utilisation : new Phase(Introduction, Decouverte,
	 *         Application, Validation).
	 * 
	 */

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id = 0;

	@OneToOne
	private Sequence sequence = null;

	@OneToOne(mappedBy = "phase", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private static Introduction introduction = null;

	@OneToOne(mappedBy = "phase", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private static Decouverte decouverte = null;

	
	private static Application application = null;

	
	private static Approfondissement approfondissement = null;

	@OneToOne(mappedBy = "phase", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private static Validation validation = null;

	/**
	 * Constructeur de la classe :
	 * 
	 * @param seq
	 *            La Phase appartient � une Sequence<br/>
	 * 
	 * @param intro
	 *            intro est de type Introduction<br/>
	 * 
	 * @param decouverte
	 *            decouverte est de type Decouverte<br/>
	 * 
	 * @param applAppr
	 *            applAppr est de type ApplicationApprofondissement<br/>
	 * 
	 * @param validation
	 *            validation est de type Validation<br/>
	 */

	public Phase(Sequence seq) {

		this();

		setSequence(seq);
		setIntroduction(new Introduction(this));
		setDecouverte(new Decouverte(this));
		setApplication(new Application(this));
		setApprofondissement(new Approfondissement(this));
		setValidation(new Validation(this));

	}

	public Phase() {
		super();
	}

	private void setSequence(Sequence seq) {
		this.sequence = seq;
	}

	private void setIntroduction(Introduction intro) {
		Phase.introduction = intro;
	}

	private void setDecouverte(Decouverte decouverte) {
		Phase.decouverte = decouverte;
	}

	private void setApplication(Application appli) {
		Phase.application = appli;
	}

	private void setApprofondissement(Approfondissement appr) {
		Phase.approfondissement = appr;
	}

	private void setValidation(Validation validation) {
		Phase.validation = validation;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return La phase d'introduction<br/>
	 */
	public Introduction getIntroduction() {
		return introduction;
	}

	/**
	 * 
	 * @return La phase de decouverte<br/>
	 */
	public Decouverte getDecouverte() {
		return decouverte;
	}

	/**
	 * 
	 * @return La phase d'application<br/>
	 */
	public Application getApplication() {
		return application;
	}

	/**
	 * 
	 * @return La phase d'approfondissement<br/>
	 */
	public Approfondissement getApprofondissement() {
		return approfondissement;
	}

	/**
	 * 
	 * @return La phase de validation<br/>
	 */
	public Validation getValidation() {
		return validation;
	}

	/**
	 * 
	 * @return La sequence a laquelle appartient la phase<br/>
	 */
	public Sequence getSequence() {
		return sequence;
	}

	/**
	 * 
	 * @return L'Id pour la gestion de la base de donne<br/>
	 */
	public int getId() {
		return id;
	}

}
