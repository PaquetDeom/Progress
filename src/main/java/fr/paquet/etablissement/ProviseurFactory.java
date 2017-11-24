package fr.paquet.etablissement;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.w3c.dom.Element;

import fr.paquet.dataBase.Connect;

public class ProviseurFactory extends Connect {

	private static ProviseurFactory uniqInqtance = null;

	/**
	 * Sauvegarde un proviseur<br/>
	 * 
	 * @param cla
	 */
	public void save(Proviseur pro) {

		EntityTransaction t = getEm().getTransaction();
		try {

			t.begin();
			getEm().persist(pro);
			t.commit();

		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}

	/**
	 * Remove un proviseur<br/>
	 * 
	 * @param elv
	 */
	public void remove(Proviseur pro) {
		EntityTransaction t = getEm().getTransaction();
		try {

			t.begin();
			getEm().remove(pro);
			t.commit();

		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}

	public void Load(Element elt) {

		try {
			save(new Proviseur(elt));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static ProviseurFactory getInstance() {
		if (uniqInqtance == null) {
			uniqInqtance = new ProviseurFactory();
		}
		return uniqInqtance;
	}

}
