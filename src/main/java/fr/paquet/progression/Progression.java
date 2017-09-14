package fr.paquet.progression;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.paquet.referentiel.Referentiel;
import fr.paquet.sequence.Sequence;

/**
 * 
 * @author Nathana�l
 *
 *         La classe Progression return CompetenceIntermediaire �chelonn�e dans
 *         la temps<br/>
 *
 *         Mode d'utilisation : new Progression("Prog 1", 1, 1152)<br/>
 *
 */

@Entity
@Table(name = "PROGRESSION")
public class Progression {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id = 0;

	@ManyToOne()
	private Referentiel referentiel = null;

	@Column(name = "TITRE", length = 50)
	private String titre;

	@Column(name = "DEBUT")
	private int debut;

	@Column(name = "FIN")
	private int fin;

	@OneToMany(mappedBy = "progression", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ConfigCompInt> configCompInts = null;

	@OneToMany(mappedBy = "progression", fetch = FetchType.LAZY)
	private List<Sequence> sequences = null;

	/**
	 * Constructeur (��� A FINIR ���)<br/>
	 * 
	 * @param titre
	 * @param debut
	 * @param fin
	 */

	public Progression(Referentiel ref, String titre) {

		this();
		setReferentiel(ref);
		setTitre(titre);
		setDebut(1);
		setFin(1152);

	}

	public Progression() {
		super();
	}

	private void setReferentiel(Referentiel ref) {
		this.referentiel = ref;
	}

	private void setTitre(String titre) {
		this.titre = titre.trim().toUpperCase();
	}

	private void setDebut(int debut) {
		this.debut = debut;
	}

	private void setFin(int fin) {
		this.fin = fin;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void addSequence(Sequence seq) {

		getSequences().add(seq);
	}

	public void addConfigCompInt(ConfigCompInt cci) {
		getConfigCompInts().add(cci);
	}

	public List<Sequence> getSequences() {
		if (sequences == null)
			sequences = new ArrayList<Sequence>();
		return sequences;
	}

	public List<ConfigCompInt> getConfigCompInts() {
		if (configCompInts == null)
			configCompInts = new ArrayList<ConfigCompInt>();
		return configCompInts;
	}

	public String getTitre() {
		return titre;
	}

	public int getDebut() {
		return debut;
	}

	public int getFin() {
		return fin;
	}

	public Referentiel getReferentiel() {
		return referentiel;
	}

	/**
	 * calcul le nombre de jours compris entre la date passée en paramètre d et
	 * le 01/09/2016 <br/>
	 * calcul à partir de la le nombre de semaine<br/>
	 * et renvoie ce nombre de semaine multiplié par 10 (nombre d'heure de cours
	 * par semaine).
	 * 
	 * @param d
	 * @return
	 */
	public int getNumSceance(Date d) {
		Calendar rentree = new GregorianCalendar(2017, 4, 2);
		LocalDate dateRentree = rentree.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate dateD = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		long nbJours = ChronoUnit.DAYS.between(dateRentree, dateD) + 1;

		return (int) nbJours * 10 / 7 + 1;
	}

	public int getId() {
		return id;
	}

	public String toString() {
		return getTitre();
	}

}
