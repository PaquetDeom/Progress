package fr.paquet.etablissement;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.w3c.dom.Element;

import fr.paquet.dataBase.Connect;
import fr.paquet.referentiel.ProgressFactory;

public class ResponsableEleveFactory extends ProgressFactory {

	public ResponsableEleveFactory(EntityManager em) {
		super();
		setEm(em);
	}

	public ResponsableEleveFactory(Element elt) {

		Load(elt);
	}

	/**
	 * Sauvegarde un eleve<br/>
	 * 
	 * @param resp
	 */
	public void save(ResponsableEleve resp) {

		EntityTransaction t = getEm().getTransaction();
		try {

			t.begin();
			getEm().persist(resp);
			t.commit();

		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}

	/**
	 * Remove un eleve<br/>
	 * 
	 * @param resp
	 */
	public void remove(ResponsableEleve resp) {
		EntityTransaction t = getEm().getTransaction();
		try {

			t.begin();
			getEm().remove(resp);
			t.commit();

		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}

	public void Load(Element elt) {

		try {
			ResponsableEleve resp = new ResponsableEleve();
			int id = 0;
			String nomDeFamille = null;
			String prenom = null;

			Boolean sexe = false;

			id = Integer.parseInt(elt.getAttribute("PERSONNE_ID"));
			resp.setId(id);

			String exp1 = "NOM_DE_FAMILLE";
			nomDeFamille = elt.getElementsByTagName(exp1).item(0).getTextContent();
			resp.setNom(nomDeFamille);

			// TODO
			String exp2 = "PRENOM";
			prenom = elt.getElementsByTagName(exp2).item(0).getTextContent();
			resp.setPrenom(prenom);

			String exp9 = "LL_CIVILITE";
			String str9 = null;
			if (elt.getElementsByTagName(exp9) != null && elt.getElementsByTagName(exp9).getLength() != 0) {
				str9 = elt.getElementsByTagName(exp9).item(0).getTextContent();
				String[] str = str9.trim().split(" ");
				str9 = str[0];
				if (!str9.equals("MONSIEUR"))
					sexe = true;
				else
					sexe = false;
				resp.setSexe(sexe);
			}

			else
				sexe = null;

			String exp10 = "ADRESSE_ID";
			String str10 = null;
			if (elt.getElementsByTagName(exp10) != null && elt.getElementsByTagName(exp10).getLength() != 0) {
				str10 = elt.getElementsByTagName(exp10).item(0).getTextContent();
				long id1 = Integer.parseInt(str10);
				resp.setCoordonnee(new CoordonneeFactory(Connect.getEmf().createEntityManager()).find(id1));
			}

			new ResponsableEleveFactory(Connect.getEmf().createEntityManager()).save(resp);

			resp = null;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
