package fr.paquet.etablissement;

import java.text.ParseException;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.ibm.icu.text.SimpleDateFormat;

import fr.paquet.dataBase.Connect;


import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class GeographieFactory extends Connect {

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

	public Geographie find(long id) {

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
		try {
			save(new Geographie(elt));

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	private static GeographieFactory uniqInstance = null;

	public static GeographieFactory getInstance() {
		if (uniqInstance == null) {
			uniqInstance = new GeographieFactory();
		}
		return uniqInstance;
	}

}
