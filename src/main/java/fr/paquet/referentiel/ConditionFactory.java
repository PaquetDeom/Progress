package fr.paquet.referentiel;

import javax.persistence.EntityTransaction;

import fr.paquet.dataBase.Connect;

public class ConditionFactory extends Connect {

	
	/**
	 * Sauvegarde d'une condition<br/>
	 * 
	 * @param cond
	 */

	public void save(Condition cond) {
		EntityTransaction t = getEm().getTransaction();
		try {
			t.begin();
			getEm().persist(cond);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}
}
