package fr.paquet.referentiel.test;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;

import fr.paquet.commun.Diplome;
import fr.paquet.commun.DiplomeFactory;
import fr.paquet.dataBase.Connect;
import fr.paquet.referentiel.Capacite;
import fr.paquet.referentiel.Competence;
import fr.paquet.referentiel.Referentiel;
import fr.paquet.referentiel.ReferentielFactory;
import fr.paquet.referentiel.Savoir;
import fr.paquet.referentiel.SavoirAssocie;
import fr.paquet.referentiel.Unite;

public class TestClassSavoir {

	@Test
	public void testgetdB() {
		try {

			Diplome dip = new DiplomeFactory().findDiplome("BAC PRO",
					"TECHNICIEN CONSTRUCTEUR BOIS");

			Referentiel ref = new ReferentielFactory().findReferentiel(dip);

			Competence comp = ref.getCapacites().get(0).getCompetences().get(0);

			assertTrue(comp.getSavoirs().get(0).getCode().equals("S1"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testgetCode() {
		try {
			Referentiel ref = new Referentiel(new Diplome("BAC PRO", "CB"));

			Savoir sav = new Savoir(ref, "  s2", "    les intervenants");
			assertTrue(sav.getCode().equals("S2"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testgetIntitule() {
		try {
			Referentiel ref = new Referentiel(new Diplome("BAC PRO", "CB"));

			Savoir sav = new Savoir(ref, "S2", "    les intervenants");
			assertTrue(sav.getIntitule().equals("LES INTERVENANTS"));

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Test
	public void testgetSavoirAssocie() {
		try {
			Referentiel ref = new Referentiel(new Diplome("BAC PRO", "CB"));

			Savoir sav1 = new Savoir(ref, "S3", "    les documents techniques");

			new SavoirAssocie(sav1, 1, "Savoir Associe 1");
			new SavoirAssocie(sav1, 2, "Savoir Associe 2");
			new SavoirAssocie(sav1, 3, "Savoir Associe 3");
			new SavoirAssocie(sav1, 4, "Savoir Associe 4");
			new SavoirAssocie(sav1, 5, "Savoir Associe 5");
			new SavoirAssocie(sav1, 6, "Savoir Associe 6");

			assertEquals(sav1.getSavoirsAssocies().size(), 6);

			for (int i = 0; i < sav1.getSavoirsAssocies().size(); i++) {
				List<SavoirAssocie> l = sav1.getSavoirsAssocies();
				SavoirAssocie savAss = l.get(i);
				switch (i) {
				case 0:
					assertEquals(savAss.getCode(), "S3.1");
					break;
				case 1:
					assertEquals(savAss.getCode(), "S3.2");
					break;
				case 2:
					assertEquals(savAss.getCode(), "S3.3");
					break;
				case 3:
					assertEquals(savAss.getCode(), "S3.4");
					break;
				case 4:
					assertEquals(savAss.getCode(), "S3.5");
					break;
				case 5:
					assertEquals(savAss.getCode(), "S3.6");
					break;
				}

			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Test
	public void testgetCompetences() {

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

			s1.addCompetence(c1_1);
			s1.addCompetence(c2_1);
			s1.addCompetence(c5_1);
			s7.addCompetence(c1_1);
			s7.addCompetence(c2_1);
			s7.addCompetence(c5_1);

			s2.addCompetence(c2_1);
			s2.addCompetence(c3_1);
			s2.addCompetence(c4_1);
			s2.addCompetence(c5_1);
			s6.addCompetence(c2_1);
			s6.addCompetence(c3_1);
			s6.addCompetence(c4_1);
			s6.addCompetence(c5_1);

			s3.addCompetence(c2_1);

			s4.addCompetence(c2_1);
			s4.addCompetence(c3_1);
			s8.addCompetence(c2_1);
			s8.addCompetence(c3_1);

			s5.addCompetence(c4_1);
			s5.addCompetence(c5_1);
			s9.addCompetence(c4_1);
			s9.addCompetence(c5_1);

			assertEquals(s1.getCompetences().size(), 3);
			assertEquals(s2.getCompetences().size(), 4);
			assertEquals(s3.getCompetences().size(), 1);
			assertEquals(s4.getCompetences().size(), 2);
			assertEquals(s5.getCompetences().size(), 2);
			assertEquals(s6.getCompetences().size(), 4);
			assertEquals(s7.getCompetences().size(), 3);
			assertEquals(s8.getCompetences().size(), 2);
			assertEquals(s9.getCompetences().size(), 2);

			for (int i = 0; i < s1.getCompetences().size(); i++) {
				List<Competence> l = s1.getCompetences();
				Competence comp = l.get(i);
				switch (i) {
				case 0:
					assertEquals(comp.getCode(), "C1.1");
					break;
				case 1:
					assertEquals(comp.getCode(), "C2.1");
					break;
				case 2:
					assertEquals(comp.getCode(), "C5.1");
					break;
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@Test
	public void testgetReferentiel() {
		try {

			Referentiel ref = new Referentiel(new Diplome("BAC PRO", "CB"));
			Referentiel ref1 = new Referentiel(new Diplome("CAP", "ORGO"));

			Savoir s1 = new Savoir(ref, "S1", "L'entreprise et son environement");
			Savoir s2 = new Savoir(ref, "S2", "La communication technique");
			Savoir s3 = new Savoir(ref1, "S3", "Le confort de l'habitat");
			Savoir s4 = new Savoir(ref1, "S4", "La m�canique et la r�sistance des mat�riaux");

			assertTrue(s1.getReferentiel().getDiplome().getDiplome().equals("BAC PRO"));
			assertTrue(s2.getReferentiel().getDiplome().getDiplome().equals("BAC PRO"));
			assertTrue(s3.getReferentiel().getDiplome().getDiplome().equals("CAP"));
			assertTrue(s4.getReferentiel().getDiplome().getDiplome().equals("CAP"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
