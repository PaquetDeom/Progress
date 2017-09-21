package fr.paquet.referentiel;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class CompetenceFactory extends ProgressFactory {

	public CompetenceFactory(EntityManager em) {
		super();
		setEm(em);
	}

	/**
	 * Sauvegarde une competence<br/>
	 * 
	 * @param comp
	 */
	public void save(Competence comp) {
		EntityTransaction t = getEm().getTransaction();
		try {
			t.begin();
			getEm().persist(comp);
			t.commit();

		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}

}
