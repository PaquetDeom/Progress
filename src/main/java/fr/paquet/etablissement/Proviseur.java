package fr.paquet.etablissement;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PROVISEUR")
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "PRPRID")),
		@AttributeOverride(name = "nom", column = @Column(name = "PRPRNOM", length = 20)),
		@AttributeOverride(name = "prenom", column = @Column(name = "PRPRPRENOM", length = 50)), })
public class Proviseur extends Personne {

	/**
	 * @author Nathanaël
	 * 
	 *         La class represente un proviseur d'un EPLE<br/>
	 */

	/**
	 * Constructeur vide pour la gestion de la DB<br/>
	 */
	public Proviseur() {
		super();
	}

}
