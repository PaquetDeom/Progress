package fr.paquet.sequence;

import java.util.List;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import fr.paquet.dataBase.Connect;
import fr.paquet.progression.Progression;


public class SequenceFactory extends Connect {


	/**
	 * 
	 * @return La liste des sequences d'une progression<br/>
	 */
	@SuppressWarnings("unchecked")
	public List<Sequence> findSequences(Progression progression) {
		Query query = getEm().createQuery("SELECT seq FROM Sequence seq where seq.progression=:progression");
		query.setParameter("progression", progression);
		return (List<Sequence>) query.getResultList();
	}

	/**
	 * 
	 * @param prog
	 * @param titre
	 * @return Une sequence selon sa progression et son titre<br/>
	 */
	public static Sequence findSequence(Progression prog, String titre) {
		Query query = getEm()
				.createQuery("SELECT seq FROM Sequence seq where seq.progression=:progression and seq.titre=:titre");
		query.setParameter("progression", prog);
		query.setParameter("titre", titre);
		return (Sequence) query.getSingleResult();
	}

	/**
	 * Sauvegarde d'une sequence<br/>
	 * 
	 * @param seq
	 */
	public void save(Sequence seq) {
		EntityTransaction t = getEm().getTransaction();
		try {

			t.begin();
			getEm().persist(seq);
			t.commit();

		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}

	/**
	 * supprime une sequence de la base de donne<br/>
	 * 
	 * @param seq
	 * @throws Exception
	 */
	public void remove(Sequence seq) {

		EntityTransaction t = getEm().getTransaction();

		try {
			if (seq != null) {
				t.begin();
				getEm().remove(seq);
				t.commit();
			}

		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}
}
