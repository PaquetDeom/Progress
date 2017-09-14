package fr.paquet.referentiel;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class SavoirAssocieFactory extends ProgressFactory {

	/**
	 * @author Nathanaël
	 * 
	 *         La class est enfant de ProgressFactory()<br/>
	 */

	public SavoirAssocieFactory(EntityManager em) {
		super();
		setEm(em);
	}

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
