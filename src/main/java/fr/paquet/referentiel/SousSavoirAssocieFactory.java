package fr.paquet.referentiel;

import javax.persistence.EntityTransaction;

import fr.paquet.dataBase.Connect;

public class SousSavoirAssocieFactory extends Connect {



	/**
	 * Sauvegarde d'un SousSavoirAssocie()<br/>
	 */

	public void save(SousSavoirAssocie ssa) {
		EntityTransaction t = getEm().getTransaction();

		try {
			getEm().persist(ssa);
			t.commit();

		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}
}
