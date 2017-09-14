package fr.paquet.referentiel;

import javax.persistence.EntityManager;

public class ProgressFactory {

	/**
	 * @author Nathanaël
	 * 
	 *         Classe mére de toutes les class (Factory) du package
	 *         référentiel<br/>
	 */

	private static EntityManager em;

	public static EntityManager getEm() {
		return em;
	}

	public static void setEm(EntityManager em) {
		ProgressFactory.em = em;
	}

}
