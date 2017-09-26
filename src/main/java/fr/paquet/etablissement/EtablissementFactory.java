package fr.paquet.etablissement;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import javax.persistence.NoResultException;

import javax.persistence.Query;

import org.w3c.dom.Element;

import com.ibm.icu.text.SimpleDateFormat;

import fr.paquet.dataBase.Connect;
import fr.paquet.referentiel.ProgressFactory;

public class EtablissementFactory extends ProgressFactory {

	public EtablissementFactory(EntityManager em) {
		super();
		setEm(em);
	}

	public EtablissementFactory(Element elt) {

		Load(elt);
	}

	/**
	 * Sauvegarde un etablissement<br/>
	 * 
	 * @param etab
	 */
	public void save(Etablissement etab) {

		EntityTransaction t = getEm().getTransaction();
		if (find(etab.getCodeRne()) != null) {
			try {
				t.begin();
				getEm().refresh(etab);
				t.commit();
			} catch (Exception e) {
				t.rollback();
				throw (e);
			}
		} else {

			try {

				t.begin();
				getEm().persist(etab);
				t.commit();

			} catch (Exception e) {
				t.rollback();
				throw (e);
			}
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
	public Etablissement find(String codeRNE) {

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

		try {
			
			Etablissement etab = (Etablissement) query.getSingleResult();
			etab.setProviseur(pro);
			new EtablissementFactory(Connect.getEmf().createEntityManager()).save(etab);
			
		} catch (NoResultException e) {
			pro = null;
		}
		

	}

	public void Load(Element elt) {

		try {
			Etablissement etab = new Etablissement();
			String codeRNE = null;
			String sigle = null;
			String denominationPrincipale = null;
			String denominationComplementaire = null;
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
			Date dateOuverture = null;
			Date dateFermeture = null;

			codeRNE = elt.getAttribute("CODE_RNE");
			etab.setCodeRNE(codeRNE);

			String exp1 = "SIGLE";
			sigle = elt.getElementsByTagName(exp1).item(0).getTextContent();
			etab.setSigle(sigle);

			String exp2 = "DENOM_PRINC";
			denominationPrincipale = elt.getElementsByTagName(exp2).item(0).getTextContent();
			etab.setDenominationPrincipale(denominationPrincipale);

			String exp3 = "DENOM_COMPL";
			try {
				denominationComplementaire = elt.getElementsByTagName(exp3).item(0).getTextContent();
				etab.setDenominationComplementaire(denominationComplementaire);
			} catch (NullPointerException e) {
				denominationComplementaire = null;
			}

			String exp4 = "DATE_OUVERTURE";
			String str4 = elt.getElementsByTagName(exp4).item(0).getTextContent();
			dateOuverture = formater.parse(str4);
			etab.setDateOuverture(dateOuverture);

			String exp5 = "DATE_FERMETURE";
			String str5 = elt.getElementsByTagName(exp5).item(0).getTextContent();
			dateFermeture = formater.parse(str5);
			etab.setDateFermeture(dateFermeture);

			new EtablissementFactory(Connect.getEmf().createEntityManager()).save(etab);
			etab = null;

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
