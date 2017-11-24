package fr.paquet.ihm.Import;

import java.util.EnumSet;

import fr.paquet.io.edt.EleveAvecAdresseIntegration;
import fr.paquet.io.edt.ExpProfesseur;
import fr.paquet.io.siecle.CommunIntegration;
import fr.paquet.io.siecle.GeographiqueIntegration;
import fr.paquet.io.siecle.NomenclatureIntegration;
import fr.paquet.io.siecle.RneIntegration;
import fr.paquet.io.siecle.XMLFileIntegration;

public enum XMLDocuments implements Comparable<XMLDocuments> {

	GEOGRAPHIQUE, COMMUN, NOMENCLATURE, ELEVEAVECADRESSE, EXP_ELEVE, EXP_PROFESSEUR;

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
		case ELEVEAVECADRESSE:
			return "elevesAvecAdresses.xml";
		case GEOGRAPHIQUE:
			return "geographique.xml";
		case NOMENCLATURE:
			return "nomenclature.xml";
		case EXP_PROFESSEUR:
			return "EXP_PROFESSEUR.xml";
		case EXP_ELEVE:
			return "EXP_ELEVE.xml";
		}
		return null;
	}

	public boolean isSiecle() {
		switch (this) {
		case COMMUN:
		case ELEVEAVECADRESSE:
		case GEOGRAPHIQUE:
		case NOMENCLATURE:
			return true;
		default:
			return false;
		}
	}

	public boolean isEDT() {
		switch (this) {
		case EXP_PROFESSEUR:
		case EXP_ELEVE:
			return true;
		default:
			return false;
		}
	}

	public Class<? extends XMLFileIntegration> getIntegrator() {
		switch (this) {
		case COMMUN:
			return CommunIntegration.class;
		case GEOGRAPHIQUE:
			return GeographiqueIntegration.class;
		case NOMENCLATURE:
			return NomenclatureIntegration.class;
		case ELEVEAVECADRESSE:
			return EleveAvecAdresseIntegration.class;
		case EXP_ELEVE:
			return null;
		case EXP_PROFESSEUR:
			return ExpProfesseur.class;
		}
		return null;
	}

}