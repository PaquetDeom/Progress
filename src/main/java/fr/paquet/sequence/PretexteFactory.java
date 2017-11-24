package fr.paquet.sequence;

import java.util.List;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import fr.paquet.dataBase.Connect;


public class PretexteFactory extends Connect {

	/**
	 * 
	 * @return La liste des pretextes<br/>
	 */
	@SuppressWarnings("unchecked")
	public List<Pretexte> findPretextes() {
		Query query = getEm().createQuery("SELECT pret FROM Pretexte pret ");

		return (List<Pretexte>) query.getResultList();
	}

	/**
	 * Sauvegarde d'un pretexte<br/>
	 * 
	 * @param seq
	 */
	public void save(Pretexte pret) {
		EntityTransaction t = getEm().getTransaction();
		try {

			getEm().persist(pret);
			t.commit();

		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}

	/**
	 * Supprime un pretexte de la base<br/>
	 * 
	 * @param pret
	 */
	public void remove(Pretexte pret) {
		EntityTransaction t = getEm().getTransaction();
		try {

			getEm().remove(pret);
			t.commit();

		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}
}
