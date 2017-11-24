package fr.paquet.etablissement;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.w3c.dom.Element;

import com.ibm.icu.text.SimpleDateFormat;

import fr.paquet.dataBase.Connect;

public class EleveFactory extends Connect {

	private static EleveFactory uniqInstance = null;

	/**
	 * Sauvegarde un eleve<br/>
	 * 
	 * @param elv
	 */
	public void save(Eleve elv) {

		EntityTransaction t = getEm().getTransaction();
		try {

			t.begin();
			getEm().persist(elv);
			t.commit();

		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}

	/**
	 * Sauvegarde un eleve<br/>
	 * 
	 * @param elv
	 */
	public void update(Eleve elv) {

		EntityTransaction t = getEm().getTransaction();
		try {

			t.begin();
			getEm().merge(elv);
			t.commit();

		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}

	/**
	 * Remove un eleve<br/>
	 * 
	 * @param elv
	 */
	public void remove(Eleve elv) {
		EntityTransaction t = getEm().getTransaction();
		try {

			t.begin();
			getEm().remove(elv);
			t.commit();

		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}

	/**
	 * 
	 * @param name
	 * @param firstName
	 * @return Un eleve avec son Nom et son Prenom<br/>
	 */
	public Eleve FindByNameAndFirstName(String name, String firstName) {

		Query query = getEm()
				.createQuery("SELECT Eleve FROM Eleve eleve where eleve.name=:name and eleve.firstName=:firstName");
		query.setParameter("name", name);
		query.setParameter("firstName", firstName);

		try {

			return (Eleve) query.getSingleResult();

		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static Eleve findEleveByNameFirstName(String name, String firstName) {
		Query query = getEm()
				.createQuery("SELECT eleve FROM Eleve eleve where eleve.nom=:name AND eleve.prenom=:firstName");
		query.setParameter("name", name);
		query.setParameter("firstName", firstName);
		return (Eleve) query.getSingleResult();
	}

	/**
	 * Affectation d'une classe a un eleve<br/>
	 * 
	 * @param elv
	 */
	public void AffectClasse(String name, String firstName, Classe cla) {
		Eleve e = findEleveByNameFirstName(name, firstName);
		e.addClasse(cla);
		save(e);
	}

	public void Load(Element elt) {

		try {

			save(new Eleve(elt));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @return l'intance unique de la classe<br/>
	 */
	public static EleveFactory getInstance() {
		if (uniqInstance == null) {
			uniqInstance = new EleveFactory();
		}
		return uniqInstance;
	}

}
