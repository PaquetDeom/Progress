package fr.paquet.etablissement;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import javax.persistence.NoResultException;

import javax.persistence.Query;

import org.w3c.dom.Element;

import fr.paquet.dataBase.Connect;
import fr.paquet.referentiel.ProgressFactory;

public class EtablissementFactory extends ProgressFactory {

	public EtablissementFactory(EntityManager em) {
		super();
		setEm(em);
	}

	public EtablissementFactory(Element eltParametre, Element eltDonnee) {

		Load(eltParametre, eltDonnee);

	}

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
	public Etablissement findEtablissementByRne(String codeRNE) {

		Query query = getEm().createQuery("SELECT etab FROM Etablissement etab where etab.codeRNE=:codeRNE");
		query.setParameter("codeRNE", codeRNE);

		try {

			return (Etablissement) query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}

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

	public void Load(Element eltParametre, Element eltDonnee) {

		try {

			Etablissement etab = new Etablissement();
			String codeRNE = null;
			String denominationPrincipale = null;
			String denominationComplementaire = null;

			String exp0 = "UAJ";
			codeRNE = eltParametre.getElementsByTagName(exp0).item(0).getTextContent();
			etab.setCodeRNE(codeRNE);

			String exp1 = "DENOM_PRINC";
			denominationPrincipale = eltDonnee.getElementsByTagName(exp1).item(0).getTextContent();
			etab.setDenominationPrincipale(denominationPrincipale);

			String exp2 = "DENOM_COMPL";
			try {
				denominationComplementaire = eltDonnee.getElementsByTagName(exp2).item(0).getTextContent();
				etab.setDenominationComplementaire(denominationComplementaire);
			} catch (NullPointerException e) {
				denominationComplementaire = null;
			}

			new EtablissementFactory(Connect.getEmf().createEntityManager()).save(etab);
			etab = null;

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
