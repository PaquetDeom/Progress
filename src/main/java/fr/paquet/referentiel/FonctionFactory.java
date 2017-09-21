package fr.paquet.referentiel;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class FonctionFactory extends ProgressFactory {

	/**
	 * @author Nathanaël
	 * 
	 *         La class est enfant de ProgressFactory()<br/>
	 */

	public FonctionFactory(EntityManager em) {
		super();
		setEm(em);
	}

	/**
	 * Sauvegarde de Fonction()<br/>
	 */

	public void save(Fonction fc) {
		EntityTransaction t = getEm().getTransaction();
		try {
			t.begin();
			getEm().persist(fc);
			t.commit();

		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}

}
