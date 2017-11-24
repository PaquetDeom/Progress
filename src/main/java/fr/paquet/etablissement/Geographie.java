package fr.paquet.etablissement;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.w3c.dom.Element;

import com.ibm.icu.text.SimpleDateFormat;

@Entity
@Table(name = "GEOGRAPHIE")
public class Geographie extends XMLBean {

	/**
	 * @author Nathanaël
	 * 
	 *         La class represente des pays<br/>
	 */

	@Id
	@Column(name = "GEGEID")
	private int id_Code_Pays = 0;

	@Column(name = "GEGELBL_COURT", length = 200)
	private String libelle_Court = null;

	@Column(name = "GEGELBL_LONG", length = 200)
	private String libelle_Long = null;

	@Column(name = "GEGEDATE_OUVERTURE")
	@Temporal(TemporalType.DATE)
	private Date ouverture = null;

	@Column(name = "GEGEDATE_FERMETURE")
	@Temporal(TemporalType.DATE)
	private Date fermeture = null;

	/**
	 * Constructeur vide pour la gestion de la DB<br/>
	 * @param elt 
	 */
	public Geographie() {
		super(null);
	}
	
	/**
	 * Constructeur pour la creation de geographique par *.xml</br>
	 * @param elt
	 * @throws Exception
	 */
	public Geographie(Element elt) throws Exception {
		super(elt);
		setId_Code_Pays(Integer.parseInt(elt.getAttribute("CODE_PAYS")));
		setLibelle_Court(getStringFromXml("LIBELLE_COURT"));
		setLibelle_Long(getStringFromXml("LIBELLE_LONG"));
		setOuverture(getDateFromXml("DATE_OUVERTURE"));
		setFermeture(getDateFromXml("DATE_FERMETURE"));
	}

	/**
	 * 
	 * @param id
	 *            int id du pays<br/>
	 * @param libCourt
	 *            String libelle court<br/>
	 * @param libLong
	 *            String libelle long<br/>
	 * @param ouverture
	 *            Date ouverture du pays<br/>
	 * @param fermeture
	 *            Date fermeture du pays<br/>
	 * @throws Exception
	 *             La date d'ouverture est aprés la date de fermeture<br/>
	 *             La date de fermeture est avant la date d'ouverture<br/>
	 */
	public Geographie(int id, String libCourt, String libLong, Date ouverture, Date fermeture) throws Exception {
		this(null);
		setId_Code_Pays(id);
		setLibelle_Court(libCourt);
		setLibelle_Long(libLong);
		setOuverture(ouverture);
		setFermeture(fermeture);
	}

	public void setId_Code_Pays(int id_Code_Pays) {
		this.id_Code_Pays = id_Code_Pays;
	}

	public void setLibelle_Long(String libelle_Long) {
		this.libelle_Long = libelle_Long.trim();
	}

	public void setOuverture(Date ouverture) throws Exception {
		if (getFermeture() != null && ouverture.after(getFermeture()))
			throw new Exception("La date d'ouverture doit etre avant");
		this.ouverture = ouverture;
	}

	public void setFermeture(Date fermeture) throws Exception {
		if (getOuverture() != null && fermeture.before(getOuverture()))
			throw new Exception("La date de fermeture doit etre après");
		this.fermeture = fermeture;
	}

	public void setLibelle_Court(String libelle_Court) {
		this.libelle_Court = libelle_Court.trim();
	}

	/**
	 * 
	 * @return L'id pour la gestion de la DB<br/>
	 */
	public int getId_Code_Pays() {
		return id_Code_Pays;
	}

	/**
	 * 
	 * @return Le libelle court sans espace a droite et a gauche<br/>
	 */
	public String getLibelle_Court() {
		return libelle_Court;
	}

	/**
	 * 
	 * @return Le libelle long sans espace a droite et a gauche<br/>
	 */
	public String getLibelle_Long() {
		return libelle_Long;
	}

	/**
	 * 
	 * @return La date d'ouverture<br/>
	 */
	public Date getOuverture() {
		return ouverture;
	}

	/**
	 * 
	 * @return La date de fermeture<br/>
	 */
	public Date getFermeture() {
		return fermeture;
	}

}
