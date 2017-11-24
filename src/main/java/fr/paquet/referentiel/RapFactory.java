package fr.paquet.referentiel;

import javax.persistence.EntityTransaction;

import fr.paquet.dataBase.Connect;

public class RapFactory extends Connect {

	
	/**
	 * Sauvegarde d'un Rap<br/>
	 */

	public void save(Rap rap) {
		EntityTransaction t = getEm().getTransaction();
		try {
			getEm().persist(rap);
			t.commit();
		} catch (Exception e) {

			t.rollback();
			throw (e);
		}
	}
}
