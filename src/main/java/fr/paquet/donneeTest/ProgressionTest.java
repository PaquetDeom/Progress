package fr.paquet.donneeTest;

import fr.paquet.progression.ConfigCompInt;
import fr.paquet.progression.Progression;
import fr.paquet.referentiel.Capacite;
import fr.paquet.referentiel.Competence;
import fr.paquet.referentiel.CompetenceIntermediaire;

public class ProgressionTest {

	private static Progression prog = null;

	public static Progression getProgression() {

		try {
			if (prog == null) {

				prog = new Progression(ReferentielTest.getReferentiel(), "PROGRESSION 1");

				Capacite cap1 = ReferentielTest.getReferentiel().getCapacites().get(0);
				Capacite cap2 = ReferentielTest.getReferentiel().getCapacites().get(1);
				Capacite cap3 = ReferentielTest.getReferentiel().getCapacites().get(2);

				Competence comp1 = cap1.getCompetences().get(0);
				Competence comp2 = cap2.getCompetences().get(0);
				Competence comp3 = cap3.getCompetences().get(0);

				CompetenceIntermediaire compInt1 = comp1.getCompetencesIntermediaires().get(0);
				CompetenceIntermediaire compInt2 = comp2.getCompetencesIntermediaires().get(0);
				CompetenceIntermediaire compInt3 = comp3.getCompetencesIntermediaires().get(0);

				new ConfigCompInt(prog, compInt1, 1, 3);
				new ConfigCompInt(prog, compInt2, 2, 5);
				new ConfigCompInt(prog, compInt3, 6, 10);

			}

			return prog;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}
}
