package fr.paquet.etablissement;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.w3c.dom.Element;



@Entity
@Table(name = "ELEVE")
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "ELELID")),
		@AttributeOverride(name = "nom", column = @Column(name = "ELELNOM", length = 20)),
		@AttributeOverride(name = "prenom", column = @Column(name = "ELELPRENOM", length = 50)),
		@AttributeOverride(name = "dateEntree", column = @Column(name = "ELELDTENTREE")),
		@AttributeOverride(name = "dateSortie", column = @Column(name = "ELELDTSORTIE")),
		@AttributeOverride(name = "dateDeNaissance", column = @Column(name = "ELELDTNAISSANCE")),
		@AttributeOverride(name = "masculin", column = @Column(name = "ELELSEXE")) })
public class Eleve extends Personne implements AEmploiDuTemps {

	@Column(name = "ELELDOUBLEMENT")
	private boolean doublement = false;

	@Column(name = "ELELSMS")
	private boolean sms = false;

	@Column(name = "ELELCODE_REGIME", length = 10)
	@Nullable
	private String codeRegime = null;

	@Column(name = "ELELTRANSPORT")
	private boolean adhesionTransport = false;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<Classe> classes = null;

	@JoinColumn(name = "ELEMID")
	@OneToOne
	private EmploiDuTemps edt = null;

	@Column(name = "ELELLO", length = 20)
	private String login = null;

	public Eleve() {
		super();
	}

	public Eleve(Element elt) throws Exception {
		super(elt);
		setId(getIdFromXml("ELEVE_ID"));
		setNom(getStringFromXml("NOM_DE_FAMILLE").trim().toUpperCase());
		setPrenom(getStringFromXml("PRENOM").trim().toUpperCase());
		setDateDeNaissance(getDateFromXml("DATE_NAISS"));

		if (!getStringFromXml("DOUBLEMENT").equals("0"))
			setDoublement(true);

		if (getStringFromXml("ACCEPTE_SMS") != null) {
			if (!getStringFromXml("ACCEPTE_SMS").equals("0"))
				setSms(true);
		}

		setDateEntree(getDateFromXml("DATE_ENTREE"));

		if (getStringFromXml("DATE_SORTIE") != null)
			setDateSortie(getDateFromXml("DATE_SORTIE"));
		else
			setDateSortie(getDate("01/01/9999"));

		setCodeRegime(getStringFromXml("CODE_REGIME"));

		if (getStringFromXml("CODE_SEXE") != null) {

			if (!getStringFromXml("CODE_SEXE").equals("1")) {
				setSexe(false);
			}
		}

		if (!getStringFromXml("ADHESION_TRANSPORT").equals("0"))
			setAdhesionTransport(true);

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

	public void addClasse(Classe cl) {
		getClasses().add(cl);
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
	 * @return le code regime sans espace a droite et a gauche et en majuscule<br/>
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
	public EmploiDuTemps getEdt() {
		if (edt == null)
			edt = new EmploiDuTemps();
		return edt;
	}

}
