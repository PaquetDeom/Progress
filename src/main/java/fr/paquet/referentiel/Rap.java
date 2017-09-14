package fr.paquet.referentiel;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RAP")
public class Rap {

	/**
	 * @author Nathanael
	 * 
	 *         Le Rap represente le referentiel des activites professionnelles
	 *         d'un referentiel de l'education national<br/>
	 * 
	 *         Il contient les Fonction, Activite et Tache.
	 * 
	 *         Mode d'utilisation : new Rap(Referentiel)<br/>
	 */

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;

	@OneToMany(mappedBy = "rap", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Fonction> fonctions = null;

	@OneToOne()
	private Referentiel referentiel = null;

	/**
	 * 
	 * @param ref
	 *            Rap appartient a un unique referentiel de l'education national
	 *            <br/>
	 */

	public Rap(Referentiel ref) {
		this();
		setReferentiel(ref);
		ref.setRap(this);
	}

	public Rap() {
		super();
	}

	private void setReferentiel(Referentiel ref) {
		this.referentiel = ref;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void addFonction(Fonction fonction) {
		getFonctions().add(fonction);
	}

	/**
	 * 
	 * @return La liste de Fonctions du Rap<br/>
	 */
	public List<Fonction> getFonctions() {
		if (fonctions == null)
			fonctions = new ArrayList<Fonction>();
		return fonctions;
	}

	/**
	 * 
	 * @return Le referentiel auquel appartient le rap<br/>
	 */
	public Referentiel getReferentiel() {
		return referentiel;
	}

	/**
	 * 
	 * @return L'Id pour la gestion de la Db<br/>
	 */
	public int getId() {
		return id;
	}

}
