package fr.paquet.etablissement;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.w3c.dom.Element;

import fr.paquet.dataBase.Connect;

public class CommuneFactory extends Connect {

	private static CommuneFactory uniqInstance = null;

	/**
	 * Sauvegarde une commune<br/>
	 * 
	 * @param com
	 */
	public void save(Commune com) {

		EntityTransaction t = getEm().getTransaction();
		try {

			t.begin();
			getEm().persist(com);
			t.commit();

		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}

	/**
	 * 
	 * @param id
	 * @return une Commune en fonction de son codeCommune<br/>
	 */
	public Commune findCommune(String codeCommune) {

		Query query = getEm().createQuery("SELECT com FROM Commune com where com.codeCommune=:codeCommune");
		query.setParameter("codeCommune", codeCommune);
		return (Commune) query.getSingleResult();
	}

	/**
	 * Remove une commune<br/>
	 * 
	 * @param com
	 */
	public void remove(Commune com) {
		EntityTransaction t = getEm().getTransaction();
		try {

			t.begin();
			getEm().remove(com);
			t.commit();

		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}

	/**
	 * Charge la Table commune de la DB<br/>
	 * 
	 * @param elt
	 */
	public void Load(Element elt) {

		try {
			save(new Commune(elt));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @return l'intance unique de la class<br/>
	 */
	public static CommuneFactory getInstance() {
		if (uniqInstance == null) {
			uniqInstance = new CommuneFactory();
		}
		return uniqInstance;
	}
}
