package fr.paquet.progression;

import fr.paquet.referentiel.SavoirAssocie;

public class ConfigSavoirAssocie {

	private SavoirAssocie savoirAssocie = null;
	private String type;

	public ConfigSavoirAssocie(SavoirAssocie savoirAssocie, String type) throws Exception {

		setSavoirAssocie(savoirAssocie);
		setType(type);
	}

	private void setType(String type2) throws Exception {

		type2 = type2.trim().toUpperCase();

		if (!type2.equals("SAVOIR THEORIQUE") | !type2.equals("SAVOIR PROCEDURAL")
				| !type2.equals("SAVOIR FAIRE PROCEDURAL") | !type2.equals("SAVOIR FAIRE EXPERIENTIEL")
				| !type2.equals("SAVOIR FAIRE SOCIAL") | !type2.equals("SAVOIR COGNITIF"))
			throw new Exception("type de savoir invalide");

		this.type = type2.trim().toUpperCase();

	}

	private void setSavoirAssocie(SavoirAssocie savoirAssocie2) {

		this.savoirAssocie = savoirAssocie2;

	}

	public SavoirAssocie getSavoirAssocie() {
		return savoirAssocie;
	}

	public String getType() {
		return type;
	}

}
