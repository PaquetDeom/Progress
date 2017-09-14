package fr.paquet.referentiel;

import java.util.ArrayList;
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

/**
 * 
 * @author Nathanael
 * 
 *         La classe Activite represente<br/>
 *         une activite du RAP d'un referentiel donne de l'education national.
 * 
 *         Mode d'utilisation : new Activite(Fonction1, "REALISATION").
 * 
 */

@Entity
@Table(name = "ACTIVITE")
public class Activite {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;

	@Column(name = "INTITULE", length = 140)
	private String intitule;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "activite")
	private List<Tache> taches;

	@ManyToOne()
	private Fonction fonction = null;

	/**
	 * Contructeur de la classe Activite<br/>
	 * 
	 * @param Fonction
	 *            La classe activite charge une liste d'activite dans la classe
	 *            Fonction<br/>
	 * @param UnIntitule
	 *            le libelle de la fonction : <br/>
	 *            exemple : "REALISATION" <br/>
	 *            ATTENTION : l'intitul� est en majuscule
	 */

	public Activite(Fonction fonc, String unIntitule) {

		this();
		setIntitule(unIntitule);
		fonc.addActivite(this);
		setFonction(fonc);
	}

	public Activite() {
		super();
	}

	private void setIntitule(String intitule) {

		String[] tab = intitule.trim().split(":");
		tab[0] = tab[0].trim().toUpperCase();
		intitule = tab[0];

		for (int i = 0; i < tab.length; i++) {
			if (intitule.equals(tab[i]))
				intitule = tab[i].concat(" ") + ":";
			else
				intitule = intitule.concat(" ") + tab[i].trim().toLowerCase();
		}

		this.intitule = intitule;
	}

	private void setFonction(Fonction fonction) {
		this.fonction = fonction;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void addTache(Tache tache) {
		getTaches().add(tache);
	}

	/**
	 * 
	 * @return La liste de taches de l'activite<br/>
	 */
	public List<Tache> getTaches() {
		if (taches == null)
			taches = new ArrayList<Tache>();
		return taches;
	}

	/**
	 * 
	 * @return L'intitule de l'activite sous la forme :<br/>
	 *         "PREMIERE CHAINE EN MAJUSCULE" + ":" + "Seconde chaine"<br/>
	 *
	 */
	public String getIntitule() {
		return intitule;
	}

	/**
	 * 
	 * @return La fonction a laquelle appartient l'activite<br/>
	 */
	public Fonction getFonction() {
		return fonction;
	}

	/**
	 * 
	 * @return La premiere partie de l'intitule en majuscule<br/>
	 */
	public String getTypeTache() {
		String[] tab = this.intitule.split(":");
		return tab[0].trim();
	}

	/**
	 * 
	 * @return L'id pour le gestion de la Db<br/>
	 */
	public int getId() {
		return id;
	}

}
