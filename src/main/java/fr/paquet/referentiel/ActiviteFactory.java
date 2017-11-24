package fr.paquet.referentiel;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import fr.paquet.dataBase.Connect;

public class ActiviteFactory extends Connect {



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
