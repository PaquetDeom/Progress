package fr.paquet.progression;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import fr.paquet.progression.Progression;
import fr.paquet.referentiel.ProgressFactory;
import fr.paquet.referentiel.Referentiel;

public class ProgressionFactory extends ProgressFactory {

	public ProgressionFactory(EntityManager em) {
		super();
		setEm(em);
	}

	/**
	 * 
	 * @return La liste des progressions d'un referentiel<br/>
	 */
	@SuppressWarnings("unchecked")
	public List<Progression> findProgressions(Referentiel ref) throws Exception {
		Query query = getEm().createQuery("SELECT prog FROM Progression prog where prog.referentiel=:referentiel");
		query.setParameter("referentiel", ref);
		List<Progression> progressions = (List<Progression>) query.getResultList();
		if (progressions.isEmpty())
			throw new Exception("Il n'y a pas de progression correspondante à ce réferentiel dans la base de donnée");
		return progressions;
	}

	/**
	 * 
	 * @param prog
	 * @param titre
	 * @return Une progression selon son referentiel et son titre<br/>
	 */
	public Progression findProgression(Referentiel ref, String titre) {
		Query query = getEm().createQuery(
				"SELECT prog FROM Progression prog where prog.referentiel=:referentiel and prog.titre=:titre");
		query.setParameter("referentiel", ref);
		query.setParameter("titre", titre);
		return (Progression) query.getSingleResult();
	}

	/**
	 * Sauvegarde d'une progression<br/>
	 * 
	 * @param prog
	 */
	public void save(Progression prog) throws Exception {
		if (findProgressions(prog.getReferentiel()).contains(prog))
			throw new Exception("Cette progression existe déja dans la base de donnée");
		EntityTransaction t = getEm().getTransaction();
		try {
			t.begin();
			getEm().persist(prog);
			t.commit();

		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}

	/**
	 * supprime une progression de la base de donne<br/>
	 * 
	 * @param prog
	 */
	public void remove(Progression prog) {
		EntityTransaction t = getEm().getTransaction();
		try {

			getEm().remove(prog);
			t.commit();

		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}
}
