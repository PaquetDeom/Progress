package fr.paquet.sequence;

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

import fr.paquet.referentiel.Competence;
import fr.paquet.referentiel.Savoir;
import fr.paquet.referentiel.SavoirAssocie;
import fr.paquet.referentiel.SousSavoirAssocie;

@Entity
@Table(name = "DECOUVERTE")
public class Decouverte {

	/**
	 * @author Nathanael
	 * 
	 *         La class represente une phase d'une sequence.
	 */

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id = 0;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Phase phase = null;

	@Column(name = "DESCRIPTION", length = 400)
	private String description;

	@OneToMany(fetch = FetchType.LAZY)
	private List<SavoirAssocie> savoirAssocies = null;

	/**
	 * Constructeur de la class<br/>
	 * 
	 * @param phase
	 * 
	 *            Decouverte appartient a une phae de une sequence<br/>
	 * 
	 * @param description
	 * 
	 *            Description est de type string<br/>
	 */

	public Decouverte() {

		super();
	}

	public Decouverte(Phase ph) {
		this();
		setPhase(ph);
	}

	private void setPhase(Phase ph) {
		this.phase = ph;
	}

	public void setDescription(String description2) {
		this.description = description2.trim();

	}

	public void setId(int id) {
		this.id = id;
	}

	public void addSavoirAssocie(SavoirAssocie savAss) throws Exception {

		if (getSavoirAssociePossibles().contains(savAss))
			savoirAssocies.add(savAss);
		else
			throw new Exception("Savoir Associe invalide");

	}

	/**
	 * 
	 * @return La description sans espace a droite et a gauche<br/>
	 */
	public String getDescription() {
		return description.trim();
	}

	/**
	 * 
	 * @return La phase a laquelle appartient decouverte<br/>
	 */
	public Phase getPhase() {
		return phase;
	}

	/**
	 * 
	 * @return La liste des savoir possible de decouverte<br/>
	 */
	public List<Savoir> getSavoirPossibles() {

		List<Savoir> savs = new ArrayList<Savoir>();

		for (int i = 0; i < getPhase().getApplication().getCompetences().size(); i++) {
			Competence comp = getPhase().getApplication().getCompetences().get(i);
			for (int n = 0; n < comp.getSavoirs().size(); n++) {
				Savoir sav = comp.getSavoirs().get(n);
				if (savs.contains(sav))
					sav = null;
				else
					savs.add(sav);
			}
		}
		return savs;
	}

	/**
	 * 
	 * @return La liste des savoirs associes possibles de decouverte<br/>
	 */
	public List<SavoirAssocie> getSavoirAssociePossibles() {
		List<SavoirAssocie> savAsss = new ArrayList<SavoirAssocie>();

		for (int i = 0; i < getSavoirPossibles().size(); i++) {
			Savoir sav = getSavoirPossibles().get(i);
			for (int n = 0; n < sav.getSavoirsAssocies().size(); n++) {
				SavoirAssocie savAss = sav.getSavoirsAssocies().get(n);
				if (savAsss.contains(savAss))
					savAss = null;
				else
					savAsss.add(savAss);
			}
		}
		return savAsss;
	}

	/**
	 * 
	 * @return La liste des sous savoirs associes de decouverte<br/>
	 */
	public List<SousSavoirAssocie> getSousSavoirAssocie() {

		List<SousSavoirAssocie> ssass = new ArrayList<SousSavoirAssocie>();

		for (int i = 0; i < getSavoirAssocies().size(); i++) {
			SavoirAssocie ssa = getSavoirAssocies().get(i);
			for (int n = 0; n < ssa.getSousSavoirsAssocies().size(); n++) {
				SousSavoirAssocie ssas = ssa.getSousSavoirsAssocies().get(n);
				if (ssass.contains(ssas))
					ssas = null;
				else
					ssass.add(ssas);
			}
		}

		return ssass;
	}

	/**
	 * 
	 * @return La liste des savoirs associes de decouverte<br/>
	 */
	public List<SavoirAssocie> getSavoirAssocies() {
		if (savoirAssocies == null)
			savoirAssocies = new ArrayList<SavoirAssocie>();
		return savoirAssocies;
	}

	/**
	 * 
	 * @return L'id pour la gestion de la base de donne<br/>
	 */
	public int getId() {
		return id;
	}

}
