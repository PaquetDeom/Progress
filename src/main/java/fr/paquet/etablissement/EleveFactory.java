package fr.paquet.etablissement;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.w3c.dom.Element;

import com.ibm.icu.text.SimpleDateFormat;

import fr.paquet.dataBase.Connect;
import fr.paquet.referentiel.ProgressFactory;

public class EleveFactory extends ProgressFactory {

	public EleveFactory(EntityManager em) {
		super();
		setEm(em);
	}

	public EleveFactory(Element elt) {

		Load(elt);
	}

	/**
	 * Sauvegarde un eleve<br/>
	 * 
	 * @param elv
	 */
	public void save(Eleve elv) {

		EntityTransaction t = getEm().getTransaction();
		try {

			t.begin();
			getEm().persist(elv);
			t.commit();

		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}

	/**
	 * Remove un eleve<br/>
	 * 
	 * @param elv
	 */
	public void remove(Eleve elv) {
		EntityTransaction t = getEm().getTransaction();
		try {

			t.begin();
			getEm().remove(elv);
			t.commit();

		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}

	public void Load(Element elt) {

		try {
			Eleve elv = new Eleve();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			int id = 0;
			String nomDeFamille = null;
			String prenom = null;
			Date dateNaissance = null;
			Boolean doublement = false;

			Boolean accepteSms = false;

			Date dateSortie = null;
			String codeRegime = null;
			Date dateEntree = null;
			Boolean sexe = false;

			Boolean adhesionTransport = false;

			id = Integer.parseInt(elt.getAttribute("ELEVE_ID"));
			elv.setId(id);

			String exp1 = "NOM_DE_FAMILLE";
			nomDeFamille = elt.getElementsByTagName(exp1).item(0).getTextContent();
			elv.setNom(nomDeFamille);

			// TODO
			String exp2 = "PRENOM";
			prenom = elt.getElementsByTagName(exp2).item(0).getTextContent();
			elv.setPrenom(prenom);

			String exp3 = "DATE_NAISS";
			dateNaissance = formatter.parse(elt.getElementsByTagName(exp3).item(0).getTextContent());
			elv.setDateDeNaissance(dateNaissance);

			String exp4 = "DOUBLEMENT";
			String str4 = elt.getElementsByTagName(exp4).item(0).getTextContent();
			if (str4.equals("0"))
				doublement = false;
			else
				doublement = true;
			elv.setDoublement(doublement);

			String exp5 = "ACCEPTE_SMS";
			String str5 = null;
			if (elt.getElementsByTagName(exp5) != null && elt.getElementsByTagName(exp5).getLength() != 0) {
				str5 = elt.getElementsByTagName(exp5).item(0).getTextContent();
				if (!str5.equals("0"))
					accepteSms = true;
			} else
				accepteSms = false;
			elv.setSms(accepteSms);

			String exp7 = "DATE_ENTREE";
			dateEntree = formatter.parse(elt.getElementsByTagName(exp7).item(0).getTextContent());
			elv.setDateEntree(dateEntree);

			String exp6 = "DATE_SORTIE";
			if (elt.getElementsByTagName(exp6) != null && elt.getElementsByTagName(exp6).getLength() != 0)
				dateSortie = formatter.parse(elt.getElementsByTagName(exp6).item(0).getTextContent());
			else
				dateSortie = formatter.parse("01/01/9999");
			elv.setDateSortie(dateSortie);

			String exp8 = "CODE_REGIME";
			codeRegime = elt.getElementsByTagName(exp8).item(0).getTextContent();
			elv.setCodeRegime(codeRegime);

			String exp9 = "CODE_SEXE";
			String str9 = null;
			if (elt.getElementsByTagName(exp9) != null && elt.getElementsByTagName(exp9).getLength() != 0) {
				str9 = elt.getElementsByTagName(exp9).item(0).getTextContent();
				if (!str9.equals("1"))
					sexe = false;
				else
					sexe = true;
				elv.setSexe(sexe);
			}

			else
				sexe = null;

			String exp10 = "ADHESION_TRANSPORT";
			String str10 = elt.getElementsByTagName(exp10).item(0).getTextContent();
			if (!str10.equals("0"))
				adhesionTransport = true;
			else
				adhesionTransport = false;
			elv.setAdhesionTransport(adhesionTransport);
			
			String exp11 = "ADRESSE_ID";
			String str11 = null;
			if (elt.getElementsByTagName(exp11) != null && elt.getElementsByTagName(exp11).getLength() != 0) {
				str11 = elt.getElementsByTagName(exp11).item(0).getTextContent();
				long id1 = Integer.parseInt(str11);
				elv.setCoordonnee(new CoordonneeFactory(Connect.getEmf().createEntityManager()).find(id1));
			}
			

			new EleveFactory(Connect.getEmf().createEntityManager()).save(elv);

			elv = null;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}