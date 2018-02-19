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
				Capacite cap4 = ReferentielTest.getReferentiel().getCapacites().get(3);
				Capacite cap5 = ReferentielTest.getReferentiel().getCapacites().get(4);

				Competence comp14 = cap1.getCompetences().get(3);
				Competence comp41 = cap4.getCompetences().get(0);
				Competence comp53 = cap5.getCompetences().get(2);

				CompetenceIntermediaire compInt1 = comp14.getCompetencesIntermediaires().get(0);
				CompetenceIntermediaire compInt2 = comp41.getCompetencesIntermediaires().get(0);
				CompetenceIntermediaire compInt3 = comp53.getCompetencesIntermediaires().get(0);

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
