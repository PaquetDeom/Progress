package fr.paquet.etablissement;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.w3c.dom.Element;

import fr.paquet.dataBase.Connect;
import fr.paquet.referentiel.ProgressFactory;

public class ProviseurFactory extends ProgressFactory {

	public ProviseurFactory(EntityManager em) {
		super();
		setEm(em);
	}

	public ProviseurFactory(Element elt) {

		Load(elt);
	}

	/**
	 * Sauvegarde un proviseur<br/>
	 * 
	 * @param cla
	 */
	public void save(Proviseur pro) {

		EntityTransaction t = getEm().getTransaction();
		try {

			t.begin();
			getEm().persist(pro);
			t.commit();

		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}

	/**
	 * Remove un proviseur<br/>
	 * 
	 * @param elv
	 */
	public void remove(Proviseur pro) {
		EntityTransaction t = getEm().getTransaction();
		try {

			t.begin();
			getEm().remove(pro);
			t.commit();

		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}

	public void Load(Element elt) {

		try {

			Proviseur pro = new Proviseur();
			String nom = null;
			String prenom = null;
			String denomination = null;

			String exp1 = "NOM_RESP";
			String str1 = elt.getElementsByTagName(exp1).item(0).getTextContent();
			String[] str1Tab1 = str1.split(" ");
			prenom = str1Tab1[0];
			nom = str1Tab1[1];
			pro.setNom(nom);
			pro.setPrenom(prenom);

			String exp2 = "DENOM_PRINC";
			denomination = elt.getElementsByTagName(exp2).item(0).getTextContent();

			new ProviseurFactory(Connect.getEmf().createEntityManager()).save(pro);
			new EtablissementFactory(Connect.getEmf().createEntityManager()).AffectProviseur(denomination, pro);

			pro = null;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
