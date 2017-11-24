package fr.paquet.referentiel;


import javax.persistence.EntityTransaction;

import fr.paquet.dataBase.Connect;

public class CompetenceFactory extends Connect {

	
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
