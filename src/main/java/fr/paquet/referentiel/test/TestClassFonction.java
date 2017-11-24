package fr.paquet.referentiel.test;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;

import fr.paquet.commun.Diplome;
import fr.paquet.commun.DiplomeFactory;
import fr.paquet.dataBase.Connect;
import fr.paquet.referentiel.Activite;
import fr.paquet.referentiel.Fonction;
import fr.paquet.referentiel.Rap;
import fr.paquet.referentiel.Referentiel;
import fr.paquet.referentiel.ReferentielFactory;

public class TestClassFonction {

	@Test
	public void testgetDb() {
		try {

			Diplome dip = new DiplomeFactory().findDiplome("BAC PRO",
					"TECHNICIEN CONSTRUCTEUR BOIS");

			Referentiel ref = new ReferentielFactory().findReferentiel(dip);
			Rap rap = ref.getRap();

			List<Fonction> fc = rap.getFonctions();

			assertTrue(fc.size() == 1);

			for (int i = 0; i < fc.size(); i++) {
				Fonction fonc = fc.get(i);
				switch (i) {
				case 0:
					assertTrue(fonc.getIntitule().equals("REALISATION"));
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

			Fonction f1 = new Fonction(new Rap(new Referentiel(new Diplome("BAC PRO", "CB"))), "          realisation");
			Fonction f2 = new Fonction(new Rap(new Referentiel(new Diplome("BAC PRO", "CB"))), "realisation");
			Fonction f3 = new Fonction(new Rap(new Referentiel(new Diplome("BAC PRO", "CB"))), "     realisAtion     ");

			assertTrue(f1.getIntitule().equals("REALISATION"));
			assertTrue(f2.getIntitule().equals("REALISATION"));
			assertTrue(f3.getIntitule().equals("REALISATION"));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testgetActivites() {

		try {

			Fonction fonc1 = new Fonction(new Rap(new Referentiel(new Diplome("BAC PRO", "CB"))), "REALISATION");

			new Activite(fonc1, "PREPARATION : Contr�le et r�ception de l'existant");
			new Activite(fonc1, "PREPARATION : D�finition d'un produit");
			new Activite(fonc1, "PREPARATION : D�finition de la m�thode de mise en oeuvre");
			new Activite(fonc1, "PREPARATION : Planification de la r�alisation de l'ouvrage");

			assertEquals(fonc1.getActivites().size(), 4);

			for (int i = 0; i < fonc1.getActivites().size(); i++) {
				List<Activite> l = fonc1.getActivites();
				Activite act = l.get(i);
				switch (i) {
				case 0:
					assertEquals(act.getIntitule(), "PREPARATION : contr�le et r�ception de l'existant");
					break;
				case 1:
					assertEquals(act.getIntitule(), "PREPARATION : d�finition d'un produit");
					break;
				case 2:
					assertEquals(act.getIntitule(), "PREPARATION : d�finition de la m�thode de mise en oeuvre");
					break;
				case 3:
					assertEquals(act.getIntitule(), "PREPARATION : planification de la r�alisation de l'ouvrage");
					break;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
