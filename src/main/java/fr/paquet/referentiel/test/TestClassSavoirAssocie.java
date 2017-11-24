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

public class TestClassSavoirAssocie {

	@Test
	public void testgetDb() {
		try {

			Diplome dip = new DiplomeFactory().findDiplome("BAC PRO",
					"TECHNICIEN CONSTRUCTEUR BOIS");

			Referentiel ref = new ReferentielFactory().findReferentiel(dip);

			Savoir sav = ref.getCapacites().get(0).getCompetences().get(0).getSavoirs().get(0);

			assertTrue(sav.getSavoirsAssocies().get(0).getCode().equals("S1.1"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testgetCode() {
		try {
			Referentiel ref = new Referentiel(new Diplome("BAC PRO", "CB"));
			SavoirAssocie savAss1 = new SavoirAssocie(new Savoir(ref, "    s3", "Les intervenants"), 1,
					"La maitrise d'oeuvre");

			assertTrue(savAss1.getCode().equals("S3.1"));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testgetIntitule() {
		try {
			Referentiel ref = new Referentiel(new Diplome("BAC PRO", "CB"));
			SavoirAssocie savAss2 = new SavoirAssocie(new Savoir(ref, "S3", "Les intervenants"), 1,
					"               La maitrise d'oeuvre");

			assertTrue(savAss2.getIntitule().equals("La maitrise d'oeuvre"));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testgetSousSavoirAssocie() {
		try {
			Referentiel ref = new Referentiel(new Diplome("BAC PRO", "CB"));

			SavoirAssocie savAss1 = new SavoirAssocie(new Savoir(ref, "S3", "Les intervenants"), 1,
					"La maitrise d'oeuvre");

			new SousSavoirAssocie(savAss1, 1, "list1", 1);
			new SousSavoirAssocie(savAss1, 2, "list2", 2);
			new SousSavoirAssocie(savAss1, 3, "list3", 3);
			new SousSavoirAssocie(savAss1, 4, "list4", 4);

			assertEquals(savAss1.getSousSavoirsAssocies().size(), 4);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
