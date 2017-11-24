package fr.paquet.referentiel;

import javax.persistence.EntityTransaction;

import fr.paquet.dataBase.Connect;

public class TacheFactory extends Connect {

	
	/**
	 * Sauvegarde d une Tache()<br/>
	 */

	public void save(Tache tac) {
		EntityTransaction t = getEm().getTransaction();
		try {
			getEm().persist(tac);
			t.commit();

		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}
}
