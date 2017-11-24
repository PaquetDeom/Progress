package fr.paquet.referentiel;

import java.util.List;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import fr.paquet.commun.Diplome;
import fr.paquet.dataBase.Connect;

public class ReferentielFactory extends Connect {

	
	@SuppressWarnings("unchecked")
	/**
	 * 
	 * @return tous les referentiels<br/>
	 */
	public List<Referentiel> findReferentiels() throws Exception {

		Query query = getEm().createQuery("SELECT ref FROM Referentiel ref");
		List<Referentiel> referentiels = (List<Referentiel>) query.getResultList();
		if (referentiels.isEmpty())
			throw new Exception("Il n'y a pas de referentiel dans la base de donnee");
		return referentiels;

	}

	/**
	 * 
	 * @param diplome
	 * @return Le referentiel du diplome<br/>
	 */
	public Referentiel findReferentiel(Diplome diplome) {
		Query query = getEm().createQuery("SELECT ref FROM Referentiel ref where ref.diplome=:diplome");
		query.setParameter("diplome", diplome);
		return (Referentiel) query.getSingleResult();
	}

	/**
	 * Methode pour sauvegarder un referentiel<br/>
	 * 
	 * @param ref
	 * @throws Exception
	 *             si le referentiel est deja dans la DB<br/>
	 */
	public void save(Referentiel ref) throws Exception {

		if (findReferentiels().contains(ref))
			throw new Exception("Ce referentiel existe dans la base de donnée");
		EntityTransaction t = getEm().getTransaction();
		try {
			t.begin();
			getEm().persist(ref);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}

	public static void remove(Referentiel ref) {
		EntityTransaction t = getEm().getTransaction();
		try {
			t.begin();
			getEm().remove(ref);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}

}
