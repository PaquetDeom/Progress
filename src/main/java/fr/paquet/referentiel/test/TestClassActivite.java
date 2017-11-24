package fr.paquet.referentiel.test;

import static org.junit.Assert.*;
import org.junit.Test;

import fr.paquet.commun.Diplome;
import fr.paquet.commun.DiplomeFactory;
import fr.paquet.dataBase.Connect;
import fr.paquet.referentiel.Activite;
import fr.paquet.referentiel.Fonction;
import fr.paquet.referentiel.Rap;
import fr.paquet.referentiel.Referentiel;
import fr.paquet.referentiel.ReferentielFactory;

public class TestClassActivite {

	@Test
	public void testgetDb() {
		try {

			Diplome dip = new DiplomeFactory().findDiplome("BAC PRO", "TECHNICIEN CONSTRUCTEUR BOIS");
			
			Referentiel ref = new ReferentielFactory().findReferentiel(dip);

			Fonction fc = ref.getRap().getFonctions().get(0);

			assertTrue(fc.getActivites().size() == 23);

			for (int i = 0; i < fc.getActivites().size(); i++) {
				Activite act = fc.getActivites().get(i);
				switch (i) {
				case 0:
					assertTrue(act.getIntitule().equals("PREPARATION : Controle et réception de l'existant"));
					break;
				case 1:
					assertTrue(act.getIntitule().equals("PREPARATION : Définition d'un produit"));
					break;
				case 2:
					assertTrue(act.getIntitule().equals("PREPARATION : Définition de la méthode de mise en oeuvre"));
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testgetFonction() {
		try {

			Activite act = new Activite(new Fonction(new Rap(new Referentiel(new Diplome("BAC PRO", "CB"))), "   REALISATION"),
					"PREPARATION : Contr�le et r�ception de l'existant");

			assertEquals(act.getFonction().getIntitule(), "REALISATION");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testgetIntitule() {
		try {

			Activite act1 = new Activite(new Fonction(new Rap(new Referentiel(new Diplome("BAC PRO", "CB"))), "REALISATION"),
					"mise en oeuvre : d�finition d'un produit       ");
			Activite act = new Activite(new Fonction(new Rap(new Referentiel(new Diplome("BAC PRO", "CB"))), "REALISATION"),
					"       PREPARAtion : d�finition d'un produit");

			assertTrue(act1.getIntitule().equals("MISE EN OEUVRE : d�finition d'un produit"));
			assertTrue(act.getIntitule().equals("PREPARATION : d�finition d'un produit"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testgetTaches() {
		try {
			Activite act = new Activite(new Fonction(new Rap(new Referentiel(new Diplome("BAC PRO", "CB"))), "REALISATION"),
					"PREPARATION : Contr�le et r�ception de l'existant");

			assertEquals(act.getFonction().getIntitule(), "REALISATION");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testgetTypeTache() {
		try {
			new Activite(new Fonction(new Rap(new Referentiel(new Diplome("BAC PRO", "CB"))), "REALISATION"),
					"PREPARATION : Contr�le et r�ception de l'existant");

			Activite act = new Activite(new Fonction(new Rap(new Referentiel(new Diplome("BAC PRO", "CB"))), "REALISATION"),
					"    PREPAration : D�finition d'un produit   ");
			Activite act1 = new Activite(new Fonction(new Rap(new Referentiel(new Diplome("BAC PRO", "CB"))), "REALISATION"),
					"    fabrication : organisation et preparation de la fabrication   ");
			Activite act2 = new Activite(new Fonction(new Rap(new Referentiel(new Diplome("BAC PRO", "CB"))), "REALISATION"),
					"    LOGISTIQUE   ");

			assertEquals(act.getTypeTache(), "PREPARATION");
			assertEquals(act1.getTypeTache(), "FABRICATION");
			assertEquals(act2.getTypeTache(), "LOGISTIQUE");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
