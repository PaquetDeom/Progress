package fr.paquet.commun;

import java.util.List;
import javax.persistence.Query;

import fr.paquet.dataBase.Connect;

public class DiplomeFactory extends Connect {

	@SuppressWarnings("unchecked")
	/**
	 * 
	 * @return tous les diplomes<br/>
	 */
	public List<Diplome> findDiplomes() throws Exception {

		Query query = getEm().createQuery("SELECT dip FROM Diplome dip");
		List<Diplome> diplomes = (List<Diplome>) query.getResultList();
		if (diplomes.isEmpty())
			throw new Exception("Il n'y a pas de diplome dans la base de donnee");
		return diplomes;

	}

	/**
	 * 
	 * @param diplome
	 * @param type
	 * @return un diplome donne<br/>
	 */
	public Diplome findDiplome(String diplome, String type) {
		Query query = getEm().createQuery("SELECT dip FROM Diplome dip where dip.diplome=:diplome and dip.type=:type");
		query.setParameter("diplome", diplome);
		query.setParameter("type", type);
		return (Diplome) query.getSingleResult();
	}
}
