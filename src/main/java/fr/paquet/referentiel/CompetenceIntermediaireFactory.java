package fr.paquet.referentiel;

import javax.persistence.EntityTransaction;

import fr.paquet.dataBase.Connect;

public class CompetenceIntermediaireFactory extends Connect {

	
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
