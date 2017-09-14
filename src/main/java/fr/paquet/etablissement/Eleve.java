package fr.paquet.etablissement;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ELEVE")
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "ELELID")),
		@AttributeOverride(name = "nom", column = @Column(name = "ELELNOM", length = 20)),
		@AttributeOverride(name = "prenom", column = @Column(name = "ELELPRENOM", length = 50)),
		@AttributeOverride(name = "dateEntree", column = @Column(name = "ELELDTENTREE")),
		@AttributeOverride(name = "dateSortie", column = @Column(name = "ELELDTSORTIE")),
		@AttributeOverride(name = "dateDeNaissance", column = @Column(name = "ELELDTNAISSANCE")),
		@AttributeOverride(name = "masculin", column = @Column(name = "ELELSEXE")) })
public class Eleve extends Personne implements ACoordonnee, AEmploiDuTemps {

	@Column(name = "ELELDOUBLEMENT")
	private boolean doublement = false;

	@Column(name = "ELELSMS")
	private boolean sms = false;

	@Column(name = "ELELCODE_REGIME", length = 10)
	@Nullable
	private String codeRegime = null;

	@Column(name = "ELELTRANSPORT")
	private boolean adhesionTransport = false;

	@JoinColumn(name = "ELREID")
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ResponsableEleve> responsableEleves = null;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<Classe> classes = null;

	@JoinColumn(name = "ELCOID")
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Coordonnee coordonnee = null;

	@Column(name = "ELELCOORDONNEE_RESPONSABLE")
	private boolean coordonneeResponsable = true;

	@JoinColumn(name = "ELEMID")
	@OneToOne
	private EmploiDuTemps edt = null;

	public Eleve() {
		super();
	}

	public Eleve(long id) {
		this();
		setId(id);
	}

	public void setDoublement(boolean doublement) {
		this.doublement = doublement;
	}

	public void setSms(boolean sms) {
		this.sms = sms;
	}

	public void setCodeRegime(String codeRegime) {
		codeRegime = codeRegime.trim().toUpperCase();
		if (!codeRegime.equals("1") && !codeRegime.equals("2") && !codeRegime.equals("3"))
			codeRegime = null;
		this.codeRegime = codeRegime;
	}

	public void setAdhesionTransport(boolean adhesionTransport) {
		this.adhesionTransport = adhesionTransport;
	}

	public void setCoordonneeResponsable(boolean coordonneeResponsable) {
		this.coordonneeResponsable = coordonneeResponsable;
	}

	public void setCoordonnee(Coordonnee coord) {
		this.coordonnee = coord;
	}

	public void addResponsableEleve(ResponsableEleve re) {
		getResponsableEleves().add(re);
	}

	public void addClasse(Classe cl) {
		getClasses().add(cl);
	}

	public boolean isCoordonneeResponsable() {
		return coordonneeResponsable;
	}

	/**
	 * 
	 * @return Les responsables d'un eleve<br/>
	 */
	public List<ResponsableEleve> getResponsableEleves() {
		if (responsableEleves == null)
			responsableEleves = new ArrayList<ResponsableEleve>();
		return responsableEleves;
	}

	/**
	 * 
	 * @return Le responsable si il est unique de l'eleve<br/>
	 */
	public ResponsableEleve getResponsableEleve() {
		if (getResponsableEleves().size() == 1)
			return getResponsableEleves().get(0);
		return null;
	}

	/**
	 * 
	 * @return La liste des classes d'un eleve<br/>
	 */
	public List<Classe> getClasses() {
		if (classes == null)
			classes = new ArrayList<Classe>();
		return classes;
	}

	/**
	 * 
	 * @return La classe si elle est unique d'un eleve<br/>
	 */
	public Classe getClasse() {
		if (getClasses().size() == 1)
			return getClasses().get(0);
		return null;
	}

	/**
	 * 
	 * @return true si l'eleve a redouble<br/>
	 */
	public boolean isDoublement() {
		return doublement;
	}

	/**
	 * 
	 * @return si l'eleve accepte de rececoir des sms<br/>
	 */
	public boolean isSms() {
		return sms;
	}

	/**
	 * 
	 * @return le code regime sans espace a droite et a gauche et en
	 *         majuscule<br/>
	 *         Le code regime doit etre soit "INT" soit "EXT" ou soit "DP"<br/>
	 */
	public String getCodeRegime() {
		if (codeRegime.equals("1"))
			codeRegime = "EXT";
		if (codeRegime.equals("2"))
			codeRegime = "DP";
		if (codeRegime.equals("3"))
			codeRegime = "INT";
		else
			codeRegime = null;
		return codeRegime;
	}

	/**
	 * 
	 * @return Si l'eleve adhere a un moyen de transport<br/>
	 */
	public boolean isAdhesionTransport() {
		return adhesionTransport;
	}

	@Override
	public Coordonnee getCoordonnee() {

		return coordonnee;
	}

	@Override
	public EmploiDuTemps getEdt() {
		if (edt == null)
			edt = new EmploiDuTemps();
		return edt;
	}

}
