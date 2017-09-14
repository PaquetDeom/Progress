package fr.paquet.progression.test;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.paquet.commun.Diplome;
import fr.paquet.progression.ConfigCompInt;
import fr.paquet.progression.Progression;
import fr.paquet.referentiel.Capacite;
import fr.paquet.referentiel.Competence;
import fr.paquet.referentiel.CompetenceIntermediaire;
import fr.paquet.referentiel.Referentiel;
import fr.paquet.referentiel.TaxonomieBlum;
import fr.paquet.referentiel.Unite;

public class TestClassConfigCompInt {

	@Test
	public void testgetCompetenceIntermediaire() {
		try {

			Referentiel ref = new Referentiel(new Diplome("BAC PRO", "CB"));

			Progression prog = new Progression(ref, "essai1");

			Capacite cap1 = new Capacite(ref, "C1", "S'INFORMER ANALYSER");
			Capacite cap2 = new Capacite(ref, "C2", "TRAITER DECIDER PREPARER");
			Capacite cap3 = new Capacite(ref, "C3", "FABRIQUER");

			Unite u11 = new Unite(11, "ANALYSE TECHNIQUE D'UN OUVRAGE");
			Unite u2 = new Unite(2, "PREPARATION D'UNE FABRICATION ET D'UNE MISE EN OEUVRE SUR CHANTIER");
			Unite u32 = new Unite(32, "FABRICATION D'UN OUVRAGE");

			Competence comp1 = new Competence(cap1, u11, 1, "D�coder et analyser des donn�es de d�finition");
			Competence comp2 = new Competence(cap1, u2, 2, "D�coder et analyser des donn�es op�ratoires");
			Competence comp3 = new Competence(cap2, u11, 1, "Choisir, adapter et justifier des solutions techniques");
			Competence comp4 = new Competence(cap3, u32, 1, "Organiser et mettre en s�curit� les postes de travail");

			CompetenceIntermediaire c_1_1_1 = new CompetenceIntermediaire(comp1, 1,
					"Inventorier les pieces constitutives");
			CompetenceIntermediaire c_1_1_2 = new CompetenceIntermediaire(comp1, 2,
					"Caracteriser les pieces et composants");
			CompetenceIntermediaire c_1_2_1 = new CompetenceIntermediaire(comp2, 1,
					"Identifier les moyens de fabrication");
			CompetenceIntermediaire c_1_2_2 = new CompetenceIntermediaire(comp2, 2, "Identifier les moyens humains");
			CompetenceIntermediaire c_2_1_1 = new CompetenceIntermediaire(comp3, 1, "Identifier les caracteristiques");
			CompetenceIntermediaire c_2_1_2 = new CompetenceIntermediaire(comp3, 2, "Comparer les caracteristiques");
			CompetenceIntermediaire c_3_1_1 = new CompetenceIntermediaire(comp4, 1, "Organiser les zones de travail");
			CompetenceIntermediaire c_3_1_2 = new CompetenceIntermediaire(comp4, 2,
					"Rendre accessible les postes de travail");

			ConfigCompInt cc1 = new ConfigCompInt(prog, c_1_1_1, 1, 7);
			ConfigCompInt cc2 = new ConfigCompInt(prog, c_1_1_2, 2, 9);
			ConfigCompInt cc3 = new ConfigCompInt(prog, c_1_2_1, 10, 14);
			ConfigCompInt cc4 = new ConfigCompInt(prog, c_1_2_2, 8, 12);
			ConfigCompInt cc5 = new ConfigCompInt(prog, c_2_1_1, 13, 16);
			ConfigCompInt cc6 = new ConfigCompInt(prog, c_2_1_2, 14, 15);
			ConfigCompInt cc7 = new ConfigCompInt(prog, c_3_1_1, 17, 22);
			ConfigCompInt cc8 = new ConfigCompInt(prog, c_3_1_2, 20, 21);

			assertTrue(cc1.getCompetenceIntermediaire().getCode().equals("C1.1.1"));
			assertTrue(cc2.getCompetenceIntermediaire().getCode().equals("C1.1.2"));
			assertTrue(cc3.getCompetenceIntermediaire().getCode().equals("C1.2.1"));
			assertTrue(cc4.getCompetenceIntermediaire().getCode().equals("C1.2.2"));
			assertTrue(cc5.getCompetenceIntermediaire().getCode().equals("C2.1.1"));
			assertTrue(cc6.getCompetenceIntermediaire().getCode().equals("C2.1.2"));
			assertTrue(cc7.getCompetenceIntermediaire().getCode().equals("C3.1.1"));
			assertTrue(cc8.getCompetenceIntermediaire().getCode().equals("C3.1.2"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testgetDebut() {
		try {

			Referentiel ref = new Referentiel(new Diplome("BAC PRO", "CB"));

			Progression prog = new Progression(ref, "essai1");

			Capacite cap1 = new Capacite(ref, "C1", "S'INFORMER ANALYSER");
			Capacite cap2 = new Capacite(ref, "C2", "TRAITER DECIDER PREPARER");
			Capacite cap3 = new Capacite(ref, "C3", "FABRIQUER");

			Unite u11 = new Unite(11, "ANALYSE TECHNIQUE D'UN OUVRAGE");
			Unite u2 = new Unite(2, "PREPARATION D'UNE FABRICATION ET D'UNE MISE EN OEUVRE SUR CHANTIER");
			Unite u32 = new Unite(32, "FABRICATION D'UN OUVRAGE");

			Competence comp1 = new Competence(cap1, u11, 1, "D�coder et analyser des donn�es de d�finition");
			Competence comp2 = new Competence(cap1, u2, 2, "D�coder et analyser des donn�es op�ratoires");
			Competence comp3 = new Competence(cap2, u11, 1, "Choisir, adapter et justifier des solutions techniques");
			Competence comp4 = new Competence(cap3, u32, 1, "Organiser et mettre en s�curit� les postes de travail");

			CompetenceIntermediaire c_1_1_1 = new CompetenceIntermediaire(comp1, 1,
					"Inventorier les pieces constitutives");
			CompetenceIntermediaire c_1_1_2 = new CompetenceIntermediaire(comp1, 2,
					"Caracteriser les pieces et composants");
			CompetenceIntermediaire c_1_2_1 = new CompetenceIntermediaire(comp2, 1,
					"Identifier les moyens de fabrication");
			CompetenceIntermediaire c_1_2_2 = new CompetenceIntermediaire(comp2, 2, "Identifier les moyens humains");
			CompetenceIntermediaire c_2_1_1 = new CompetenceIntermediaire(comp3, 1, "Identifier les caracteristiques");
			CompetenceIntermediaire c_2_1_2 = new CompetenceIntermediaire(comp3, 2, "Comparer les caracteristiques");
			CompetenceIntermediaire c_3_1_1 = new CompetenceIntermediaire(comp4, 1, "Organiser les zones de travail");
			CompetenceIntermediaire c_3_1_2 = new CompetenceIntermediaire(comp4, 2,
					"Rendre accessible les postes de travail");

			ConfigCompInt cc1 = new ConfigCompInt(prog, c_1_1_1, 1, 7);
			ConfigCompInt cc2 = new ConfigCompInt(prog, c_1_1_2, 2, 9);
			ConfigCompInt cc3 = new ConfigCompInt(prog, c_1_2_1, 10, 14);
			ConfigCompInt cc4 = new ConfigCompInt(prog, c_1_2_2, 8, 12);
			ConfigCompInt cc5 = new ConfigCompInt(prog, c_2_1_1, 13, 16);
			ConfigCompInt cc6 = new ConfigCompInt(prog, c_2_1_2, 14, 15);
			ConfigCompInt cc7 = new ConfigCompInt(prog, c_3_1_1, 17, 22);
			ConfigCompInt cc8 = new ConfigCompInt(prog, c_3_1_2, 20, 21);

			assertTrue(cc1.getDebut() == 1);
			assertTrue(cc2.getDebut() == 2);
			assertTrue(cc3.getDebut() == 10);
			assertTrue(cc4.getDebut() == 8);
			assertTrue(cc5.getDebut() == 13);
			assertTrue(cc6.getDebut() == 14);
			assertTrue(cc7.getDebut() == 17);
			assertTrue(cc8.getDebut() == 20);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testgetFin() {
		try {

			Referentiel ref = new Referentiel(new Diplome("BAC PRO", "CB"));

			Progression prog = new Progression(ref, "essai1");

			Capacite cap1 = new Capacite(ref, "C1", "S'INFORMER ANALYSER");
			Capacite cap2 = new Capacite(ref, "C2", "TRAITER DECIDER PREPARER");
			Capacite cap3 = new Capacite(ref, "C3", "FABRIQUER");

			Unite u11 = new Unite(11, "ANALYSE TECHNIQUE D'UN OUVRAGE");
			Unite u2 = new Unite(2, "PREPARATION D'UNE FABRICATION ET D'UNE MISE EN OEUVRE SUR CHANTIER");
			Unite u32 = new Unite(32, "FABRICATION D'UN OUVRAGE");

			Competence comp1 = new Competence(cap1, u11, 1, "D�coder et analyser des donn�es de d�finition");
			Competence comp2 = new Competence(cap1, u2, 2, "D�coder et analyser des donn�es op�ratoires");
			Competence comp3 = new Competence(cap2, u11, 1, "Choisir, adapter et justifier des solutions techniques");
			Competence comp4 = new Competence(cap3, u32, 1, "Organiser et mettre en s�curit� les postes de travail");

			CompetenceIntermediaire c_1_1_1 = new CompetenceIntermediaire(comp1, 1,
					"Inventorier les pieces constitutives");
			CompetenceIntermediaire c_1_1_2 = new CompetenceIntermediaire(comp1, 2,
					"Caracteriser les pieces et composants");
			CompetenceIntermediaire c_1_2_1 = new CompetenceIntermediaire(comp2, 1,
					"Identifier les moyens de fabrication");
			CompetenceIntermediaire c_1_2_2 = new CompetenceIntermediaire(comp2, 2, "Identifier les moyens humains");
			CompetenceIntermediaire c_2_1_1 = new CompetenceIntermediaire(comp3, 1, "Identifier les caracteristiques");
			CompetenceIntermediaire c_2_1_2 = new CompetenceIntermediaire(comp3, 2, "Comparer les caracteristiques");
			CompetenceIntermediaire c_3_1_1 = new CompetenceIntermediaire(comp4, 1, "Organiser les zones de travail");
			CompetenceIntermediaire c_3_1_2 = new CompetenceIntermediaire(comp4, 2,
					"Rendre accessible les postes de travail");

			ConfigCompInt cc1 = new ConfigCompInt(prog, c_1_1_1, 1, 7);
			ConfigCompInt cc2 = new ConfigCompInt(prog, c_1_1_2, 2, 9);
			ConfigCompInt cc3 = new ConfigCompInt(prog, c_1_2_1, 10, 14);
			ConfigCompInt cc4 = new ConfigCompInt(prog, c_1_2_2, 8, 12);
			ConfigCompInt cc5 = new ConfigCompInt(prog, c_2_1_1, 13, 16);
			ConfigCompInt cc6 = new ConfigCompInt(prog, c_2_1_2, 14, 15);
			ConfigCompInt cc7 = new ConfigCompInt(prog, c_3_1_1, 17, 22);
			ConfigCompInt cc8 = new ConfigCompInt(prog, c_3_1_2, 20, 21);

			assertTrue(cc1.getFin() == 7);
			assertTrue(cc2.getFin() == 9);
			assertTrue(cc3.getFin() == 14);
			assertTrue(cc4.getFin() == 12);
			assertTrue(cc5.getFin() == 16);
			assertTrue(cc6.getFin() == 15);
			assertTrue(cc7.getFin() == 22);
			assertTrue(cc8.getFin() == 21);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testgetProgression() {
		try {

			Referentiel ref = new Referentiel(new Diplome("BAC PRO", "CB"));

			Progression prog = new Progression(ref, "essai1");
			Progression prog1 = new Progression(ref, "essai2");
			Progression prog2 = new Progression(ref, "essai3");

			Capacite cap1 = new Capacite(ref, "C1", "S'INFORMER ANALYSER");
			Capacite cap2 = new Capacite(ref, "C2", "TRAITER DECIDER PREPARER");
			Capacite cap3 = new Capacite(ref, "C3", "FABRIQUER");

			Unite u11 = new Unite(11, "ANALYSE TECHNIQUE D'UN OUVRAGE");
			Unite u2 = new Unite(2, "PREPARATION D'UNE FABRICATION ET D'UNE MISE EN OEUVRE SUR CHANTIER");
			Unite u32 = new Unite(32, "FABRICATION D'UN OUVRAGE");

			Competence comp1 = new Competence(cap1, u11, 1, "D�coder et analyser des donn�es de d�finition");
			Competence comp2 = new Competence(cap1, u2, 2, "D�coder et analyser des donn�es op�ratoires");
			Competence comp3 = new Competence(cap2, u11, 1, "Choisir, adapter et justifier des solutions techniques");
			Competence comp4 = new Competence(cap3, u32, 1, "Organiser et mettre en s�curit� les postes de travail");

			CompetenceIntermediaire c_1_1_1 = new CompetenceIntermediaire(comp1, 1,
					"Inventorier les pieces constitutives");
			CompetenceIntermediaire c_1_1_2 = new CompetenceIntermediaire(comp1, 2,
					"Caracteriser les pieces et composants");
			CompetenceIntermediaire c_1_2_1 = new CompetenceIntermediaire(comp2, 1,
					"Identifier les moyens de fabrication");
			CompetenceIntermediaire c_1_2_2 = new CompetenceIntermediaire(comp2, 2, "Identifier les moyens humains");
			CompetenceIntermediaire c_2_1_1 = new CompetenceIntermediaire(comp3, 1, "Identifier les caracteristiques");
			CompetenceIntermediaire c_2_1_2 = new CompetenceIntermediaire(comp3, 2, "Comparer les caracteristiques");
			CompetenceIntermediaire c_3_1_1 = new CompetenceIntermediaire(comp4, 1, "Organiser les zones de travail");
			CompetenceIntermediaire c_3_1_2 = new CompetenceIntermediaire(comp4, 2,
					"Rendre accessible les postes de travail");

			ConfigCompInt cc1 = new ConfigCompInt(prog, c_1_1_1, 1, 7);
			ConfigCompInt cc2 = new ConfigCompInt(prog, c_1_1_2, 2, 9);
			ConfigCompInt cc3 = new ConfigCompInt(prog, c_1_2_1, 10, 14);
			ConfigCompInt cc4 = new ConfigCompInt(prog1, c_1_2_2, 8, 12);
			ConfigCompInt cc5 = new ConfigCompInt(prog1, c_2_1_1, 13, 16);
			ConfigCompInt cc6 = new ConfigCompInt(prog1, c_2_1_2, 14, 15);
			ConfigCompInt cc7 = new ConfigCompInt(prog2, c_3_1_1, 17, 22);
			ConfigCompInt cc8 = new ConfigCompInt(prog2, c_3_1_2, 20, 21);

			assertTrue(cc1.getProgression().getTitre().equals("ESSAI1"));
			assertTrue(cc7.getProgression().getTitre().equals("ESSAI3"));
			assertTrue(cc8.getProgression().getTitre().equals("ESSAI3"));
			assertTrue(cc2.getProgression().getTitre().equals("ESSAI1"));
			assertTrue(cc5.getProgression().getTitre().equals("ESSAI2"));
			assertTrue(cc6.getProgression().getTitre().equals("ESSAI2"));
			assertTrue(cc3.getProgression().getTitre().equals("ESSAI1"));
			assertTrue(cc4.getProgression().getTitre().equals("ESSAI2"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testgetNiveau() {
		try {

			Referentiel ref = new Referentiel(new Diplome("BAC PRO", "CB"));

			Progression prog = new Progression(ref, "essai1");

			Capacite cap1 = new Capacite(ref, "C1", "S'INFORMER ANALYSER");

			Unite u11 = new Unite(11, "ANALYSE TECHNIQUE D'UN OUVRAGE");
			Unite u2 = new Unite(2, "PREPARATION D'UNE FABRICATION ET D'UNE MISE EN OEUVRE SUR CHANTIER");

			Competence comp1 = new Competence(cap1, u11, 1, "D�coder et analyser des donn�es de d�finition");
			Competence comp2 = new Competence(cap1, u2, 2, "D�coder et analyser des donn�es op�ratoires");

			CompetenceIntermediaire c_1_1_1 = new CompetenceIntermediaire(comp1, 1,
					"Inventorier les pieces constitutives");
			CompetenceIntermediaire c_1_1_2 = new CompetenceIntermediaire(comp1, 2,
					"Caracteriser les pieces et composants");
			CompetenceIntermediaire c_1_2_1 = new CompetenceIntermediaire(comp2, 1,
					"Identifier les moyens de fabrication");

			ConfigCompInt cc1 = new ConfigCompInt(prog, c_1_1_1, 1, 7);
			ConfigCompInt cc2 = new ConfigCompInt(prog, c_1_1_2, 2, 9);
			ConfigCompInt cc3 = new ConfigCompInt(prog, c_1_2_1, 10, 14);

			cc1.setValidNiveau(false);
			cc2.setValidNiveau(false);
			cc1.setNiveau(5);
			cc2.setNiveau(3);

			TaxonomieBlum.addTaxonomieBlum("IDENTIFIER", 3);
			TaxonomieBlum.addTaxonomieBlum("EVALUER", 6);

			assertTrue(cc3.getNiveau() == 3);
			assertTrue(cc1.getNiveau() == 5);
			assertTrue(cc2.getNiveau() == 3);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
