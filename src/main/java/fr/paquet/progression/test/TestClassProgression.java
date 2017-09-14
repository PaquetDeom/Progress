package fr.paquet.progression.test;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;

import fr.paquet.commun.Diplome;
import fr.paquet.progression.ConfigCompInt;
import fr.paquet.progression.Progression;
import fr.paquet.referentiel.Capacite;
import fr.paquet.referentiel.Competence;
import fr.paquet.referentiel.CompetenceIntermediaire;
import fr.paquet.referentiel.Referentiel;
import fr.paquet.referentiel.Unite;
import fr.paquet.sequence.Sequence;

public class TestClassProgression {

	@Test
	public void testgetConfigCompInts() {
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

			new ConfigCompInt(prog, c_1_1_1, 1, 7);
			new ConfigCompInt(prog, c_1_1_2, 2, 9);
			new ConfigCompInt(prog, c_1_2_1, 10, 14);
			new ConfigCompInt(prog, c_1_2_2, 8, 12);
			new ConfigCompInt(prog, c_2_1_1, 13, 16);
			new ConfigCompInt(prog, c_2_1_2, 14, 15);
			new ConfigCompInt(prog, c_3_1_1, 17, 22);
			new ConfigCompInt(prog, c_3_1_2, 20, 21);

			assertEquals(prog.getConfigCompInts().size(), 8);

			for (int i = 0; i < prog.getConfigCompInts().size(); i++) {
				List<ConfigCompInt> l = prog.getConfigCompInts();
				ConfigCompInt cci = l.get(i);
				switch (i) {
				case 0:
					assertEquals(cci.getCompetenceIntermediaire().getCode(), "C1.1.1");
					break;
				case 1:
					assertEquals(cci.getCompetenceIntermediaire().getCode(), "C1.1.2");

					break;
				case 2:
					assertEquals(cci.getCompetenceIntermediaire().getCode(), "C1.2.1");

					break;
				case 3:
					assertEquals(cci.getCompetenceIntermediaire().getCode(), "C1.2.2");

					break;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testgetDebut() {
		try {

			Progression prog = new Progression(new Referentiel(new Diplome("BAC PRO", "CB")), "essai1");
			Progression prog1 = new Progression(new Referentiel(new Diplome("BAC PRO", "CB")), "essai2");
			Progression prog2 = new Progression(new Referentiel(new Diplome("BAC PRO", "CB")), "essai3");

			assertTrue(prog.getDebut() == 1);
			assertTrue(prog1.getDebut() == 1);
			assertTrue(prog2.getDebut() == 1);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testgetFin() {
		try {

			Progression prog = new Progression(new Referentiel(new Diplome("BAC PRO", "CB")), "essai1");
			Progression prog1 = new Progression(new Referentiel(new Diplome("BAC PRO", "CB")), "essai2");
			Progression prog2 = new Progression(new Referentiel(new Diplome("BAC PRO", "CB")), "essai3");

			assertTrue(prog.getFin() == 1152);
			assertTrue(prog1.getFin() == 1152);
			assertTrue(prog2.getFin() == 1152);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testgetSequences() {
		try {
			Progression prog = new Progression(new Referentiel(new Diplome("BAC PRO", "CB")), "essai1");

			new Sequence(prog, "Titre 1");
			new Sequence(prog, "Titre 2");
			new Sequence(prog, "Titre 3");

			assertTrue(prog.getSequences().size() == 3);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testgetTitre() {
		try {
			Progression prog = new Progression(new Referentiel(new Diplome("BAC PRO", "CB")), "essai1");
			Progression prog1 = new Progression(new Referentiel(new Diplome("BAC PRO", "CB")), "  essai2");
			Progression prog2 = new Progression(new Referentiel(new Diplome("BAC PRO", "CB")), "        esSAi3    ");

			assertTrue(prog.getTitre().equals("ESSAI1"));
			assertTrue(prog1.getTitre().equals("ESSAI2"));
			assertTrue(prog2.getTitre().equals("ESSAI3"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
