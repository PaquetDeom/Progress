package fr.paquet.referentiel;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class CapaciteFactory extends ProgressFactory {

	/**
	 * La class extends ProgressFactory
	 * 
	 * @param em
	 */
	public CapaciteFactory(EntityManager em) {
		super();
		setEm(em);
	}

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
