package fr.paquet.etablissement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@Table(name = "ETABLISSEMENT")
public class Etablissement {

	
	@Id
	@Column(name = "ETETID", length = 20)
	private String codeRNE = null;

	@Column(name = "ETETSIGLE", length = 20)
	private String sigle = null;

	@Column(name = "ETETDENOMINATION_PRINCIPALE", length = 200)
	private String denominationPrincipale = null;

	@Column(name = "ETETDENOMINATION_COMPLEMENTAIRE", length = 200)
	private String denominationComplementaire = null;

	@Column(name = "ETETDATE_OUVERTURE")
	@Temporal(TemporalType.DATE)
	private Date dateOuverture = null;

	@Column(name = "ETETDATE_FERMETURE")
	@Temporal(TemporalType.DATE)
	private Date dateFermeture = null;

	@JoinColumn(name = "ETPRID")
	@OneToOne
	private Proviseur proviseur = null;

	@OneToMany(fetch = FetchType.LAZY)
	private List<Classe> classes = null;

	public Etablissement(String rne) {
		this();
		setCodeRNE(rne);
	}

	public Etablissement() {
		super();
	}

	public void setProviseur(Proviseur proviseur) {
		this.proviseur = proviseur;
	}

	public void setCodeRNE(String rne) {
		this.codeRNE = rne.trim().toUpperCase();

	}


	public void setSigle(String sigle) {

		this.sigle = sigle.trim().toUpperCase();
	}

	public void setDenominationPrincipale(String denominationPrincipale) throws Exception {
		if (denominationPrincipale == null)
			throw new Exception("Denomination non saisi");
		this.denominationPrincipale = denominationPrincipale.trim();
	}

	public void setDenominationComplementaire(String denominationComplementaire) throws Exception {
		this.denominationComplementaire = denominationComplementaire.trim();
	}

	public void setDateOuverture(Date ouverture) throws Exception {
		if (getDateFermeture() != null && ouverture.after(getDateFermeture()))
			throw new Exception("Date invalide");
		this.dateOuverture = ouverture;
	}

	public void setDateFermeture(Date fermeture) throws Exception {
		if (getDateOuverture() != null && fermeture.before(getDateOuverture()))
			throw new Exception("Date invalide");
		this.dateFermeture = fermeture;
	}

	public void addClasses(Classe classe) {
		getClasses().add(classe);
	}

	/**
	 * 
	 * @return Le code RNE de l'etablissement sans espace a droite et a gauche
	 *         et en majuscule<br/>
	 *         est l'id pour la gestion de la DB<br/>
	 */
	public String getCodeRne() {
		return codeRNE;
	}

	
	/**
	 * 
	 * @return sigle sans espace a droite et a gauche en majuscule<br/>
	 * @throws si
	 *             sigle est null<br/>
	 */
	public String getSigle() {
		return sigle;
	}

	/**
	 * 
	 * @return denimination sans espace a droite et a gauche<br/>
	 * @throws si
	 *             denomination est null<br/>
	 */
	public String getDenominationPrincipale() {
		return denominationPrincipale;
	}

	/**
	 * 
	 * @return la denomination complementaire sans espace a droite et a
	 *         gauche<br/>
	 */
	public String getDenominationComplementaire() {
		return denominationComplementaire;
	}

	/**
	 * 
	 * @return le proviseur de l'etablissement scolaire<br/>
	 */
	public Proviseur getProviseur() {
		return proviseur;
	}

	/**
	 * 
	 * @return la liste de classes d'un etablissement scolaire<br/>
	 */
	public List<Classe> getClasses() {
		if (classes == null)
			classes = new ArrayList<Classe>();
		return classes;
	}

	/**
	 * 
	 * @return la date d'ouverture de l'etablissement<br/>
	 * @throws si
	 *             la date d'ouverture et apres la date de fermeture<br/>
	 */
	public Date getDateOuverture() {
		return dateOuverture;
	}

	/**
	 * 
	 * @return la date de fermeture de l'etablissement<br/>
	 *         cette date peut etre egale a null<br/>
	 * @throws si
	 *             la date de fermeture est avant la date d'ouverture<br/>
	 */
	public Date getDateFermeture() {
		return dateFermeture;
	}

	@Override
	public String toString() {
		return getSigle() + " - " + getDenominationPrincipale();
	}

}
