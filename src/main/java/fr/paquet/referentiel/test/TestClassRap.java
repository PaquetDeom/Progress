package fr.paquet.referentiel.test;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;

import fr.paquet.commun.Diplome;
import fr.paquet.commun.DiplomeFactory;
import fr.paquet.dataBase.Connect;
import fr.paquet.referentiel.Fonction;
import fr.paquet.referentiel.Rap;
import fr.paquet.referentiel.Referentiel;
import fr.paquet.referentiel.ReferentielFactory;

public class TestClassRap {

	@Test
	public void testgetDb() {
		try {

			Diplome dip = new DiplomeFactory().findDiplome("BAC PRO",
					"TECHNICIEN CONSTRUCTEUR BOIS");

			Referentiel ref = new ReferentielFactory().findReferentiel(dip);

			Rap rap = ref.getRap();

			assertTrue(rap.getReferentiel().getDiplome().getType().equals("TECHNICIEN CONSTRUCTEUR BOIS"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testgetFonctions() {
		try {

			Rap rap = new Rap(new Referentiel(new Diplome("BAC PRO", "CB")));
			new Fonction(rap, "String1");
			new Fonction(rap, "String2");
			new Fonction(rap, "String3");

			assertEquals(rap.getFonctions().size(), 3);

			for (int i = 0; i < rap.getFonctions().size(); i++) {
				List<Fonction> l = rap.getFonctions();
				Fonction f = l.get(i);
				switch (i) {
				case 0:
					assertEquals(f.getIntitule(), "STRING1");
					break;
				case 1:
					assertEquals(f.getIntitule(), "STRING2");
					break;
				case 2:
					assertEquals(f.getIntitule(), "STRING3");
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

			Rap rap = new Rap(ref);
			Rap rap1 = new Rap(ref1);

			assertTrue(rap.getReferentiel().getDiplome().getDiplome().equals("BAC PRO"));
			assertTrue(rap1.getReferentiel().getDiplome().getDiplome().equals("CAP"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
