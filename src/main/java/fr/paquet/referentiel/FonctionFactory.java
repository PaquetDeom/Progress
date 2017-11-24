package fr.paquet.referentiel;

import javax.persistence.EntityTransaction;

import fr.paquet.dataBase.Connect;

public class FonctionFactory extends Connect {


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
