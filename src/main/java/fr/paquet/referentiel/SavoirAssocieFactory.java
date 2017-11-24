package fr.paquet.referentiel;


import javax.persistence.EntityTransaction;

import fr.paquet.dataBase.Connect;

public class SavoirAssocieFactory extends Connect {

	

	/**
	 * Sauvegarde de SavoirAssocie()<br/>
	 */

	public void save(SavoirAssocie ssa) {
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
