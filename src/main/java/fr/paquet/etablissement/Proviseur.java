package fr.paquet.etablissement;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.w3c.dom.Element;

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
		super(null);
	}
	
	/**
	 * Constructeur de la class qui permet la creation d'un proviseur a partir d'un *.xml<br/>
	 * @param elt
	 * @throws Exception
	 */
	public Proviseur(Element elt) throws Exception {
		super(elt);
		String exp1 = "NOM_RESP";
		String str1 = elt.getElementsByTagName(exp1).item(0).getTextContent();
		String[] str1Tab1 = str1.split(" ");
		setNom(getStringFromXml(str1Tab1[1]));
		setPrenom(getStringFromXml(str1Tab1[0]));
		//TODO Affect un etablissement
	}

}
