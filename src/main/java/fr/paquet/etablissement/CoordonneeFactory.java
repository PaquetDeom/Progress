package fr.paquet.etablissement;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.w3c.dom.Element;

import fr.paquet.dataBase.Connect;


public class CoordonneeFactory extends Connect {

	
	public CoordonneeFactory(Element elt) {

		Load(elt);
	}

	/**
	 * Sauvegarde une coordonnee<br/>
	 * 
	 * @param elv
	 */
	public void save(Coordonnee coord) {

		EntityTransaction t = getEm().getTransaction();
		try {

			t.begin();
			getEm().persist(coord);
			t.commit();

		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}

	/**
	 * Remove une coordonnee<br/>
	 * 
	 * @param coor
	 */
	public void remove(Coordonnee coord) {
		EntityTransaction t = getEm().getTransaction();
		try {

			t.begin();
			getEm().remove(coord);
			t.commit();

		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}

	/**
	 * 
	 * @param id
	 * @return une coordonnee depuis son id<br/>
	 */
	public Coordonnee find(long id) {

		Query query = getEm().createQuery("SELECT coord FROM Coordonnee coord where coord.id=:id");
		query.setParameter("id", id);

		try {

			return (Coordonnee) query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}

	}

	public void Load(Element elt) {

		long id = 0;
		Coordonnee coord = new Coordonnee();
		String adresse1 = null;
		String adresse2 = null;
		String adresse3 = null;
		String adresse4 = null;
		String codeCommune = null;
		Commune com = null;
		Geographie geo = null;

		try {

			id = Integer.parseInt(elt.getAttribute("ADRESSE_ID"));

			coord.setId(id);

			String exp1 = "LIGNE1_ADRESSE";
			if (elt.getElementsByTagName(exp1) != null && elt.getElementsByTagName(exp1).getLength() != 0) {
				adresse1 = elt.getElementsByTagName(exp1).item(0).getTextContent();
				coord.setAdresse1(adresse1);
			}

			String exp2 = "LIGNE2_ADRESSE";
			if (elt.getElementsByTagName(exp2) != null && elt.getElementsByTagName(exp2).getLength() != 0) {
				adresse2 = elt.getElementsByTagName(exp2).item(0).getTextContent();
				coord.setAdresse2(adresse2);
			}

			String exp3 = "LIGNE3_ADRESSE";
			if (elt.getElementsByTagName(exp3) != null && elt.getElementsByTagName(exp3).getLength() != 0) {
				adresse3 = elt.getElementsByTagName(exp3).item(0).getTextContent();
				coord.setAdresse3(adresse3);
			}

			String exp4 = "LIGNE4_ADRESSE";
			if (elt.getElementsByTagName(exp4) != null && elt.getElementsByTagName(exp4).getLength() != 0) {
				adresse4 = elt.getElementsByTagName(exp4).item(0).getTextContent();
				coord.setAdresse4(adresse4);
			}

			String exp6 = "CODE_COMMUNE_INSEE";
			if (elt.getElementsByTagName(exp6) != null && elt.getElementsByTagName(exp6).getLength() != 0) {
				codeCommune = elt.getElementsByTagName(exp6).item(0).getTextContent();
				com = new CommuneFactory().findCommune(codeCommune);
				coord.setCommune(com);
			}

			String exp7 = "CODE_PAYS";
			if (elt.getElementsByTagName(exp7).item(0).getTextContent() != null
					&& elt.getElementsByTagName(exp7).getLength() != 0) {
				int idCodePays = Integer.parseInt(elt.getElementsByTagName(exp7).item(0).getTextContent());
				GeographieFactory.getInstance().find(idCodePays);
				coord.setGeographie(geo);
			}

			if (find(id) == null)
				save(coord);

			coord = null;

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
