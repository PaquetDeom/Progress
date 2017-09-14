package fr.paquet.referentiel;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class ActiviteFactory extends ProgressFactory {

	/**
	 * @author Nathanaël
	 * 
	 *         La class est enfant de ProgressFactory()<br/>
	 */

	public ActiviteFactory(EntityManager em) {
		super();
		setEm(em);
	}

	/**
	 * Sauvegarde de Activite<br/>
	 */

	public void save(Activite act) {
		EntityTransaction t = getEm().getTransaction();
		try {
			getEm().persist(act);
			t.commit();

		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}
}
