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
import fr.paquet.referentiel.Unite;

public class TestClassCapacite {

	@Test
	public void testgetDb() {
		try {

			Diplome dip = new DiplomeFactory().findDiplome("BAC PRO",
					"TECHNICIEN CONSTRUCTEUR BOIS");

			Referentiel ref = new ReferentielFactory().findReferentiel(dip);

			assertTrue(ref.getCapacites().size() == 6);

			for (int i = 0; i < ref.getCapacites().size(); i++) {
				Capacite cap = ref.getCapacites().get(i);
				switch (i) {
				case 0:
					assertTrue(cap.getCode().equals("C1"));
					break;
				case 1:
					assertTrue(cap.getCode().equals("C2"));
					break;
				case 2:
					assertTrue(cap.getCode().equals("C3"));
					break;
				case 3:
					assertTrue(cap.getCode().equals("C4"));
					break;
				case 4:
					assertTrue(cap.getCode().equals("C5"));
					break;
				case 5:
					assertTrue(cap.getCode().equals("C6"));
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

			Capacite cap1 = new Capacite(ref, " c1", "S'INFORMER ANALYSER");
			Capacite cap2 = new Capacite(ref, " C2", "	TRAITER DECIDER PREPARER");
			Capacite cap3 = new Capacite(ref, "C3", "FABRIQUER");

			assertTrue(cap1.getCode().equals("C1"));
			assertTrue(cap2.getCode().equals("C2"));
			assertTrue(cap3.getCode().equals("C3"));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testgetIntitule() {

		try {

			Referentiel ref = new Referentiel(new Diplome("BAC PRO", "CB"));

			Capacite cap1 = new Capacite(ref, " c1", "S'INFORMER ANALYSER          ");
			Capacite cap2 = new Capacite(ref, " C2", "	TRAITER DECIDER PREPARER");
			Capacite cap3 = new Capacite(ref, "C3", "FAbriQUER");

			assertTrue(cap1.getIntitule().equals("S'INFORMER ANALYSER"));
			assertTrue(cap2.getIntitule().equals("TRAITER DECIDER PREPARER"));
			assertTrue(cap3.getIntitule().equals("FABRIQUER"));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testgetCompetences() {

		try {

			Referentiel ref = new Referentiel(new Diplome("BAC PRO", "CB"));

			Capacite cap1 = new Capacite(ref, "C1", "S'INFORMER ANALYSER");

			new Competence(cap1, new Unite(11, "ANALYSE"), 1, "D�coder et analyser des donn�es de d�finition");
			new Competence(cap1, new Unite(11, "ANALYSE"), 2, "D�coder et analyser des donn�es op�ratoires");
			new Competence(cap1, new Unite(11, "ANALYSE"), 3, "D�coder et analyser des donn�es de gestion");
			new Competence(cap1, new Unite(11, "ANALYSE"), 4, "Relever et r�ceptionner une situation de chantier");

			assertEquals(cap1.getCompetences().size(), 4);

			for (int i = 0; i < cap1.getCompetences().size(); i++) {
				List<Competence> l = cap1.getCompetences();
				Competence comp = l.get(i);
				switch (i) {
				case 0:
					assertEquals(comp.getCode(), "C1.1");
					break;
				case 1:
					assertEquals(comp.getCode(), "C1.2");
					break;
				case 2:
					assertEquals(comp.getCode(), "C1.3");
					break;
				case 3:
					assertEquals(comp.getCode(), "C1.4");
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

			Capacite cap1 = new Capacite(ref, "C1", "S'INFORMER ANALYSER");
			Capacite cap2 = new Capacite(ref1, "C1", "MANGER");

			assertTrue(cap1.getReferentiel() == ref);
			assertTrue(cap1.getReferentiel().getDiplome().getDiplome().equals("BAC PRO"));
			assertTrue(cap1.getReferentiel().getDiplome().getType().equals("CB"));

			assertTrue(cap2.getReferentiel() == ref1);
			assertTrue(cap2.getReferentiel().getDiplome().getDiplome().equals("CAP"));
			assertTrue(cap2.getReferentiel().getDiplome().getType().equals("ORGO"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
