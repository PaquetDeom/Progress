package fr.paquet.referentiel;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class SavoirFactory extends ProgressFactory {

	/**
	 * @author Nathanaël
	 * 
	 *         La class est enfant de ProgressFactory()<br/>
	 */

	public SavoirFactory(EntityManager em) {
		super();
		setEm(em);
	}

	/**
	 * Sauvegarde d'un Savoir()<br/>
	 */

	public void save(Savoir sav) {
		EntityTransaction t = getEm().getTransaction();
		try {
			getEm().persist(sav);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}
}
