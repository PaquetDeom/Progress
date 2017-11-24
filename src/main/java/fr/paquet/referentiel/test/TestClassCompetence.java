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
import fr.paquet.referentiel.CompetenceIntermediaire;
import fr.paquet.referentiel.Condition;
import fr.paquet.referentiel.CritereEvaluation;
import fr.paquet.referentiel.Fonction;
import fr.paquet.referentiel.Rap;
import fr.paquet.referentiel.Referentiel;
import fr.paquet.referentiel.ReferentielFactory;
import fr.paquet.referentiel.Savoir;
import fr.paquet.referentiel.Tache;
import fr.paquet.referentiel.Unite;

public class TestClassCompetence {

	@Test
	public void testgetCode() {

		try {

			Referentiel ref = new Referentiel(new Diplome("BAC PRO", "CB"));
			Capacite cap = new Capacite(ref, "C2", "INFORMER");
			Competence comp = new Competence(cap, new Unite(11, "ANALYSE"), 5, "Etablir les Doc");
			Competence comp1 = new Competence(cap, new Unite(11, "ANALYSE"), -7, "Rechercher les docs");
			assertTrue(comp.getCode().equals("C2.5"));
			assertTrue(comp1.getCode().equals("C2.7"));

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@Test
	public void testDB() {

		try {

			Diplome dip = new DiplomeFactory().findDiplome("BAC PRO",
					"TECHNICIEN CONSTRUCTEUR BOIS");

			Referentiel ref = new ReferentielFactory().findReferentiel(dip);

			Capacite cap = ref.getCapacites().get(0);

			assertTrue(cap.getCompetences().size() == 4);

			for (int i = 0; i < cap.getCompetences().size(); i++) {
				Competence comp = cap.getCompetences().get(i);
				switch (i) {
				case 0:
					assertTrue(comp.getCode().equals("C1.1"));
					break;
				case 1:
					assertTrue(comp.getCode().equals("C1.2"));
					break;
				case 2:
					assertTrue(comp.getCode().equals("C1.3"));
					break;
				case 3:
					assertTrue(comp.getCode().equals("C1.4"));
					break;
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@Test
	public void testgetIntitule() {
		try {

			Referentiel ref = new Referentiel(new Diplome("BAC PRO", "CB"));
			Capacite cap = new Capacite(ref, "C2", "INFORMER");
			Competence comp = new Competence(cap, new Unite(11, "ANALYSE"), 5, "       Etablir les Doc");
			assertTrue(comp.getIntitule().equals("Etablir les Doc"));

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Test
	public void testgetCapacite() {
		try {

			Referentiel ref = new Referentiel(new Diplome("BAC PRO", "CB"));
			Capacite cap = new Capacite(ref, "   C2", "INFORMER");
			Competence comp = new Competence(cap, new Unite(11, "ANALYSE"), 5, "       Etablir les Doc");
			assertEquals(comp.getCapacite().getCode(), "C2");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Test
	public void testgetCompetencesIntermediaires() {
		try {

			Referentiel ref = new Referentiel(new Diplome("BAC PRO", "CB"));
			Capacite cap = new Capacite(ref, "C2", "INFORMER");
			Competence comp = new Competence(cap, new Unite(11, "ANALYSE"), 5, "       Etablir les Doc");
			Competence comp1 = new Competence(cap, new Unite(11, "ANALYSE"), 6, "Truc");
			new CompetenceIntermediaire(comp, 1, "Prendre des saucisses");
			new CompetenceIntermediaire(comp, 2, "Cuire des saucisses");
			new CompetenceIntermediaire(comp, 3, "Faire la sauce des saucisses");
			new CompetenceIntermediaire(comp, 4, "Manger des saucisses");
			new CompetenceIntermediaire(comp1, 4, "aaa");
			assertEquals(comp.getCompetencesIntermediaires().size(), 4);

			for (int i = 0; i < comp.getCompetencesIntermediaires().size(); i++) {
				List<CompetenceIntermediaire> l = comp.getCompetencesIntermediaires();
				CompetenceIntermediaire compInt = l.get(i);
				switch (i) {
				case 0:
					assertEquals(compInt.getCode(), "C2.5.1");
					break;
				case 1:
					assertEquals(compInt.getCode(), "C2.5.2");
					break;
				case 2:
					assertEquals(compInt.getCode(), "C2.5.3");
					break;
				case 3:
					assertEquals(compInt.getCode(), "C2.5.4");
					break;
				}

			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Test
	public void testgetConditions() {
		try {

			Referentiel ref = new Referentiel(new Diplome("BAC PRO", "CB"));
			Competence c = new Competence(new Capacite(ref, "C6", "Animer Communiquer"), new Unite(11, "ANALYSE"), 2,
					"Animer les actions qualit� et s�curit�");
			c.addCompetenceIntermediaire(1, "Mettre en oeuvre", 1);
			c.addCompetenceIntermediaire(2, "Suivre et controler les procedures", 1);
			c.addCompetenceIntermediaire(3, "Participer � l'analyse", 1);
			c.addCompetenceIntermediaire(4, "Faire Adh�rer", 1);

			Condition cond1 = new Condition("Plan de levage");
			Condition cond2 = new Condition("Planning");

			c.addCondition(cond1);
			c.addCondition(cond2);

			assertEquals(c.getConditions().get(0).getCondition(), "Plan de levage");
			assertEquals(c.getConditions().get(1).getCondition(), "Planning");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testgetCritereEvaluation() {
		try {

			Referentiel ref = new Referentiel(new Diplome("BAC PRO", "CB"));
			Competence c = new Competence(new Capacite(ref, "C6", "Animer Communiquer"), new Unite(11, "ANALYSE"), 2,
					"Animer les actions qualit� et s�curit�");
			c.addCompetenceIntermediaire(1, "Mettre en oeuvre", 1);
			c.addCompetenceIntermediaire(2, "Suivre et controler les procedures", 1);
			c.addCompetenceIntermediaire(3, "Participer � l'analyse", 1);
			c.addCompetenceIntermediaire(4, "Faire Adh�rer", 1);

			CritereEvaluation crit1 = new CritereEvaluation("La mise en oeuvre est juste");
			CritereEvaluation crit2 = new CritereEvaluation("La Participation est juste");

			c.addCritereEvaluation(crit1);
			c.addCritereEvaluation(crit2);

			assertEquals(c.getCritereEvaluations().get(0).getCritere(), "La mise en oeuvre est juste");
			assertEquals(c.getCritereEvaluations().get(1).getCritere(), "La Participation est juste");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testgetSavoirs() {

		try {

			Referentiel ref = new Referentiel(new Diplome("BAC PRO", "CB"));

			Savoir s1 = new Savoir(ref, "S1", "L'entreprise et son environement");
			Savoir s2 = new Savoir(ref, "S2", "La communication technique");
			Savoir s3 = new Savoir(ref, "S3", "Le confort de l'habitat");
			Savoir s4 = new Savoir(ref, "S4", "La m�canique et la r�sistance des mat�riaux");
			Savoir s5 = new Savoir(ref, "S5", "Les ouvrages");
			Savoir s6 = new Savoir(ref, "S6", "Les mat�riaux, les produits et les composants");
			Savoir s7 = new Savoir(ref, "S7", "Les moyens techniques de fabrication et de mise en oeuvre sur chantier");
			Savoir s8 = new Savoir(ref, "S8", "La sant� et la s�curit� au travail");
			Savoir s9 = new Savoir(ref, "S9", "L'organisation et la gestion de fabrication et de chantier");

			Competence c1_1 = new Competence(new Capacite(ref, "C1", "S'INFORMER ANALYSER"), new Unite(11, "ANALYSE"),
					1, "D�coder et analyser les donn�es de d�finition");
			Competence c2_1 = new Competence(new Capacite(ref, "C2", "TRAITER DECIDER PREPARER"),
					new Unite(11, "ANALYSE"), 1, "Choisir, adapter et justifier des solutions techniques");
			Competence c3_1 = new Competence(new Capacite(ref, "C3", "FABRIQUER"), new Unite(11, "ANALYSE"), 1,
					"Organiser et mettre en s�curit� les postes de travail");
			Competence c4_1 = new Competence(new Capacite(ref, "C4", "METTRE EN OEUVRE SUR CHANTIER"),
					new Unite(11, "ANALYSE"), 1, "Organiser et mettre en s�curit� la zone d'intervention");
			Competence c5_1 = new Competence(new Capacite(ref, "C5", "MAINTENIR ET REMETTRE EN ETAT"),
					new Unite(11, "ANALYSE"), 1, "Assurer la maintenance de premier niveau des machine");

			c1_1.addSavoir(s1);
			c1_1.addSavoir(s2);
			c1_1.addSavoir(s3);

			c2_1.addSavoir(s1);
			c2_1.addSavoir(s2);
			c2_1.addSavoir(s3);
			c2_1.addSavoir(s4);

			c3_1.addSavoir(s5);

			c4_1.addSavoir(s6);
			c4_1.addSavoir(s7);

			c5_1.addSavoir(s8);
			c5_1.addSavoir(s9);

			assertEquals(c1_1.getSavoirs().size(), 3);
			assertEquals(c2_1.getSavoirs().size(), 4);
			assertEquals(c3_1.getSavoirs().size(), 1);
			assertEquals(c4_1.getSavoirs().size(), 2);
			assertEquals(c5_1.getSavoirs().size(), 2);

			for (int i = 0; i < c2_1.getSavoirs().size(); i++) {
				List<Savoir> l = c2_1.getSavoirs();
				Savoir sav = l.get(i);
				switch (i) {
				case 0:
					assertEquals(sav.getCode(), "S1");
					break;
				case 1:
					assertEquals(sav.getCode(), "S2");
					break;
				case 2:
					assertEquals(sav.getCode(), "S3");
					break;
				case 3:
					assertEquals(sav.getCode(), "S4");
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testgetTaches() {
		try {
			Referentiel ref = new Referentiel(new Diplome("BAC PRO", "CB"));

			Tache T1 = new Tache(
					new Activite(new Fonction(new Rap(ref), "REALISATION"), "FABRICATION : finition et traitement"),
					"T1", "String1", 1, "ABC");
			Tache T2 = new Tache(
					new Activite(new Fonction(new Rap(ref), "REALISATION"), "FABRICATION : finition et traitement"),
					"T2", "String2", 1, "AC");
			Tache T3 = new Tache(
					new Activite(new Fonction(new Rap(ref), "REALISATION"), "FABRICATION : finition et traitement"),
					"T3", "String3", 1, "BC");
			Tache T4 = new Tache(
					new Activite(new Fonction(new Rap(ref), "REALISATION"), "FABRICATION : finition et traitement"),
					"T4", "String4", 1, "C");

			Competence c1_1 = new Competence(new Capacite(ref, "C1", "S'INFORMER ANALYSER"), new Unite(11, "ANALYSE"),
					1, "D�coder et analyser les donn�es de d�finition");
			Competence c2_1 = new Competence(new Capacite(ref, "C2", "TRAITER DECIDER PREPARER"),
					new Unite(11, "ANALYSE"), 1, "Choisir, adapter et justifier des solutions techniques");

			c1_1.addTache(T1);
			c1_1.addTache(T2);
			c1_1.addTache(T3);

			c2_1.addTache(T3);
			c2_1.addTache(T4);

			assertEquals(c1_1.getTaches().size(), 3);
			assertEquals(c2_1.getTaches().size(), 2);

			for (int i = 0; i < c1_1.getTaches().size(); i++) {
				List<Tache> l = c1_1.getTaches();
				Tache t = l.get(i);
				switch (i) {
				case 0:
					assertEquals(t.getIntitule(), "- String1");
					break;
				case 1:
					assertEquals(t.getIntitule(), "- String2");
					break;
				case 2:
					assertEquals(t.getIntitule(), "- String3");
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testgetUnite() {
		try {

			Referentiel ref = new Referentiel(new Diplome("BAC PRO", "CB"));

			Unite u_11 = new Unite(11, "ANALYSE TECHNIQUE D'UN OUVRAGE");
			Unite u_2 = new Unite(2, "PREPARATION D'UNE FABRICATION ET D'UNE MISE EN OEUVRE SUR CHANTIER");

			Competence c1_1 = new Competence(new Capacite(ref, "C1", "S'INFORMER ANALYSER"), u_11, 1,
					"D�coder et analyser des donn�es de d�finition");
			Competence c1_2 = new Competence(new Capacite(ref, "C1", "S'INFORMER ANALYSER"), u_11, 2,
					"D�coder et analyser des donn�es op�ratoires");
			Competence c1_3 = new Competence(new Capacite(ref, "C1", "S'INFORMER ANALYSER"), u_2, 3,
					"D�coder et analyser des donn�es de gestion");
			Competence c1_4 = new Competence(new Capacite(ref, "C1", "S'INFORMER ANALYSER"), u_2, 4,
					"Relever et r�ceptionner une situation de chantier");

			assertTrue(c1_1.getUnite() == u_11);
			assertTrue(c1_2.getUnite() == u_11);
			assertTrue(c1_3.getUnite() == u_2);
			assertTrue(c1_4.getUnite() == u_2);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
