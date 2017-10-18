package fr.paquet.etablissement;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.w3c.dom.Element;

import fr.paquet.dataBase.Connect;
import fr.paquet.referentiel.ProgressFactory;

public class ProfesseurFactory extends ProgressFactory {

	public ProfesseurFactory(EntityManager em) {
		super();
		setEm(em);
	}

	public ProfesseurFactory(Element elt) {

		Load(elt);
	}

	/**
	 * Sauvegarde un professeur<br/>
	 * 
	 * @param cla
	 */
	public void save(Professeur prof) {

		EntityTransaction t = getEm().getTransaction();
		try {

			t.begin();
			getEm().persist(prof);
			t.commit();

		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}

	/**
	 * Remove un professeur<br/>
	 * 
	 * @param elv
	 */
	public void remove(Professeur prof) {
		EntityTransaction t = getEm().getTransaction();
		try {

			t.begin();
			getEm().remove(prof);
			t.commit();

		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}

	public void Load(Element elt) {

		try {

			Professeur prof = new Professeur();

			String exp1 = "NOM";
			String str1 = elt.getElementsByTagName(exp1).item(0).getTextContent();
			prof.setNom(str1);

			String exp2 = "PRENOM";
			String str2 = elt.getElementsByTagName(exp2).item(0).getTextContent();
			prof.setPrenom(str2);

			String exp3 = "DISCIPLINE";
			String str3 = elt.getElementsByTagName(exp3).item(0).getTextContent();
			prof.setDiscipline(str3);

			String exp4 = "DISC_RECRU";
			String str4 = elt.getElementsByTagName(exp4).item(0).getTextContent();
			prof.setRecrutement(str4);

			String exp7 = "CIVILITE";
			String str7 = elt.getElementsByTagName(exp7).item(0).getTextContent();
			if (str7.equals("M."))
				prof.setSexe(true);
			else
				prof.setSexe(false);

			String exp8 = "CLASSES";
			String str8 = elt.getElementsByTagName(exp8).item(0).getTextContent();
			Classe cla = new ClasseFactory(Connect.getEmf().createEntityManager()).findClasseByFormation(str8);
			prof.setClassePrincipale(cla);

			new ProfesseurFactory(Connect.getEmf().createEntityManager()).save(prof);

			prof = null;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
