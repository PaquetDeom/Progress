package fr.paquet.referentiel;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import fr.paquet.commun.Diplome;

@Entity
@Table(name = "REFERENTIEL")
public class Referentiel {

	/**
	 * @author Nathanael
	 * 
	 *         La class represente un referentiel de l'education national<br/>
	 * 
	 */

	@Id
	@GeneratedValue
	@Column(name = "ID")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private int id = 0;

	@JoinColumn(name = "REDIID")
	@OneToOne
	private Diplome diplome = null;

	@OneToOne(mappedBy = "referentiel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Rap rap = null;

	@OneToMany(mappedBy = "referentiel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Capacite> capacites = null;

	@OneToMany(mappedBy = "referentiel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Savoir> savoirs = null;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Unite> unites = null;

	/**
	 * Constructeur de la classe : <br/>
	 * 
	 * @param unDiplome
	 *            Type de diplome ex : "BAC PROFESSIONNEL"<br/>
	 * 
	 * @param unIntitule
	 *            Represente l'objet du diplome ex : "CONSTRUCTEUR BOIS".
	 * @throws Exception
	 *             Le diplome et/ou l'intule egale null<br/>
	 */

	public Referentiel(Diplome unDiplome) throws Exception {

		this();
		setDiplome(unDiplome);

	}

	public Referentiel() {
		super();
	}

	private void setDiplome(Diplome dip) throws Exception {

		this.diplome = dip;
	}

	public void setRap(Rap rap) {
		this.rap = rap;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void addCapacite(Capacite capacite) {
		getCapacites().add(capacite);
	}

	public void addSavoir(Savoir savoir) {
		getSavoirs().add(savoir);
	}

	public void addUnite(Unite unt) {
		getUnites().add(unt);
	}

	/**
	 * 
	 * @return La liste des capacites.<br/>
	 */
	public List<Capacite> getCapacites() {
		if (capacites == null)
			capacites = new ArrayList<Capacite>();
		return capacites;
	}

	/**
	 * 
	 * @return La liste des Savoirs.<br/>
	 */
	public List<Savoir> getSavoirs() {
		if (savoirs == null)
			savoirs = new ArrayList<Savoir>();
		return savoirs;
	}

	/**
	 * 
	 * @return La liste des Unites diplomantes du referentiel<br/>
	 */
	public List<Unite> getUnites() {
		if (unites == null)
			unites = new ArrayList<Unite>();
		return unites;
	}

	/**
	 * 
	 * @return Le type de diplome (BAC PRO; CAP...)<br/>
	 */
	public Diplome getDiplome() {
		return diplome;
	}

	/**
	 * 
	 * @return Le RAP du referentiel.<br/>
	 */
	public Rap getRap() {
		return rap;
	}

	/**
	 * 
	 * @return L'ID pour la gestion de la DB<br/>
	 */
	public int getId() {
		return id;
	}

	public String toString() {
		return getDiplome().getDiplome() + " - " + getDiplome().getType();
	}

}
