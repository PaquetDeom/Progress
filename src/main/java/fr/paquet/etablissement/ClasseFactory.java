package fr.paquet.etablissement;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.w3c.dom.Element;

import fr.paquet.dataBase.Connect;
import fr.paquet.referentiel.ProgressFactory;

public class ClasseFactory extends ProgressFactory {

	public ClasseFactory(EntityManager em) {
		super();
		setEm(em);
	}

	public ClasseFactory(Element elt) {

		Load(elt);
	}

	/**
	 * Sauvegarde une classe<br/>
	 * 
	 * @param cla
	 */
	public void save(Classe cla) {

		EntityTransaction t = getEm().getTransaction();
		try {

			t.begin();
			getEm().persist(cla);
			t.commit();

		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}

	/**
	 * Remove une classe<br/>
	 * 
	 * @param elv
	 */
	public void remove(Classe cla) {
		EntityTransaction t = getEm().getTransaction();
		try {

			t.begin();
			getEm().remove(cla);
			t.commit();

		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}

	public void Load(Element elt) {

		try {

			Classe cla = new Classe();
			String code = null;
			String formation = null;
			String intitule = null;

			code = elt.getAttribute("CODE_MEF");
			cla.setCode(code);

			String exp1 = "FORMATION";
			formation = elt.getElementsByTagName(exp1).item(0).getTextContent();
			cla.setFormation(formation);

			String exp2 = "LIBELLE_LONG";
			intitule = elt.getElementsByTagName(exp2).item(0).getTextContent();
			cla.setIntitule(intitule);

			new ClasseFactory(Connect.getEmf().createEntityManager()).save(cla);
			cla = null;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
