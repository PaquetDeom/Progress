package fr.paquet.etablissement;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PROVISEUR")
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "PRPRID")),
		@AttributeOverride(name = "nom", column = @Column(name = "PRPRNOM", length = 20)),
		@AttributeOverride(name = "prenom", column = @Column(name = "PRPRPRENOM", length = 50)),
		@AttributeOverride(name = "dateEntree", column = @Column(name = "PRPRDTENTREE")),
		@AttributeOverride(name = "dateSortie", column = @Column(name = "PRPRDTSORTIE")),
		@AttributeOverride(name = "dateDeNaissance", column = @Column(name = "PRPRDTNAISSANCE")),
		@AttributeOverride(name = "masculin", column = @Column(name = "PRPRSEXE")) })
public class Proviseur extends Personne {

	/**
	 * @author Nathanaël
	 * 
	 *         La class represente un proviseur d'un EPLE<br/>
	 */

	@ManyToMany
	private List<Etablissement> etablissements = null;

	/**
	 * Constructeur vide pour la gestion de la DB<br/>
	 */
	public Proviseur() {
		super();
	}

	/**
	 * Constructeur pour un proviseur qui appartient a un seul
	 * etablissement<br/>
	 * 
	 * @param etab
	 */
	public Proviseur(Etablissement etab) {
		this();
		addEtablissement(etab);
	}

	/**
	 * Constructeur pour un proviseur qui appartient a plusieurs
	 * etablissements<br/>
	 * 
	 * @param etabs
	 */
	public Proviseur(List<Etablissement> etabs) {
		this();

	}

	private void addEtablissement(Etablissement etab) {
		getEtablissements().add(etab);
	}

	/**
	 * 
	 * @return La liste d'etablissemnt d'un proviseur<br/>
	 */
	public List<Etablissement> getEtablissements() {
		if (etablissements == null)
			etablissements = new ArrayList<Etablissement>();
		return etablissements;
	}

	/**
	 * 
	 * @return L'établissement d'un proviseur<br/>
	 */
	public Etablissement getEtablissement() {
		if (getEtablissements().size() == 1)
			return getEtablissements().get(0);
		return null;
	}

}
