package fr.paquet.referentiel;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class UniteFactory extends ProgressFactory {

	/**
	 * @author Nathanaël
	 * 
	 *         La class est enfant de ProgressFactory()<br/>
	 */

	public UniteFactory(EntityManager em) {
		super();
		setEm(em);
	}

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
