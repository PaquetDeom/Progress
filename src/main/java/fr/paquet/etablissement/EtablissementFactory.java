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
	public Etablissement find(long id) {

		Query query = getEm().createQuery("SELECT etab FROM Etablissement etab where etab.id=:id");
		query.setParameter("id", id);

		try {

			return (Etablissement) query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}

	}

	public void Load(Element elt) {

		try {
			Etablissement etab = new Etablissement();
			String codeRNE = null;
			String sigle = null;
			String denominationPrincipale = null;
			String denominationComplementaire = null;
			SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
			Date dateOuverture = null;
			Date dateFermeture = null;

			codeRNE = elt.getAttribute("CODE_RNE");
			etab.setCodeRNE(codeRNE);
			etab.setId(codeRNE);

			String exp1 = "SIGLE";
			sigle = elt.getElementsByTagName(exp1).item(0).getTextContent();
			etab.setSigle(sigle);

			String exp2 = "DENOM_PRINC";
			denominationPrincipale = elt.getElementsByTagName(exp2).item(0).getTextContent();
			etab.setDenominationPrincipale(denominationPrincipale);

			String exp3 = "DENOM_COMPL";
			denominationComplementaire = elt.getElementsByTagName(exp3).item(0).getTextContent();
			etab.setDenominationComplementaire(denominationComplementaire);

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
