package fr.paquet.referentiel;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class RapFactory extends ProgressFactory {

	/**
	 * @author Nathanaël
	 * 
	 *         La classe est enfant de ProgressFactory()<br/>
	 */

	public RapFactory(EntityManager em) {
		super();
		setEm(em);
	}

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
