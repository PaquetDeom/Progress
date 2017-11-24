package fr.paquet.referentiel.test;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;

import fr.paquet.commun.Diplome;
import fr.paquet.commun.DiplomeFactory;
import fr.paquet.dataBase.Connect;
import fr.paquet.referentiel.Capacite;
import fr.paquet.referentiel.Rap;
import fr.paquet.referentiel.Referentiel;
import fr.paquet.referentiel.ReferentielFactory;
import fr.paquet.referentiel.Savoir;

public class TestClassReferentiel {

	@Test
	public void testgetDb() {
		try {

			Diplome dip = new DiplomeFactory().findDiplome("BAC PRO",
					"TECHNICIEN CONSTRUCTEUR BOIS");

			Referentiel ref = new ReferentielFactory().findReferentiel(dip);
			List<Referentiel> refs = new ReferentielFactory().findReferentiels();

			assertTrue(ref.getDiplome().getDiplome().equals("BAC PRO"));
			assertTrue(ref.getDiplome().getType().equals("TECHNICIEN CONSTRUCTEUR BOIS"));
			assertTrue(refs.get(0).getDiplome().getType().equals("TECHNICIEN CONSTRUCTEUR BOIS"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testgetCapacite() {
		try {
			Referentiel ref = new Referentiel(new Diplome("MASTER", "Y SE PREND POUR QUI LE DEVELOPPEUR"));

			new Capacite(ref, "C1", "String1");
			new Capacite(ref, "C2", "String2");
			new Capacite(ref, "C3", "String3");

			assertEquals(ref.getCapacites().size(), 3);

			for (int i = 0; i < ref.getCapacites().size(); i++) {
				List<Capacite> lcap = ref.getCapacites();
				Capacite c = lcap.get(i);
				switch (i) {
				case 0:
					assertTrue(c.getCode().equals("C1"));
					break;
				case 1:
					assertTrue(c.getCode().equals("C2"));
					break;
				case 2:
					assertTrue(c.getCode().equals("C3"));
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testgetDiplome() {
		try {

			Referentiel ref = new Referentiel(new Diplome("         maSTER", "Y SE PREND POUR QUI LE DEVELOPPEUR"));
			Referentiel ref1 = new Referentiel(new Diplome("DOCTORat       ", "LA J'CROIS QU'IL A PETE UN CABLE"));

			assertTrue(ref.getDiplome().getDiplome().equals("MASTER"));
			assertTrue(ref1.getDiplome().getDiplome().equals("DOCTORAT"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testgetIntitule() {
		try {

			Referentiel ref = new Referentiel(new Diplome("MASTER", "         Y SE prend POUR QUI LE DEVeLoPPEUR"));
			Referentiel ref1 = new Referentiel(new Diplome("DOCTORAT", "           LA J'CroIS QU'IL A PETE UN CABLE"));

			assertTrue(ref.getDiplome().getType().equals("Y SE PREND POUR QUI LE DEVELOPPEUR"));
			assertTrue(ref1.getDiplome().getType().equals("LA J'CROIS QU'IL A PETE UN CABLE"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testgetRap() {
		try {

			Referentiel ref = new Referentiel(new Diplome("MASTER", "Y SE PREND POUR QUI LE DEVELOPPEUR"));
			Rap rap = new Rap(ref);

			assertTrue(ref.getRap() == rap);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testgetSavoirs() {
		try {

			Referentiel ref = new Referentiel(new Diplome("BAC PRO", "CB"));

			new Savoir(ref, "S1", "L'entreprise et son environement");
			new Savoir(ref, "S2", "La communication technique");
			new Savoir(ref, "S3", "Le confort de l'habitat");
			new Savoir(ref, "S4", "La m�canique et la r�sistance des mat�riaux");

			assertEquals(ref.getSavoirs().size(), 4);

			for (int i = 0; i < ref.getSavoirs().size(); i++) {
				List<Savoir> lsav = ref.getSavoirs();
				Savoir s = lsav.get(i);
				switch (i) {
				case 0:
					assertTrue(s.getCode().equals("S1"));
					break;
				case 1:
					assertTrue(s.getCode().equals("S2"));
					break;
				case 2:
					assertTrue(s.getCode().equals("S3"));
					break;
				case 3:
					assertTrue(s.getCode().equals("S4"));
					break;

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
