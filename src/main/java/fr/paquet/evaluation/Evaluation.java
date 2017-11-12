package fr.paquet.evaluation;

import fr.paquet.sequence.Sequence;

public class Evaluation {

	private long id = 0;
	private boolean sommative = true;
	private String titre = null;
	private Sequence seq = null;

	public Evaluation(Sequence seq) {

		this();
		seq.getPhase().getValidation().addEvaluation(this);
		setSequence(seq);
	}

	public Evaluation() {

		super();
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setSommative(boolean sommative) {
		this.sommative = sommative;
	}

	public void setTitre(String titre) {
		this.titre = titre.trim();
	}

	private void setSequence(Sequence seq) {
		this.seq = seq;
	}

	/**
	 * 
	 * @return l'id pour la gestion de la Db</br>
	 */
	public long getId() {
		return id;
	}

	/**
	 * 
	 * @return La sequence de l'evaluation<br/>
	 */
	public Sequence getSequence() {
		return seq;
	}

	/**
	 * 
	 * @return true si l'evaluation est sommative<br/>
	 */
	public boolean isSommative() {
		return sommative;
	}

	/**
	 * 
	 * @return Le titre de l'évluation sans espace a gauche et a droite<br/>
	 */
	public String getTitre() {
		return titre;
	}
	public void setId(long id) {
		this.id = id;
	}

	public void setSommative(boolean sommative) {
		this.sommative = sommative;
	}

	public void setTitre(String titre) {
		this.titre = titre.trim();
	}

	private void setSequence(Sequence seq) {
		this.seq = seq;
	}

	/**
	 * 
	 * @return l'id pour la gestion de la Db</br>
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * 
	 * @return La sequence de l'evaluation<br/>
	 */
	public Sequence getSequence() {
		return seq;
	}

	/**
	 * 
	 * @return true si l'evaluation est sommative<br/>
	 */
	public boolean isSommative() {
		return sommative;
	}

	/**
	 * 
	 * @return Le titre de l'évluation sans espace a gauche et a droite<br/>
	 */
	public String getTitre() {
		return titre;
	}

}
