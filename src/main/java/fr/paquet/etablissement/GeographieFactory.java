package fr.paquet.etablissement;

import java.text.ParseException;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.ibm.icu.text.SimpleDateFormat;

import fr.paquet.dataBase.Connect;

import fr.paquet.referentiel.ProgressFactory;

import org.w3c.dom.Element;

public class GeographieFactory extends ProgressFactory {

	public GeographieFactory(EntityManager em) {
		super();
		setEm(em);
	}
	
	public GeographieFactory(Element elt){
		Load(elt);
	}

	/**
	 * Sauvegarde une geographie<br/>
	 * 
	 * @param comp
	 */
	public void save(Geographie geo) {

		EntityTransaction t = getEm().getTransaction();
		try {

			t.begin();
			getEm().persist(geo);
			t.commit();

		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}
	
	public Geographie find(long id){
		
		Query query = getEm().createQuery("SELECT geo FROM Geographie geo where geo.id_Code_Pays=:paramId");
		query.setParameter("paramId", id);
		return (Geographie) query.getSingleResult();
		
	}

	/**
	 * Remove une gegraphie<br/>
	 * 
	 * @param geo
	 */
	public void remove(Geographie geo) {
		EntityTransaction t = getEm().getTransaction();
		try {

			t.begin();
			getEm().remove(geo);
			t.commit();

		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}

	/**
	 * Rempli une table de la DB a partir d'un .xml<br/>
	 */

	public void Load(Element elt) {

		Geographie geo = new Geographie();
		int id_Code_Pays = 0;
		String libelleCourt = null;
		String libelleLong = null;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date dateOuverture = null;
		Date dateFermeture = null;

		id_Code_Pays = Integer.parseInt(elt.getAttribute("CODE_PAYS"));
		geo.setId_Code_Pays(id_Code_Pays);

		String exp1 = "LIBELLE_COURT";
		libelleCourt = elt.getElementsByTagName(exp1).item(0).getTextContent();
		geo.setLibelle_Court(libelleCourt);

		String exp2 = "LIBELLE_LONG";
		libelleLong = elt.getElementsByTagName(exp2).item(0).getTextContent();
		geo.setLibelle_Long(libelleLong);

		try {
			String exp4 = "DATE_OUVERTURE";
			String str4 = elt.getElementsByTagName(exp4).item(0).getTextContent();
			dateOuverture = formatter.parse(str4);
			geo.setOuverture(dateOuverture);

			String exp5 = "DATE_FERMETURE";
			String str5 = elt.getElementsByTagName(exp5).item(0).getTextContent();
			dateFermeture = formatter.parse(str5);

			geo.setFermeture(dateFermeture);

			new GeographieFactory(Connect.getEmf().createEntityManager()).save(geo);

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
