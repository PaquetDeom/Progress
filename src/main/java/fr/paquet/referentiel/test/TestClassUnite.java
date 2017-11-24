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

public class TestClassUnite {

	@Test
	public void testgetDb() {
		try {

			Diplome dip = new DiplomeFactory().findDiplome("BAC PRO",
					"TECHNICIEN CONSTRUCTEUR BOIS");

			Referentiel ref = new ReferentielFactory().findReferentiel(dip);

			assertTrue(ref.getCapacites().get(0).getCompetences().get(0).getUnite().getReferentiel().getDiplome()
					.getType().equals("TECHNICIEN CONSTRUCTEUR BOIS"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testgetCode() {
		try {
			Unite unt1 = new Unite(11, "ANALYSE");

			assertTrue(unt1.getCode().equals("U.11"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testgetCompetences() {
		try {
			Referentiel ref = new Referentiel(new Diplome("BAC PRO", "CB"));

			Unite U_11 = new Unite(11, "ANALYSE TECHNIQUE D'UN OUVRAGE");
			Unite U_2 = new Unite(2, "PREPARATION D'UNE FABRICATION ET D'UNE MISE EN OEUVRE SUR CHANTIER");

			new Competence(new Capacite(ref, "C1", "S'INFORMER ANALYSER"), U_11, 1,
					"D�coder et analyser les donn�es de d�finition");
			new Competence(new Capacite(ref, "C2", "TRAITER DECIDER PREPARER"), U_11, 1,
					"Choisir, adapter et justifier des solutions techniques");
			new Competence(new Capacite(ref, "C3", "FABRIQUER"), U_2, 1,
					"Organiser et mettre en s�curit� les postes de travail");
			new Competence(new Capacite(ref, "C4", "METTRE EN OEUVRE SUR CHANTIER"), U_2, 1,
					"Organiser et mettre en s�curit� la zone d'intervention");
			new Competence(new Capacite(ref, "C5", "MAINTENIR ET REMETTRE EN ETAT"), U_2, 1,
					"Assurer la maintenance de premier niveau des machine");

			assertEquals(U_11.getCompetences().size(), 2);
			assertEquals(U_2.getCompetences().size(), 3);

			for (int i = 0; i < U_2.getCompetences().size(); i++) {
				List<Competence> l = U_2.getCompetences();
				Competence comp = l.get(i);
				switch (i) {
				case 0:
					assertEquals(comp.getCode(), "C3.1");
					break;
				case 1:
					assertEquals(comp.getCode(), "C4.1");
					break;
				case 2:
					assertEquals(comp.getCode(), "C5.1");
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
			Unite unt1 = new Unite(11, "   ANAlySE");

			assertTrue(unt1.getIntitule().equals("ANALYSE"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
