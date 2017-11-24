package fr.paquet.referentiel.test;

import static org.junit.Assert.*;
import org.junit.Test;
import fr.paquet.commun.Diplome;
import fr.paquet.commun.DiplomeFactory;
import fr.paquet.dataBase.Connect;
import fr.paquet.referentiel.Referentiel;
import fr.paquet.referentiel.ReferentielFactory;
import fr.paquet.referentiel.Savoir;
import fr.paquet.referentiel.SavoirAssocie;
import fr.paquet.referentiel.SousSavoirAssocie;

public class TestClassSousSavoirAssocie {

	@Test
	public void testgetDb() {
		try {

			Diplome dip = new DiplomeFactory().findDiplome("BAC PRO",
					"TECHNICIEN CONSTRUCTEUR BOIS");

			Referentiel ref = new ReferentielFactory().findReferentiel(dip);

			SavoirAssocie savass = ref.getCapacites().get(0).getCompetences().get(0).getSavoirs().get(0)
					.getSavoirsAssocies().get(0);

			assertTrue(savass.getSousSavoirsAssocies().get(0).getIntitule()
					.equals("Les différents partenaires de l’acte de construire (fonction et mission)"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testgetCode() {

		try {
			Referentiel ref = new Referentiel(new Diplome("BAC PRO", "CB"));

			SousSavoirAssocie ssa = new SousSavoirAssocie(
					new SavoirAssocie(new Savoir(ref, "    s3", "Les intervenants"), 1, "La maitrise d'oeuvre"), 1,
					"list3", 2);

			assertTrue(ssa.getCode().equals("S3.11"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testgetIntitule() {
		try {
			Referentiel ref = new Referentiel(new Diplome("BAC PRO", "CB"));

			SavoirAssocie s = new SavoirAssocie(new Savoir(ref, "S3", "Les intervenants"), 1, "La maitrise d'oeuvre");

			SousSavoirAssocie ssa = new SousSavoirAssocie(s, 1, "list", 2);
			SousSavoirAssocie ssa1 = new SousSavoirAssocie(s, 2, "list2", 3);

			assertEquals(ssa.getIntitule(), "list");
			assertEquals(ssa1.getIntitule(), "list2");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Test
	public void testgetNiveau() {
		try {
			Referentiel ref = new Referentiel(new Diplome("BAC PRO", "CB"));

			SousSavoirAssocie ssa = new SousSavoirAssocie(
					new SavoirAssocie(new Savoir(ref, "    s3", "Les intervenants"), 1, "La maitrise d'oeuvre"), 1,
					"list3", 1);
			SousSavoirAssocie ssa1 = new SousSavoirAssocie(
					new SavoirAssocie(new Savoir(ref, "    s3", "Les intervenants"), 2, "La maitrise d'oeuvre"), 1,
					"list3", 2);
			SousSavoirAssocie ssa2 = new SousSavoirAssocie(
					new SavoirAssocie(new Savoir(ref, "    s3", "Les intervenants"), 3, "La maitrise d'oeuvre"), 1,
					"list3", 3);
			SousSavoirAssocie ssa3 = new SousSavoirAssocie(
					new SavoirAssocie(new Savoir(ref, "    s3", "Les intervenants"), 4, "La maitrise d'oeuvre"), 1,
					"list3", 4);

			assertEquals(ssa.getNiveau(), 1);
			assertEquals(ssa1.getNiveau(), 2);
			assertEquals(ssa2.getNiveau(), 3);
			assertEquals(ssa3.getNiveau(), 4);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
