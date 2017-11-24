package fr.paquet.referentiel;

import javax.persistence.EntityTransaction;

import fr.paquet.dataBase.Connect;

public class CritereEvaluationFactory extends Connect {

	
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
