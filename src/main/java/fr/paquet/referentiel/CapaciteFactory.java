package fr.paquet.referentiel;

import javax.persistence.EntityTransaction;

import fr.paquet.dataBase.Connect;

public class CapaciteFactory extends Connect {


	/**
	 * Methode qui gere la sauvegarde d'une capacite<br/>
	 * 
	 * @param cap
	 */
	public void save(Capacite cap) {
		EntityTransaction t = getEm().getTransaction();
		try {
			t.begin();
			getEm().persist(cap);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}

}
