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
 *         SavoirAssocie represente un savoir associe d'un referentiel de
 *         l'education nationale<br/>
 *
 *         Mode d'utilisation : new SavoirAssocie(Savoir, 1, "Les differents
 *         partenaire de l'acte de construire").
 */

@Entity
@Table(name = "SAVOIR_ASSOCIE")
public class SavoirAssocie {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	@OneToMany()
	private int id;

	@ManyToOne()
	private Savoir sav = null;

	@Column(name = "CODE")
	private int code = 0;

	@Column(name = "INTITULE", length = 200)
	private String intitule;

	@OneToMany(mappedBy = "savAss", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<SousSavoirAssocie> sousSavoirsAssocies;

	/**
	 * Contructeur de la classe SavoirAssocie<br/>
	 * 
	 * @param sav
	 *            SavoirAssocie est lie a Savoir<br/>
	 * @param unCode
	 *            code du savoir est compose du code Savoir + "." + un chiffre
	 *            <br/>
	 *            exemple : S1.1, S2.3, etc...
	 * @param unIntitule
	 *            le libelle du savoir : <br/>
	 *            exemple : "Les differents partenaire de l'acte de construire"
	 *            <br/>
	 * @throws Exception
	 *             Si le code est egale a 0
	 */

	public SavoirAssocie(Savoir sav, int unCode, String unIntitule) throws Exception {

		this();
		setCode(unCode);
		setIntitule(unIntitule);
		sav.addSavoirAssocie(this);
		setSavoir(sav);
	}

	public SavoirAssocie() {

		super();
	}

	private void setSavoir(Savoir sav) {
		this.sav = sav;
	}

	private void setIntitule(String unIntitule) {
		this.intitule = unIntitule.trim();

	}

	private void setCode(int unCode) throws Exception {

		if (unCode == 0)
			throw new Exception("Code Invalide");
		if (unCode < 0)
			this.code = -unCode;
		else
			this.code = unCode;

	}

	public void setId(int id) {
		this.id = id;
	}

	public void addSousSavoirAssocie(SousSavoirAssocie sousSavAss) {
		getSousSavoirsAssocies().add(sousSavAss);
	}

	/**
	 * 
	 * @return Le savoir auquel appartient savoir associe<br/>
	 */
	private Savoir getSavoir() {

		return sav;
	}

	/**
	 * 
	 * @return La liste de sous savoir associe du savoir associe<br/>
	 */
	public List<SousSavoirAssocie> getSousSavoirsAssocies() {
		if (sousSavoirsAssocies == null)
			sousSavoirsAssocies = new ArrayList<SousSavoirAssocie>();
		return sousSavoirsAssocies;
	}

	// public SousSavoirAssocie getSousSavoirAssocie(String code) {
	// for (SousSavoirAssocie ci : getSousSavoirsAssocies()) {
	// if (ci.getCode().equals(code)) {
	// return ci;
	// }
	// }
	// return null;
	// }

	/**
	 * 
	 * @return L'intitule du savoir associe sans espace a droite et a
	 *         gauche<br/>
	 */
	public String getIntitule() {
		return intitule;
	}

	/**
	 * 
	 * @return le code du savoir associe sous la forme : code du savoir + "." +
	 *         1, 2, 3...<br/>
	 */
	public String getCode() {
		return getSavoir().getCode() + "." + code;
	}

	/**
	 * 
	 * @return L'id pour la gestion de la Db<br/>
	 */
	public int getId() {
		return id;
	}

	public String toString() {
		return getCode() + ":" + getIntitule();
	}

}
