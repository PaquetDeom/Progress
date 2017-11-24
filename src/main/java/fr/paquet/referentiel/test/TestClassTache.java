package fr.paquet.referentiel.test;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;

import fr.paquet.commun.Diplome;
import fr.paquet.commun.DiplomeFactory;
import fr.paquet.dataBase.Connect;
import fr.paquet.referentiel.Activite;
import fr.paquet.referentiel.Capacite;
import fr.paquet.referentiel.Competence;
import fr.paquet.referentiel.Fonction;
import fr.paquet.referentiel.Rap;
import fr.paquet.referentiel.Referentiel;
import fr.paquet.referentiel.ReferentielFactory;
import fr.paquet.referentiel.Tache;
import fr.paquet.referentiel.Unite;

public class TestClassTache {

	@Test

	public void testgetDb() {
		try {

			Diplome dip = new DiplomeFactory().findDiplome("BAC PRO",
					"TECHNICIEN CONSTRUCTEUR BOIS");

			Referentiel ref = new ReferentielFactory().findReferentiel(dip);

			Activite act = ref.getRap().getFonctions().get(0).getActivites().get(0);

			assertTrue(act.getTaches().size() == 5);

			for (int i = 0; i < act.getTaches().size(); i++) {
				Tache tac = act.getTaches().get(i);
				switch (i) {
				case 0:
					assertTrue(tac.getCode().equals("T1"));
					break;
				case 1:
					assertTrue(tac.getCode().equals("T2"));
					break;
				case 2:
					assertTrue(tac.getCode().equals("T3"));
					break;
				case 3:
					assertTrue(tac.getCode().equals("T4"));
					break;
				case 4:
					assertTrue(tac.getCode().equals("T5"));
					break;

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testgetActivite() {
		try {

			Activite a1 = new Activite(
					new Fonction(new Rap(new Referentiel(new Diplome("BAC PRO", "CB"))), "REALISATION"),
					"      PREParation : Controle et reCEPTIon d'un support ");
			Tache t1 = new Tache(a1, "T1", "R�ceptionner et analyser les donn�es techniques", 2, "C");

			assertEquals(t1.getActivite().getIntitule(), "PREPARATION : controle et reception d'un support");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testgetCode() {
		try {

			Tache t1 = new Tache(
					new Activite(new Fonction(new Rap(new Referentiel(new Diplome("BAC PRO", "CB"))), "REALISATION"),
							"PREPARATION : Controle et reception d'un support"),
					"     t1", "R�ceptionner et analyser les donn�es techniques", 2, "C");

			assertEquals(t1.getCode(), "T1");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testgetIntitule() {
		try {

			Tache t1 = new Tache(
					new Activite(new Fonction(new Rap(new Referentiel(new Diplome("BAC PRO", "CB"))), "REALISATION"),
							"PREPARATION : Controle et reception d'un support"),
					"T1", "             R�ceptionner et analyser les donn�es techniques", 2, "C");

			assertEquals(t1.getIntitule(), "- R�ceptionner et analyser les donn�es techniques");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testgetNiveau() {
		try {

			Activite a1 = new Activite(
					new Fonction(new Rap(new Referentiel(new Diplome("BAC PRO", "CB"))), "REALISATION"),
					"      PREParation : Controle         et reCEPTIon d'un support ");
			Tache t1 = new Tache(a1, "T1", "R�ceptionner et analyser les donn�es techniques", 2, "C");
			Tache t2 = new Tache(a1, "T2", "Rechercher les donn�es manquantes", 2, "C");
			Tache t3 = new Tache(a1, "T3", "Controler et r�ceptionner les supports", 2, "C");
			Tache t5 = new Tache(a1, "T5", "V�rifier les conditions de mise en oeuvre du chantier", 1, "BC");

			assertEquals(t1.getNiveau(), 2);
			assertEquals(t2.getNiveau(), 2);
			assertEquals(t3.getNiveau(), 2);
			assertEquals(t5.getNiveau(), 1);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testgetTypeResponsabilite() {
		try {

			Activite a1 = new Activite(
					new Fonction(new Rap(new Referentiel(new Diplome("BAC PRO", "CB"))), "REALISATION"),
					"      PREParation : Controle         et reCEPTIon d'un support ");
			Tache t1 = new Tache(a1, "T1", "R�ceptionner et analyser les donn�es techniques", 2, " C");
			Tache t2 = new Tache(a1, "T2", "Rechercher les donn�es manquantes", 2, " a");
			Tache t3 = new Tache(a1, "T3", "Controler et r�ceptionner les supports", 2, "ABC  ");
			Tache t5 = new Tache(a1, "T5", "V�rifier les conditions de mise en oeuvre du chantier", 1, " BC");

			assertTrue(t1.getTypeResponsabilite().equals("C"));
			assertTrue(t2.getTypeResponsabilite().equals("A"));
			assertTrue(t3.getTypeResponsabilite().equals("ABC"));
			assertTrue(t5.getTypeResponsabilite().equals("BC"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testgetCompetences() {
		try {

			Referentiel ref = new Referentiel(new Diplome("BAC PRO", "CB"));

			Tache T1 = new Tache(
					new Activite(new Fonction(new Rap(new Referentiel(new Diplome("BAC PRO", "CB"))), "REALISATION"),
							"FABRICATION : finition et traitement"),
					"T1", "String1", 1, "ABC");

			Competence c1_1 = new Competence(new Capacite(ref, "C1", "S'INFORMER ANALYSER"), new Unite(11, "ANALYSE"),
					1, "D�coder et analyser les donn�es de d�finition");
			Competence c2_1 = new Competence(new Capacite(ref, "C2", "TRAITER DECIDER PREPARER"),
					new Unite(11, "ANALYSE"), 1, "Choisir, adapter et justifier des solutions techniques");

			T1.addCompetence(c1_1);
			T1.addCompetence(c2_1);

			for (int i = 0; i < T1.getCompetences().size(); i++) {
				List<Competence> l = T1.getCompetences();
				Competence comp = l.get(i);
				switch (i) {
				case 0:
					assertEquals(comp.getCode(), "C1.1");
					break;
				case 1:
					assertEquals(comp.getCode(), "C2.1");
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
