package fr.paquet.referentiel;

import javax.persistence.EntityTransaction;

import fr.paquet.dataBase.Connect;

public class SavoirFactory extends Connect {

	

	/**
	 * Sauvegarde d'un Savoir()<br/>
	 */

	public void save(Savoir sav) {
		EntityTransaction t = getEm().getTransaction();
		try {
			getEm().persist(sav);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}
}
