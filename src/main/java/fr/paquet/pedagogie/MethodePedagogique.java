package fr.paquet.pedagogie;

import fr.paquet.progression.ConfigSavoirAssocie;

public class MethodePedagogique {

	/**
	 * @author Nathanaël
	 * 
	 *         La classe retourne une methode pedagogique en fonction du type de
	 *         savoir.
	 * 
	 */

	private static ConfigSavoirAssocie savAss = null;
	private static String methode;

	public static String getType() {
		return savAss.getType();
	}

	public static String getMethodePedagogique() {
		if (getType().equals("SAVOIR PROCEDURAL"))
			methode = "METHODE DECOUVERTE";
		if (getType().equals("SAVOIR COGNITIF"))
			methode = "METHODE INTERROGATIVE";
		if (getType().equals("SAVOIR FAIRE SOCIAL"))
			methode = "METHODE EXPOSITIVE";
		if (getType().equals("SAVOIR FAIRE PROCEDURAL"))
			methode = "METHODE DEMONSTRATIVE";
		if (getType().equals("SAVOIR THEORIQUE"))
			methode = "METHODE ANALOGIQUE";

		return methode;

	}

}
