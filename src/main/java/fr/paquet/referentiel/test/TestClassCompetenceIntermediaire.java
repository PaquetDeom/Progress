package fr.paquet.referentiel.test;

import static org.junit.Assert.*;
import org.junit.Test;

import fr.paquet.commun.Diplome;
import fr.paquet.commun.DiplomeFactory;
import fr.paquet.dataBase.Connect;
import fr.paquet.referentiel.Capacite;
import fr.paquet.referentiel.Competence;
import fr.paquet.referentiel.CompetenceIntermediaire;
import fr.paquet.referentiel.Condition;
import fr.paquet.referentiel.CritereEvaluation;
import fr.paquet.referentiel.Referentiel;
import fr.paquet.referentiel.ReferentielFactory;
import fr.paquet.referentiel.Unite;

public class TestClassCompetenceIntermediaire {

	@Test
	public void testgetDb() {
		try {

			Diplome dip = new DiplomeFactory().findDiplome("BAC PRO",
					"TECHNICIEN CONSTRUCTEUR BOIS");

			Referentiel ref = new ReferentielFactory().findReferentiel(dip);

			Competence comp = ref.getCapacites().get(0).getCompetences().get(0);

			assertTrue(comp.getCompetencesIntermediaires().size() == 7);

			for (int i = 0; i < comp.getCompetencesIntermediaires().size(); i++) {
				CompetenceIntermediaire compInt = comp.getCompetencesIntermediaires().get(i);
				switch (i) {
				case 0:
					assertTrue(compInt.getCode().equals("C1.1.1"));
					break;
				case 1:
					assertTrue(compInt.getCode().equals("C1.1.2"));
					break;
				case 2:
					assertTrue(compInt.getCode().equals("C1.1.3"));
					break;
				case 3:
					assertTrue(compInt.getCode().equals("C1.1.4"));
					break;
				case 4:
					assertTrue(compInt.getCode().equals("C1.1.5"));
					break;
				case 5:
					assertTrue(compInt.getCode().equals("C1.1.6"));
					break;
				case 6:
					assertTrue(compInt.getCode().equals("C1.1.7"));
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testgetCode() {

		try {
			Referentiel ref = new Referentiel(new Diplome("BAC PRO", "CB"));

			CompetenceIntermediaire compInt = new CompetenceIntermediaire(
					new Competence(new Capacite(ref, "C2", "INFORMER"), new Unite(11, "ANALYSE"), 5, "Etablir les Doc"),
					1, "string 1");
			CompetenceIntermediaire compInt1 = new CompetenceIntermediaire(
					new Competence(new Capacite(ref, "C3", "INFORMER"), new Unite(11, "ANALYSE"), 4, "Etablir les Doc"),
					3, "string 2");

			assertTrue(compInt.getCode().equals("C2.5.1"));
			assertTrue(compInt1.getCode().equals("C3.4.3"));

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Test
	public void testgetCompetence() {
		try {

			Referentiel ref = new Referentiel(new Diplome("BAC PRO", "CB"));

			CompetenceIntermediaire compInt = new CompetenceIntermediaire(
					new Competence(new Capacite(ref, "C3", "INFORMER"), new Unite(11, "ANALYSE"), 4, "Etablir les Doc"),
					3, "string 2");

			assertEquals(compInt.getCompetence().getCode(), "C3.4");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Test
	public void testgetCondition() {

		try {
			Referentiel ref = new Referentiel(new Diplome("BAC PRO", "CB"));
			Competence c = new Competence(new Capacite(ref, "C6", "Animer Communiquer"), new Unite(11, "ANALYSE"), 2,
					"Animer les actions qualit� et s�curit�");

			CompetenceIntermediaire c623 = new CompetenceIntermediaire(c, 3, "Participer � l'analyse");
			CompetenceIntermediaire c624 = new CompetenceIntermediaire(c, 4, "Faire Adh�rer");

			Condition cond1 = new Condition("Plan de levage");
			Condition cond2 = new Condition("Planning");
			Condition cond3 = new Condition("Moyen de Protection individuel");
			Condition cond4 = new Condition("fabrication Sur Chantier");

			c.addCondition(cond1);
			c.addCondition(cond2);
			c623.addCondition(cond3);
			c623.addCondition(cond4);

			assertEquals(c623.getConditions().get(0).getCondition(), "Moyen de Protection individuel");
			assertEquals(c624.getConditions().get(0).getCondition(), "Plan de levage");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testgetCritereEvaluation() {

		try {
			Referentiel ref = new Referentiel(new Diplome("BAC PRO", "CB"));
			Competence c = new Competence(new Capacite(ref, "C6", "Animer Communiquer"), new Unite(11, "ANALYSE"), 2,
					"Animer les actions qualite et securite");

			CompetenceIntermediaire c623 = new CompetenceIntermediaire(c, 3, "Participer a l'analyse");
			CompetenceIntermediaire c624 = new CompetenceIntermediaire(c, 4, "Faire Adherer");

			CritereEvaluation crit1 = new CritereEvaluation("La mise en oeuvre est juste");
			CritereEvaluation crit2 = new CritereEvaluation("La Participation est juste");
			CritereEvaluation crit3 = new CritereEvaluation("Tout est juste");
			CritereEvaluation crit4 = new CritereEvaluation("Pierre attend son mp3");

			c.addCritereEvaluation(crit1);
			c.addCritereEvaluation(crit2);

			c623.addCritereEvaluation(crit3);
			c623.addCritereEvaluation(crit4);

			assertEquals(c624.getCritereEvaluations().get(0).getCritere(), "La mise en oeuvre est juste");
			assertEquals(c623.getCritereEvaluations().get(1).getCritere(), "Pierre attend son mp3");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testgetIntitule() {

		try {
			Referentiel ref = new Referentiel(new Diplome("BAC PRO", "CB"));

			CompetenceIntermediaire c2_52 = new CompetenceIntermediaire(
					new Competence(new Capacite(ref, "C2", "INFORMER"), new Unite(11, "ANALYSE"), 5, "Etablir les Doc"),
					2, "IdentiFIer les points de controles");
			CompetenceIntermediaire c2_53 = new CompetenceIntermediaire(
					new Competence(new Capacite(ref, "C2", "INFORMER"), new Unite(11, "ANALYSE"), 5, "Etablir les Doc"),
					2, "   eVALUER les points de controles");
			CompetenceIntermediaire c2_54 = new CompetenceIntermediaire(
					new Competence(new Capacite(ref, "C2", "INFORMER"), new Unite(11, "ANALYSE"), 5, "Etablir les Doc"),
					2, "Choisir les points de controles");

			assertTrue(c2_52.getIntitule().equals("IDENTIFIER les points de controles"));
			assertTrue(c2_53.getIntitule().equals("EVALUER les points de controles"));
			assertTrue(c2_54.getIntitule().equals("CHOISIR les points de controles"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testgetVerbe() {

		try {
			Referentiel ref = new Referentiel(new Diplome("BAC PRO", "CB"));

			CompetenceIntermediaire c2_51 = new CompetenceIntermediaire(
					new Competence(new Capacite(ref, "C2", "INFORMER"), new Unite(11, "ANALYSE"), 5, "Etablir les Doc"),
					1, "   IdentiFIer les points de controles");

			assertTrue(c2_51.getVerbe().equals("IDENTIFIER"));

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
