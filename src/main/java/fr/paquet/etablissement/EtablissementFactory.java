package fr.paquet.etablissement;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import javax.persistence.NoResultException;

import javax.persistence.Query;

import org.w3c.dom.Element;

import fr.paquet.dataBase.Connect;

public class EtablissementFactory extends Connect {

	private static EtablissementFactory uniqInstance = null;

	/**
	 * Sauvegarde un etablissement<br/>
	 * 
	 * @param etab
	 */
	public void save(Etablissement etab) {

		EntityTransaction t = getEm().getTransaction();

		try {

			t.begin();
			getEm().persist(etab);
			t.commit();

		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}

	/**
	 * Remove un etablissement<br/>
	 * 
	 * @param etab
	 */
	public void remove(Etablissement etab) {
		EntityTransaction t = getEm().getTransaction();
		try {

			t.begin();
			getEm().remove(etab);
			t.commit();

		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}

	/**
	 * 
	 * @param id
	 * @return un etablissement depuis son id<br/>
	 */
	public Etablissement findEtablissementByRne(String codeRNE) throws Exception {

		Query query = getEm().createQuery("SELECT etab FROM Etablissement etab where etab.codeRNE=:codeRNE");
		query.setParameter("codeRNE", codeRNE);
		return (Etablissement) query.getSingleResult();
	}

	/**
	 * Affectation d'un proviseur a un etablissement<br/>
	 * 
	 * @param denominationPrincipale
	 * @param pro
	 */
	public void AffectProviseur(String denominationPrincipale, Proviseur pro) {

		Query query = getEm().createQuery(
				"SELECT etab FROM Etablissement etab where etab.denominationPrincipale=:denominationPrincipale");
		query.setParameter("denominationPrincipale", denominationPrincipale);

		EntityTransaction t = getEm().getTransaction();

		try {
			Etablissement etabDb = (Etablissement) query.getSingleResult();

			t.begin();
			etabDb.setProviseur(pro);
			t.commit();

		} catch (NoResultException e) {
			e.printStackTrace();
		} catch (Exception e) {
			t.rollback();
			throw (e);
		}

	}

	public void Load(Element elt, Etablissement etab) {

		try {
			save(new Etablissement(elt, etab));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @return l'instance unique de class<br/>
	 */
	public static EtablissementFactory getInstance() {
		if (uniqInstance == null) {
			uniqInstance = new EtablissementFactory();
		}
		return uniqInstance;
	}

	public static Etablissement getEtablissement(String rne) throws Exception {
		return getInstance().findEtablissementByRne(rne);
	}

}
