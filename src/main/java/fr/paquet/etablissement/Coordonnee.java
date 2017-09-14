package fr.paquet.etablissement;

import java.util.regex.Pattern;

import javax.annotation.Nullable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "COORDONNEE")
public class Coordonnee {

	/**
	 * @author Nathanaël
	 * 
	 *         La class gere les coordonnes de eleve ou etablissement<br/>
	 *         fonctionne avec l'interface ACoordonnee<br/>
	 */

	@Id
	@GeneratedValue
	@Column(name = "COCOID")
	private long id = 0;

	@Column(name = "COCOAD1", length = 100)
	private String adresse1 = null;

	@Column(name = "COCOAD2", length = 100)
	@Nullable
	private String adresse2 = null;

	@Column(name = "COCOAD3", length = 100)
	@Nullable
	private String adresse3 = null;

	@Column(name = "COCOAD4", length = 100)
	@Nullable
	private String adresse4 = null;

	@JoinColumn(name = "COCMID")
	@OneToOne
	private Commune commune = null;

	@Column(name = "COCOMAIL", length = 100)
	private String mail = null;

	@Column(name = "COCOTEL", length = 20)
	private String tel = null;

	@JoinColumn(name = "COGEID")
	@OneToOne
	private Geographie geo = null;

	/**
	 * Constructeur vide<br/>
	 */
	public Coordonnee() {
		super();
	}

	/**
	 * Constructeur de la class<br/>
	 * coordonnee avec lieu dit<br/>
	 * 
	 * @param geo
	 *            de type Geographie represente le pays ainsi que le code du
	 *            pays<br/>
	 * @param lieuDit
	 *            de type String<br/>
	 * @param nRue
	 *            de type String<br/>
	 * @param rue
	 *            de type String<br/>
	 * @param commune
	 *            de type Commune represente la commune ainsi que son code
	 *            postal<br/>
	 * @throws Exception
	 *             Si la geographie ou la commune est vide<br/>
	 */
	public Coordonnee(Geographie geo, String add1, String add2, String add3, String add4, Commune commune)
			throws Exception {
		this();
		setGeographie(geo);
		setAdresse1(add1);
		setAdresse2(add2);
		setAdresse3(add3);
		setAdresse4(add4);
		setCommune(commune);
	}

	public void setId(long id) {

		this.id = id;
	}

	public void setGeographie(Geographie geo) throws Exception {
		if (geo == null)
			throw new Exception("Veuillez saisir un pays");
		this.geo = geo;
	}

	public void setAdresse1(String adresse) {
		this.adresse1 = adresse.trim();
	}

	public void setAdresse2(String adresse) {
		this.adresse2 = adresse.trim();
	}

	public void setAdresse3(String adresse) {
		this.adresse3 = adresse.trim();
	}

	public void setAdresse4(String adresse) {
		this.adresse4 = adresse.trim();
	}

	public void setCommune(Commune commune) throws Exception {
		if (commune == null)
			throw new Exception("Veuillez saisir une commune");
		this.commune = commune;
	}

	public void setMail(String mail) throws Exception {

		boolean a = false;
		a = Pattern.matches("(*@*.*)", mail);

		if (a == false)
			throw new Exception("Adresse mail invalide");
		this.mail = mail;
	}

	public void setTelephone(String tel) throws Exception {

		boolean a = false;
		a = Pattern.matches("(+33[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9])", tel);

		if (a == false)
			throw new Exception("Numéros invalide");
		this.tel = tel;
	}

	/**
	 * 
	 * @return le code pays et le pays<br/>
	 */
	public Geographie getGeographie() {
		return geo;
	}

	/**
	 * 
	 * @return le ligne d'adresse 1 sans espace a droite et a gauche<br/>
	 */
	public String getAdresse1() {
		return adresse1;
	}

	/**
	 * 
	 * @return le ligne d'adresse 2 sans espace a droite et a gauche<br/>
	 */
	public String getAdresse2() {
		return adresse2;
	}

	/**
	 * 
	 * @return le ligne d'adresse 3 sans espace a droite et a gauche<br/>
	 */
	public String getAdresse3() {
		return adresse3;
	}

	/**
	 * 
	 * @return le ligne d'adresse 1 sans espace a droite et a gauche<br/>
	 */
	public String getAdresse4() {
		return adresse4;
	}

	/**
	 * 
	 * @return La commune et son code postal<br/>
	 */
	public Commune getCommune() {
		return commune;
	}

	/**
	 * 
	 * @return une adresse mail sous forme dddddd@dddd.ddd<br/>
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * 
	 * @return Le numeros de telephone sous la forme 03.25.65.21.41<br/>
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * 
	 * @return L'id pour la gestion de la DB<br/>
	 */
	public long getId() {
		return id;
	}

}
