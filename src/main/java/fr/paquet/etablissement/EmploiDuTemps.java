package fr.paquet.etablissement;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOI_DU_TEMPS")
public class EmploiDuTemps {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private long id = 0;

	@OneToOne
	private Classe classe = null;

	@OneToOne
	private Eleve eleve = null;

	@OneToOne
	private Professeur prof = null;

	public EmploiDuTemps() {
		super();
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public void setEleve(Eleve eleve) {
		this.eleve = eleve;
	}

	public void setProf(Professeur prof) {
		this.prof = prof;
	}

	public long getId() {
		return id;
	}

	public Classe getClasse() {
		return classe;
	}

	public Eleve getEleve() {
		return eleve;
	}

	public Professeur getProf() {
		return prof;
	}

}
