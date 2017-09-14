package fr.paquet.referentiel;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class CritereEvaluationFactory extends ProgressFactory {

	/**
	 * La class extends CritereEvaluation()<br/>
	 * 
	 * @param em
	 */

	public CritereEvaluationFactory(EntityManager em) {
		super();
		setEm(em);
	}

	/**
	 * Sauvegarde d'un critere d'evaluation<br/>
	 * 
	 * @param crit
	 */
	public void save(CritereEvaluation crit) {
		EntityTransaction t = getEm().getTransaction();
		try {
			getEm().persist(crit);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}
}
