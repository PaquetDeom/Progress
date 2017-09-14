package fr.paquet.referentiel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author Nathanael
 *
 *         SousSavoirAssocie represente un sous savoir associe d'un referentiel
 *         de l'education nationale<br/>
 *
 *         Mode d'utilisation : new SousSavoirAssocie(SavoirAssocie, 1, < "liste
 *         d'intitule">, 2).
 */

@Entity
@Table(name = "SOUS_SAVOIR_ASSOCIE")
public class SousSavoirAssocie {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;

	@ManyToOne()
	private SavoirAssocie savAss = null;

	@Column(name = "CODE")
	private int code = 0;

	@Column(name = "INTITULE")
	private String intitule;

	@Column(name = "NIVEAU")
	private int niveau;

	/**
	 * Contructeur de la classe SousSavoirAssocie<br/>
	 * 
	 * @param sav
	 *            SousSavoirAssocie est lie a SavoirAssocie<br/>
	 * @param unCode
	 *            code du savoir est compose du code Savoir + "." + un chiffre
	 *            <br/>
	 *            exemple : "S1.1", "S2.3", etc...
	 * @param unIntitule
	 *            le libelle du savoir : <br/>
	 *            exemple : "Les differents partenaire de l'acte de construire"
	 *            <br/>
	 * @param unNiveau
	 *            le niveau du sous savoir est compris entre 1 et 4<br/>
	 * 
	 * @throws Exception
	 *             Si le code est egal a 0 renvoie "Code invalide"<br/>
	 *             Si le niveau n'est pas compris entre 1 et 4 renvoie "Niveau
	 *             invalide"<br/>
	 */

	public SousSavoirAssocie(SavoirAssocie savAss, int unCode, String unIntitule, int unNiveau) throws Exception {

		this();
		setCode(unCode);
		setIntitule(unIntitule);
		savAss.addSousSavoirAssocie(this);
		setSavAss(savAss);
		setNiveau(unNiveau);
	}

	public SousSavoirAssocie() {

		super();
	}

	private void setNiveau(int unNiveau) throws Exception {
		if (unNiveau < 1 | unNiveau > 4)
			throw new Exception("Niveau Invalide");

		this.niveau = unNiveau;

	}

	private void setIntitule(String unIntitule) {
		this.intitule = unIntitule.trim();
	}

	private void setSavAss(SavoirAssocie savAss) {
		this.savAss = savAss;
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

	/**
	 * 
	 * @return le savoir associe du sous savoir associe<br/>
	 */
	private SavoirAssocie getSavAss() {

		return savAss;
	}

	/**
	 * 
	 * @return le code du sous savoir associe sous la forme : code du savoir
	 *         associe + 1, 2, 3...<br/>
	 */
	public String getCode() {
		return getSavAss().getCode() + code;
	}

	/**
	 * 
	 * @return Le niveau du sous savoir associe 1, 2, 3 ou 4<br/>
	 */
	public int getNiveau() {
		return niveau;
	}

	/**
	 * 
	 * @return L'intitule du sous savoir associe sans espace a droite et a
	 *         gauche<br/>
	 */
	public String getIntitule() {

		return intitule;
	}

	/**
	 * 
	 * @return L'Id pour la gestion de la Db<br/>
	 */
	public int getId() {
		return id;
	}

}
