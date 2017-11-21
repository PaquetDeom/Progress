package fr.paquet.ihm.Import;

import java.util.EnumSet;

import fr.paquet.io.siecle.ClasseIntegration;
import fr.paquet.io.siecle.EleveIntegration;
import fr.paquet.io.siecle.EtablissementIntegration;
import fr.paquet.io.siecle.GeographieIntegration;
import fr.paquet.io.siecle.RneIntegration;

public enum XMLDocuments {
	COMMUN, eleveAvecAdresse, etablissements, geographique, nomenclature, structures, EXP_PROFESSEUR, EXP_MATIERE, EXP_ELEVE;

	public static XMLDocuments getDocument(String fileName) {
		for (XMLDocuments doc : EnumSet.allOf(XMLDocuments.class)) {
			if (doc.fileName().toUpperCase().equals(fileName.toUpperCase()))
				return doc;
		}
		return null;
	}

	public String fileName() {
		switch (this) {
		case COMMUN:
			return "communs.xml";
		case eleveAvecAdresse:
			return "elevesAvecAdresses.xml";
		case etablissements:
			return "etablissements.xml";
		case geographique:
			return "geographique.xml";
		case nomenclature:
			return "nomenclature.xml";
		case structures:
			return "structures.xml";
		case EXP_PROFESSEUR:
			return "EXP_PROFESSEUR.xml";
		case EXP_MATIERE:
			return "EXP_MATIERE.xml";
		case EXP_ELEVE:
			return "EXP_ELEVE.xml";
		}
		return null;
	}

	public boolean isSiecle() {
		switch (this) {
		case COMMUN:
		case eleveAvecAdresse:
		case etablissements:
		case geographique:
		case nomenclature:
		case structures:
			return true;
		default:
			return false;
		}
	}

	public boolean isEDT() {
		switch (this) {
		case EXP_PROFESSEUR:
		case EXP_MATIERE:
		case EXP_ELEVE:
			return true;
		default:
			return false;
		}
	}

	public Class getIntegrator() {
		switch (this) {
		case COMMUN:
			return EtablissementIntegration.class;
		case eleveAvecAdresse:
			return EleveIntegration.class;
		case etablissements:
			return null;
		case geographique:
			return GeographieIntegration.class;
		case nomenclature:
			return ClasseIntegration.class;
		case structures:
			return null;
		default:
			return null;
		}
	}
}