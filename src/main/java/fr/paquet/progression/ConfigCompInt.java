package fr.paquet.progression;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import fr.paquet.referentiel.CompetenceIntermediaire;
import fr.paquet.referentiel.TaxonomieBlum;

/**
 * 
 * @author Nathana�l
 * 
 *         La classe ChronoCompInt return un positionnement dans la temps d'une
 *         CompetenceIntermediaire<br/>
 * 
 *         Mode d'utilisation : new ChronoCompInt(une CompetenceIntermediaire,
 *         1, 3).
 *
 */

@Entity
@Table(name = "CONFIGCOMPINT")
public class ConfigCompInt {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id = 0;

	@ManyToOne
	private Progression progression = null;

	@Column(name = "DEBUT")
	private int seanceDebut;

	@Column(name = "FIN")
	private int seanceFin;

	// private int nbEvaluation;

	@Column(name = "NIVEAU")
	private int niveau;

	@Column(name = "VALIDNIVEAU")
	private boolean validNiveau = true;

	@OneToOne
	private CompetenceIntermediaire competenceIntermediaire;

	/**
	 * Constructeur (��� A FINIR���)
	 * 
	 * @param compint
	 * @param debut
	 * @param fin
	 */

	public ConfigCompInt(Progression prog, CompetenceIntermediaire compint, int debut, int fin) throws Exception {

		this();
		setCompetenceIntermediaire(compint);
		setDebut(debut);
		setFin(fin);
		setProgression(prog);
		prog.addConfigCompInt(this);

	}

	public ConfigCompInt() {
		super();
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

	public void setValidNiveau(boolean validNiveau) {
		if (TaxonomieBlum.getNiveau(getCompetenceIntermediaire().getVerbe()) == 0)
			validNiveau = false;
		this.validNiveau = validNiveau;
	}

	/**
	 * private void setNbEvaluation(int nbEvaluation) throws Exception { if
	 * (nbEvaluation <= 0) throw new Exception("Nombre d'evaluation invalide");
	 * this.nbEvaluation = nbEvaluation; }
	 */

	private void setProgression(Progression prog) {
		this.progression = prog;
	}

	private void setCompetenceIntermediaire(CompetenceIntermediaire compint) {
		this.competenceIntermediaire = compint;
	}

	private void setDebut(int debut) {
		this.seanceDebut = debut;
	}

	private void setFin(int fin) {
		this.seanceFin = fin;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDebut() {
		return seanceDebut;
	}

	public int getFin() {
		return seanceFin;
	}

	public CompetenceIntermediaire getCompetenceIntermediaire() {
		return competenceIntermediaire;
	}

	public Progression getProgression() {
		return progression;
	}

	/**
	 * public int getNbEvaluation() { return nbEvaluation; }
	 */

	public int getNiveau() {
		if (isValidNiveau() == true)
			return TaxonomieBlum.getNiveau(getCompetenceIntermediaire().getVerbe());
		else
			return niveau;
	}

	private boolean isValidNiveau() {
		return validNiveau;
	}

	public int getId() {
		return id;
	}

}
