package fr.paquet.referentiel;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class SousSavoirAssocieFactory extends ProgressFactory {

	/**
	 * @author Nathanaël
	 * 
	 *         La class est enfant de ProgressFactory()<br/>
	 */

	public SousSavoirAssocieFactory(EntityManager em) {
		super();
		setEm(em);
	}

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
