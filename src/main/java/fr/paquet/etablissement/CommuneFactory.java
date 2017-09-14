package fr.paquet.etablissement;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.w3c.dom.Element;

import fr.paquet.dataBase.Connect;
import fr.paquet.referentiel.ProgressFactory;

public class CommuneFactory extends ProgressFactory {

	public CommuneFactory(EntityManager em) {
		super();
		setEm(em);
	}

	public CommuneFactory(Element elt) {
		Load(elt);
	}
	

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
			Commune com = new Commune();
			String codeCommune = null;
			String commune = null;

			codeCommune = elt.getAttribute("CODE_COMMUNE_INSEE");
			com.setCodeCommune(codeCommune);

			String exp2 = "LIBELLE_LONG";
			commune = elt.getElementsByTagName(exp2).item(0).getTextContent();
			com.setCommune(commune);

			new CommuneFactory(Connect.getEmf().createEntityManager()).save(com);

			com = null;

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
