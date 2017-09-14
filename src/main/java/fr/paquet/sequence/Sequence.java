package fr.paquet.sequence;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import fr.paquet.progression.ConfigCompInt;
import fr.paquet.progression.Progression;
import fr.paquet.referentiel.Competence;
import fr.paquet.referentiel.CompetenceIntermediaire;
import fr.paquet.referentiel.Unite;
import fr.paquet.seance.Seance;

@Entity
@Table(name = "SEQ")
public class Sequence {

	/**
	 * @author Nathanael
	 * 
	 *         La classe Sequence represente une sequence d'enseignement de
	 *         l'education national<br/>
	 * 
	 *         Mode d'utilisation : new Sequence(Progression, "Sequence 1", 2,
	 *         9, Phase).
	 * 
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int id = 0;

	@Column(name = "DEBUT")
	private int debut;

	@Column(name = "FIN")
	private int fin;

	@Column(name = "TITRE", length = 50)
	private String titre;

	@OneToOne(mappedBy = "sequence", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Phase phase = null;

	@Column(name = "DEMARCHE")
	private int demarchePedagogique;

	private List<Seance> seances = null;

	@Column(name = "PREREQUIS", length = 400)
	private String prerequis;

	private List<CompetenceIntermediaire> compInts = null;

	@ManyToOne()
	private Progression progression = null;

	/**
	 * Constructeur de la classe<br/>
	 * 
	 * @param prog
	 *            prog de type Progression<br/>
	 *            une Sequence peut appartenir a une progression<br/>
	 * 
	 * @param titre
	 *            titre de type String "Sequence rabotage"<br/>
	 * 
	 * @param debut
	 *            debut de type int<br/>
	 *            represente le N de seance de debut de debut de la sequence
	 *            <br/>
	 * 
	 * @param fin
	 *            fin de type int<br/>
	 *            represente le N de seance de fin de la sequence<br/>
	 * 
	 * @param ph1
	 *            ph1 est de type Phase<br/>
	 *            une sequence est composee de differentes phases<br/>
	 * 
	 * @throws Exception
	 *             debut doit etre inferieur a fin.
	 */
	public Sequence(String titre) throws Exception {
		this(null, titre);

	}

	public Sequence(Progression prog, String titre) throws Exception {
		this();
		setProgression(prog);
		setTitre(titre);
		setPhase(new Phase(this));
		if (getProgression() != null)
			prog.addSequence(this);
	}

	public Sequence() {
		super();
	}

	private void setProgression(Progression prog) {

		this.progression = prog;
	}

	private void setTitre(String titre2) {
		this.titre = titre2.trim().toUpperCase();
	}

	public void setDebut(int debut) throws Exception {
		if (getProgression() != null && debut < getProgression().getDebut())
			throw new Exception("Debut de sequence invalide");

		this.debut = debut;
	}

	public void setFin(int fin) throws Exception {

		if (getDebut() > fin | fin == 0)
			throw new Exception("Fin de sequence invalide");
		if (getProgression() != null && fin > getProgression().getFin())
			fin = getProgression().getFin();
		this.fin = fin;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPrerequis(String prerequis) {
		this.prerequis = prerequis.trim();
	}

	public void setDemarche(int demarche) throws Exception {

		if (demarche < 1 | demarche > 3)
			throw new Exception("invalid d�marche");
		else

			this.demarchePedagogique = demarche;
	}

	private void setPhase(Phase ph) {

		this.phase = ph;
	}

	public void addCompetenceIntermediaire(CompetenceIntermediaire compInt) throws Exception {
		if (getProgression() == null)
			getCompetenceIntermediaires().add(compInt);
		else
			throw new Exception(
					"Les competences intermediaires dependent de la progression, vous ne pouvez pas les choisir");
	}

	public void addSeance(Seance si) {
		getSeances().add(si);
	}

	/**
	 * 
	 * @return La liste des seances de la sequence<br/>
	 */
	public List<Seance> getSeances() {
		if (seances == null)
			seances = new ArrayList<Seance>();
		return seances;
	}

	/**
	 * 
	 * @return Le nombre de seance de la sequence en String<br/>
	 */
	public String getNbSeance() {

		return String.valueOf(getNombreDeSeance());
	}

	/**
	 * 
	 * @return Le nombre de seance de la sequence<br/>
	 */
	public int getNombreDeSeance() {
		int nb = 0;
		if (getDebut() != 0 && getFin() != 0)
			nb = getFin() - getDebut();

		return nb;
	}

	/**
	 * 
	 * @return Les ConfigCompInt entre la date de debut et de fin de la
	 *         sequence<br/>
	 */
	public List<ConfigCompInt> getConfigCompInts() {

		List<ConfigCompInt> configCompInts = new ArrayList<ConfigCompInt>();

		if (getProgression() != null) {
			for (int i = 0; i < progression.getConfigCompInts().size(); i++) {

				ConfigCompInt cci = progression.getConfigCompInts().get(i);

				if ((cci.getDebut() == getDebut()) | (cci.getFin() == getFin())
						|| (cci.getDebut() > getDebut() && cci.getFin() < getFin())
						|| (cci.getDebut() < getDebut() && cci.getFin() > getFin())
						|| (cci.getDebut() < getDebut() && cci.getFin() < getFin() && cci.getFin() > getDebut())
						|| (cci.getDebut() > getDebut() && cci.getFin() > getFin() && cci.getDebut() < getFin())) {

					configCompInts.add(cci);
				}

				else
					cci = null;
			}

			return configCompInts;
		}
		return null;
	}

	/**
	 * 
	 * @return Les competences intermediares de la sequence<br/>
	 */
	public List<CompetenceIntermediaire> getCompetenceIntermediaires() {

		if (getProgression() != null) {
			List<CompetenceIntermediaire> competenceIntermediaires = new ArrayList<CompetenceIntermediaire>();
			if (getProgression() != null) {
				for (int i = 0; i < getConfigCompInts().size(); i++) {
					competenceIntermediaires.add(getConfigCompInts().get(i).getCompetenceIntermediaire());
				}
			}

			return competenceIntermediaires;
		}

		if (getProgression() == null) {
			compInts = null;
			compInts = new ArrayList<CompetenceIntermediaire>();
			return compInts;
		}

		else
			return null;
	}

	/**
	 * 
	 * @return Les competences de la seance<br/>
	 */
	public List<Competence> getCompetences() {

		List<Competence> competences = new ArrayList<Competence>();
		for (int i = 0; i < getCompetenceIntermediaires().size(); i++) {
			Competence comp = getCompetenceIntermediaires().get(i).getCompetence();
			if (competences.contains(comp))
				comp = null;
			else
				competences.add(comp);
		}

		return competences;
	}

	/**
	 * 
	 * @return Les unites diplomantes de la sequence<br/>
	 */
	public List<Unite> getUnites() {

		List<Unite> unites = new ArrayList<Unite>();
		for (int i = 0; i < getCompetences().size(); i++) {
			Unite unt = getCompetences().get(i).getUnite();
			if (unites.contains(unt))
				unt = null;
			else
				unites.add(unt);
		}

		return unites;
	}

	/**
	 * 
	 * @return Le titre de la sequence en majuscule sans espace a gauche et a
	 *         droite<br/>
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * 
	 * @return Le numeros de debut de sequence<br/>
	 */
	public int getDebut() {
		return debut;
	}

	/**
	 * 
	 * @return Le numeros de fin de sequence<br/>
	 */
	public int getFin() {
		return fin;
	}

	/**
	 * 
	 * @return La phase de la sequence<br/>
	 */
	public Phase getPhase() {
		return phase;
	}

	/**
	 * 
	 * @return La progression de la sequence</br>
	 * @return null si pas de progression</br>
	 */

	public Progression getProgression() {
		if (progression == null)
			return null;
		return progression;
	}

	/**
	 * 
	 * @return "La sequence ne fait pas parti d'une progression" si la sequence
	 *         ne fait pas parti d'une progression<br/>
	 * @return "LA SEQUENCE CHEVAUCHE UNE AUTRE SEQUENCE DANS LA PROGRESSION" si
	 *         plusieurs sequences se chevauchent<br/>
	 * @return null pour les autres cas<br/>
	 */
	public String getChevauchement() {

		if (getProgression() == null)
			return "La sequence ne fait pas parti d'une progression";
		else if (isChevauchement() == true)
			return "LA SEQUENCE CHEVAUCHE UNE AUTRE SEQUENCE DANS LA PROGRESSION";
		else
			return null;

	}

	/**
	 * 
	 * @return true si plusieurs sequences se chevauchent<br/>
	 */
	private boolean isChevauchement() {

		boolean chev = false;
		for (int i = 0; i < getProgression().getSequences().size(); i++) {
			Sequence seq = getProgression().getSequences().get(i);
			if ((getDebut() > seq.getDebut() && getDebut() < seq.getFin())
					| (getFin() > seq.getDebut() && getFin() < seq.getFin())
					| (getDebut() < seq.getDebut() && getFin() > seq.getFin()))
				return true;
			else
				chev = false;
		}

		return chev;

	}

	/**
	 * 
	 * @return une demarche pedagogique<br/>
	 */
	public int getDemarche() {
		return demarchePedagogique;
	}

	/**
	 * 
	 * @return Le nom de la demarche pedagogique<br/>
	 */
	public String getDemarchePeda() {
		if (getDemarche() == 1)
			return "DEMARCHE DEDUCTIVE";
		if (getDemarche() == 2)
			return "DEMARCHE INDUCTIVE";
		else
			return "DEMARCHE DIALECTIQUE";
	}

	/**
	 * 
	 * @return "Les prérequis" de la sequence<br/>
	 */
	public String getPrerequis() {
		return prerequis;
	}

	/**
	 * 
	 * @return L'Id pour la gestion de la base de donnees<br/>
	 */
	public int getId() {
		return id;
	}

	public String toString() {
		return getTitre();
	}

}
