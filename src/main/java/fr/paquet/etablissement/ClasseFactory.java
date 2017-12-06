package fr.paquet.etablissement;

import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.w3c.dom.Element;

import fr.paquet.dataBase.Connect;

public class ClasseFactory extends Connect {

	private static ClasseFactory uniqInstance = null;

	/**
	 * Sauvegarde une classe<br/>
	 * 
	 * @param cla
	 */
	public void save(Classe cla) {

		EntityTransaction t = getEm().getTransaction();
		try {

			t.begin();
			getEm().persist(cla);
			t.commit();

		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}

	/**
	 * Remove une classe<br/>
	 * 
	 * @param elv
	 */
	public void remove(Classe cla) {
		EntityTransaction t = getEm().getTransaction();
		try {

			t.begin();
			getEm().remove(cla);
			t.commit();

		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}

	public Classe findClasseByFormation(String formation, String numero) {

		// TODO
		Query query = getEm().createQuery("SELECT Classe FROM Classe classe where classe.intitule=:formation");
		query.setParameter("formation", formation);

		try {

			return (Classe) query.getSingleResult();

		} catch (NoResultException e) {
			e.printStackTrace();
			System.out.println(numero);

			return null;
		}
	}

	public void Load(Element elt, Etablissement etab) {

		try {

			save(new Classe(elt, etab));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return L'intance unique de la class<br/>
	 */
	public static ClasseFactory getInstance() {
		if (uniqInstance == null) {
			uniqInstance = new ClasseFactory();
		}
		return uniqInstance;
	}

}
