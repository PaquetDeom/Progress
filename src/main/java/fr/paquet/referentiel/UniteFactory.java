package fr.paquet.referentiel;

import javax.persistence.EntityTransaction;

import fr.paquet.dataBase.Connect;

public class UniteFactory extends Connect {

	
	/**
	 * Sauvegarde d'une unite()<br/>
	 */

	public void save(Unite unt) {
		EntityTransaction t = getEm().getTransaction();
		try {
			getEm().persist(unt);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}
}
