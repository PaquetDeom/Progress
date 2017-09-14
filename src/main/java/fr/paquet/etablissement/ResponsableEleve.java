package fr.paquet.etablissement;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RESPONSABLE_ELEVE")
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "REREID")),
		@AttributeOverride(name = "nom", column = @Column(name = "RERENOM", length = 100)),
		@AttributeOverride(name = "prenom", column = @Column(name = "REREPRENOM", length = 50)),
		@AttributeOverride(name = "dateEntree", column = @Column(name = "REREDTENTREE")),
		@AttributeOverride(name = "dateSortie", column = @Column(name = "REREDTSORTIE")),
		@AttributeOverride(name = "dateDeNaissance", column = @Column(name = "REREDTNAISSANCE")),
		@AttributeOverride(name = "masculin", column = @Column(name = "RERESEXE")) })
public class ResponsableEleve extends Personne implements ACoordonnee {

	@JoinColumn(name = "REELID")
	@OneToMany
	private List<Eleve> eleves = null;

	@JoinColumn(name = "RECOID")
	@OneToOne
	private Coordonnee coordonnee = null;

	public ResponsableEleve() {
		super();
	}

	public ResponsableEleve(Eleve elv) {
		this();
		addEleve(elv);
	}

	public void setCoordonnee(Coordonnee coordonnee) {
		this.coordonnee = coordonnee;
	}

	public void addEleve(Eleve elv) {
		getEleves().add(elv);
	}

	/**
	 * 
	 * @return La liste des eleves qui dependent de responsable<br/>
	 */
	public List<Eleve> getEleves() {
		if (eleves == null)
			eleves = new ArrayList<Eleve>();
		return eleves;
	}

	/**
	 * 
	 * @return L'eleve qui depend de responsable<br/>
	 */
	public Eleve getEleve() {
		if (getEleves().size() == 1)
			return getEleves().get(0);
		return null;
	}

	@Override
	public Coordonnee getCoordonnee() {
		// TODO Auto-generated method stub
		return null;
	}

}
