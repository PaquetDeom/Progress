package fr.paquet.donneeTest;

import java.util.ArrayList;
import java.util.List;

import fr.paquet.commun.Diplome;
import fr.paquet.commun.DiplomeFactory;
import fr.paquet.dataBase.Connect;
import fr.paquet.progression.ConfigCompInt;
import fr.paquet.progression.Progression;
import fr.paquet.referentiel.Capacite;
import fr.paquet.referentiel.Competence;
import fr.paquet.referentiel.CompetenceIntermediaire;
import fr.paquet.referentiel.Referentiel;
import fr.paquet.referentiel.ReferentielFactory;
import fr.paquet.sequence.Sequence;

public class DonneeDbTest {

	/**
	 * Donnee de test pour le devellopement<br/>
	 */
	private static Progression prog = null;

	public DonneeDbTest() {
		super();
	}

	public static Progression initTestProgressionData() throws Exception {

		Diplome dip = new DiplomeFactory().findDiplome("BAC PRO",
				"TECHNICIEN CONSTRUCTEUR BOIS");

		Referentiel ref = new ReferentielFactory().findReferentiel(dip);
		prog = new Progression(ref, "essai1");

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

		return prog;
	}

	public static Sequence initTestSequenceData() throws Exception {

		return new Sequence(prog, "Sequence 1");

	}

}
