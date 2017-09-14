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
 * La classe Fonction represente<br/>
 * une Fonction du RAP d'un referentiel donne de l'education national.
 * 
 * Mode d'utilisation : new Fonction("FABRICATION").
 * 
 */

@Entity
@Table(name = "FONCTION")
public class Fonction {

	@Id
	@GeneratedValue
	private int id;

	@Column(name = "INTITULE", length = 140)
	private String intitule;

	@ManyToOne()
	private Rap rap = null;

	@OneToMany(mappedBy = "fonction", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Activite> activites;

	/**
	 * La fonction appartient à un RAP et regroupe des activité du même type.
	 * <br/>
	 * Elle est caractérisée par un libellé. Ce libellé sera enregistré en
	 * MAJUSCULE sans espace a guache et a droite<br/>
	 * exemple : "REALISATION" <br/>
	 * 
	 * @param unIntitule
	 *            : le libelle de la fonction
	 */
	public Fonction(Rap rap, String unIntitule) {
		this();
		setIntitule(unIntitule);
		rap.addFonction(this);
		setRap(rap);
	}

	public Fonction() {
		super();
	}

	public void setId(int id) {
		this.id = id;
	}

	private void setRap(Rap rap) {
		this.rap = rap;
	}

	private void setIntitule(String intitule) {
		this.intitule = intitule.trim().toUpperCase();
	}

	public void addActivite(Activite activite) {
		getActivites().add(activite);
	}

	public List<Activite> getActivites() {
		if (activites == null)
			activites = new ArrayList<Activite>();
		return activites;
	}

	/**
	 * @return l'intitulé de la fonction en majuscule sans espace à droite ou à
	 *         gauche
	 */
	public String getIntitule() {
		return intitule;
	}

	/**
	 * 
	 * @return le Rap auquel appartient la fonction<br/>
	 */
	public Rap getRap() {
		return rap;
	}

	/**
	 * 
	 * @return l'Id pour la gestion de la Db<br/>
	 */
	public int getId() {
		return id;
	}

}
