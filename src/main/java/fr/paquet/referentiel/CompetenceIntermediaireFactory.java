package fr.paquet.referentiel;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class CompetenceIntermediaireFactory extends ProgressFactory {

	/**
	 * La class hérite de ProgressFactory<br/>
	 * 
	 * @param em
	 */

	public CompetenceIntermediaireFactory(EntityManager em) {
		super();
		setEm(em);
	}

	/**
	 * Sauvegarde une competence intermediaire<br/>
	 * 
	 * @param compInt
	 */

	public void save(CompetenceIntermediaire compInt) {
		EntityTransaction t = getEm().getTransaction();
		try {
			getEm().persist(compInt);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}

}
