package fr.paquet.referentiel;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class TacheFactory extends ProgressFactory {

	/**
	 * @author Nathanaël
	 * 
	 *         La class est enfant de ProgressFactory()<br/>
	 */

	public TacheFactory(EntityManager em) {
		super();
		setEm(em);
	}

	/**
	 * Sauvegarde d une Tache()<br/>
	 */

	public void save(Tache tac) {
		EntityTransaction t = getEm().getTransaction();
		try {
			getEm().persist(tac);
			t.commit();

		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}
}
