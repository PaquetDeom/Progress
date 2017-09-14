package fr.paquet.referentiel;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class ConditionFactory extends ProgressFactory {

	/**
	 * La class extends ProgressFactory()<br/>
	 * 
	 * @param em
	 */
	public ConditionFactory(EntityManager em) {
		super();
		setEm(em);
	}

	/**
	 * Sauvegarde d'une condition<br/>
	 * 
	 * @param cond
	 */

	public void save(Condition cond) {
		EntityTransaction t = getEm().getTransaction();
		try {
			getEm().persist(cond);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}
}
