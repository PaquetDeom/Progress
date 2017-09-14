package fr.paquet.referentiel;

import java.util.HashMap;

/**
 * 
 * @author Nathanaël
 *
 *         La classe teste les verbes des intitule de Competence et/ou de
 *         competenceIntermediaire et renvoie leur niveau.
 */
public class TaxonomieBlum {

	private static HashMap<String, Integer> list = null;

	public static void addTaxonomieBlum(String verbe, int niveau) {
		getList().put(verbe, niveau);
	}

	public static HashMap<String, Integer> getList() {
		if (list == null)
			list = new HashMap<String, Integer>();
		return list;
	}

	/**
	 * @param verbe
	 *            à l'infinitif en majuscule
	 * @return le niveau taxonomique du verbe. renvoie 0 si non trouvé
	 */
	public static int getNiveau(String verbe) {
		if (getList().get(verbe) == null)
			return 0;
		else
			return getList().get(verbe);
	}
}
