package fr.paquet.etablissement;

import javax.persistence.EntityTransaction;

import org.w3c.dom.Element;

import fr.paquet.dataBase.Connect;

public class ProfesseurFactory extends Connect {

	private static ProfesseurFactory uniqInstance = null;

	/**
	 * Sauvegarde un professeur<br/>
	 * 
	 * @param cla
	 */
	public void save(Professeur prof) {

		EntityTransaction t = getEm().getTransaction();
		try {

			t.begin();
			getEm().persist(prof);
			t.commit();

		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}

	/**
	 * Remove un professeur<br/>
	 * 
	 * @param elv
	 */
	public void remove(Professeur prof) {
		EntityTransaction t = getEm().getTransaction();
		try {

			t.begin();
			getEm().remove(prof);
			t.commit();

		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}

	public void Load(Element elt) {

		try {

			save(new Professeur(elt));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return l'intance unique de la class<br/>
	 */
	public static ProfesseurFactory getInstance() {
		if (uniqInstance == null) {
			uniqInstance = new ProfesseurFactory();
		}
		return uniqInstance;
	}

}
