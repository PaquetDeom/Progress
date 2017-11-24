package fr.paquet.sequence.test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import fr.paquet.commun.Diplome;
import fr.paquet.commun.DiplomeFactory;
import fr.paquet.dataBase.Connect;
import fr.paquet.donneeTest.SequenceTest;
import fr.paquet.progression.ConfigCompInt;
import fr.paquet.progression.Progression;
import fr.paquet.referentiel.Capacite;
import fr.paquet.referentiel.Competence;
import fr.paquet.referentiel.CompetenceIntermediaire;
import fr.paquet.referentiel.Referentiel;
import fr.paquet.referentiel.ReferentielFactory;
import fr.paquet.sequence.Sequence;

public class TestClassSequence {

	@Test
	public void testgetDb() {
		try {

			Diplome dip = new DiplomeFactory().findDiplome("BAC PRO",
					"TECHNICIEN CONSTRUCTEUR BOIS");

			Referentiel ref = new ReferentielFactory().findReferentiel(dip);
			Progression prog = new Progression(ref, "essai1");

			List<Competence> listComp = new ArrayList<Competence>();
			List<CompetenceIntermediaire> listCompInt = new ArrayList<CompetenceIntermediaire>();

			for (int i = 0; i < ref.getCapacites().size(); i++) {
				Capacite cap = ref.getCapacites().get(i);
				for (int n = 0; n < cap.getCompetences().size(); n++) {
					Competence comp = cap.getCompetences().get(n);
					listComp.add(comp);
					for (int m = 0; m < comp.getCompetencesIntermediaires().size(); m++) {
						CompetenceIntermediaire compInt = comp.getCompetencesIntermediaires().get(m);
						listCompInt.add(compInt);
					}
				}
			}

			new ConfigCompInt(prog, listCompInt.get(0), 1, 4);
			new ConfigCompInt(prog, listCompInt.get(1), 2, 8);
			new ConfigCompInt(prog, listCompInt.get(2), 6, 9);
			new ConfigCompInt(prog, listCompInt.get(10), 9, 12);
			new ConfigCompInt(prog, listCompInt.get(11), 11, 13);
			new ConfigCompInt(prog, listCompInt.get(12), 11, 12);
			new ConfigCompInt(prog, listCompInt.get(20), 14, 21);
			new ConfigCompInt(prog, listCompInt.get(21), 16, 19);
			new ConfigCompInt(prog, listCompInt.get(22), 20, 23);
			new ConfigCompInt(prog, listCompInt.get(30), 22, 25);
			new ConfigCompInt(prog, listCompInt.get(31), 25, 29);
			new ConfigCompInt(prog, listCompInt.get(32), 26, 29);
			new ConfigCompInt(prog, listCompInt.get(40), 27, 31);
			new ConfigCompInt(prog, listCompInt.get(41), 31, 34);
			new ConfigCompInt(prog, listCompInt.get(42), 32, 35);
			new ConfigCompInt(prog, listCompInt.get(50), 35, 36);
			new ConfigCompInt(prog, listCompInt.get(51), 28, 31);
			new ConfigCompInt(prog, listCompInt.get(52), 31, 40);
			new ConfigCompInt(prog, listCompInt.get(53), 34, 38);
			new ConfigCompInt(prog, listCompInt.get(70), 37, 51);
			new ConfigCompInt(prog, listCompInt.get(71), 41, 46);

			Sequence seq1 = new Sequence(prog, "sequence1");
			seq1.setDebut(1);
			seq1.setFin(5);

			assertTrue(seq1.getConfigCompInts().size() == 2);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void TestgetChavauchement() {
		try {

			Sequence seq = SequenceTest.getSequence();
			assertTrue(seq.getChevauchement() == null);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void TestgetCompetenceIntermediaire() {
		try {

			Sequence seq = SequenceTest.getSequence();
			// TODO
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void TestgetCompetences() {
		try {
			Sequence seq = SequenceTest.getSequence();
			// TODO
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void TestgetConfigCompInts() {
		try {
			Sequence seq = SequenceTest.getSequence();
			// TODO
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void TestgetDebut() {
		try {

			Sequence seq = SequenceTest.getSequence();

			assertTrue(seq.getDebut() == 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void TestgetFin() {
		try {
			Sequence seq = SequenceTest.getSequence();

			assertTrue(seq.getFin() == 5);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void TestgetNombreDeSeance() {
		try {
			Sequence seq = SequenceTest.getSequence();
			// TODO
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void TestgetNbSeance() {
		try {
			Sequence seq = SequenceTest.getSequence();
			// TODO
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void TestgetPhase() {
		try {
			Sequence seq = SequenceTest.getSequence();
			// TODO
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void TestgetPrerequis() {
		try {
			Sequence seq = SequenceTest.getSequence();
			// TODO
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void TestgetProgression() {
		try {
			Sequence seq = SequenceTest.getSequence();
			// TODO
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void TestgetTitre() {
		try {
			Sequence seq = SequenceTest.getSequence();
			// TODO
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void TestgetSeances() {
		try {
			Sequence seq = SequenceTest.getSequence();
			// TODO
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void TestgetUnites() {
		try {
			Sequence seq = SequenceTest.getSequence();
			// TODO
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
