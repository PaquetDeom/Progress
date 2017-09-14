package fr.paquet.donneeTest;

import java.util.ArrayList;
import java.util.List;

import fr.paquet.commun.Diplome;
import fr.paquet.referentiel.Activite;
import fr.paquet.referentiel.Capacite;
import fr.paquet.referentiel.Competence;
import fr.paquet.referentiel.CompetenceIntermediaire;
import fr.paquet.referentiel.Condition;
import fr.paquet.referentiel.CritereEvaluation;
import fr.paquet.referentiel.Fonction;
import fr.paquet.referentiel.Rap;
import fr.paquet.referentiel.Referentiel;
import fr.paquet.referentiel.Savoir;
import fr.paquet.referentiel.SavoirAssocie;
import fr.paquet.referentiel.SousSavoirAssocie;
import fr.paquet.referentiel.Tache;
import fr.paquet.referentiel.Unite;

public class ReferentielTest {

	private static Referentiel ref = null;

	public static Referentiel getReferentiel() {

		try {

			if (ref == null) {

				// Creation du referentiel
				ref = new Referentiel(new Diplome("BAC PRO", "CONSTRUCTEUR BOIS"));

				// Creation du Rap
				Rap rap = new Rap(ref);

				// Creation de fonction
				Fonction fonc = new Fonction(rap, "FONCTION 1");

				// Creation d'activite
				List<Activite> acts = new ArrayList<Activite>();
				acts.add(new Activite(fonc, "ACTIVITE 1"));
				acts.add(new Activite(fonc, "ACTIVITE 2"));
				acts.add(new Activite(fonc, "ACTIVITE 3"));

				// Creation des taches
				Tache tac1 = new Tache(acts.get(0), "T1", "TACHE 1", 1, "A");
				Tache tac2 = new Tache(acts.get(1), "T2", "TACHE 2", 1, "AB");
				Tache tac3 = new Tache(acts.get(2), "T3", "TACHE 3", 1, "B");
				Tache tac4 = new Tache(acts.get(2), "T4", "TACHE 4", 1, "C");
				Tache tac5 = new Tache(acts.get(2), "T5", "TACHE 5", 1, "BC");

				// Creation des unites diplomantes
				List<Unite> unts = new ArrayList<Unite>();
				unts.add(new Unite(1, "UNITE 1"));
				unts.add(new Unite(2, "UNITE 2"));
				unts.add(new Unite(3, "UNITE 3"));

				// Creation des Capacite
				List<Capacite> caps = new ArrayList<Capacite>();
				caps.add(new Capacite(getReferentiel(), "C1", "CAPACITE 1"));
				caps.add(new Capacite(getReferentiel(), "C2", "CAPACITE 2"));
				caps.add(new Capacite(getReferentiel(), "C3", "CAPACITE 3"));

				// Creation des competences
				List<Competence> comps = new ArrayList<Competence>();
				Competence comp1 = new Competence(caps.get(0), unts.get(0), 1, "COMPETNCE 1");
				Competence comp2 = new Competence(caps.get(1), unts.get(1), 1, "COMPETNCE 2");
				Competence comp3 = new Competence(caps.get(2), unts.get(1), 1, "COMPETNCE 3");
				comps.add(comp1);
				comps.add(comp2);
				comps.add(comp3);

				// Creation des competences intermediaires
				List<CompetenceIntermediaire> compInts = new ArrayList<CompetenceIntermediaire>();
				CompetenceIntermediaire compInt1 = new CompetenceIntermediaire(comp1, 1, "COMPINT1");
				CompetenceIntermediaire compInt2 = new CompetenceIntermediaire(comp2, 1, "COMPINT2");
				CompetenceIntermediaire compInt3 = new CompetenceIntermediaire(comp3, 1, "COMPINT3");
				compInts.add(compInt1);
				compInts.add(compInt2);
				compInts.add(compInt3);

				// Creation des Conditions
				List<Condition> conds = new ArrayList<Condition>();
				conds.add(new Condition("CONDITION 1"));
				conds.add(new Condition("CONDITION 2"));
				conds.add(new Condition("CONDITION 3"));
				conds.add(new Condition("CONDITION 4"));
				conds.add(new Condition("CONDITION 5"));

				// Creation des Conditions
				List<CritereEvaluation> crits = new ArrayList<CritereEvaluation>();
				crits.add(new CritereEvaluation("CRITERE 1"));
				crits.add(new CritereEvaluation("CRITERE 2"));
				crits.add(new CritereEvaluation("CRITERE 3"));
				crits.add(new CritereEvaluation("CRITERE 4"));
				crits.add(new CritereEvaluation("CRITERE 5"));

				// Association des Conditions Aux competences et/ou competence
				// Intermediaire
				comp1.addCondition(conds.get(0));
				comp2.addCondition(conds.get(1));
				comp3.addCondition(conds.get(2));
				compInt2.addCondition(conds.get(3));
				compInt3.addCondition(conds.get(4));

				// Association des Critere d'Evaluation aux competences et/ou
				// competence intermediaire
				comp1.addCritereEvaluation(crits.get(0));
				comp2.addCritereEvaluation(crits.get(1));
				comp3.addCritereEvaluation(crits.get(2));
				compInt2.addCritereEvaluation(crits.get(3));
				compInt3.addCritereEvaluation(crits.get(4));

				// Creation des Savoirs
				Savoir s1 = new Savoir(ref, "S1", "SAVOIR 1");
				Savoir s2 = new Savoir(ref, "S2", "SAVOIR 2");

				// Creation des savoirs associes
				List<SavoirAssocie> savasss = new ArrayList<SavoirAssocie>();
				savasss.add(new SavoirAssocie(s1, 1, "S-SAVOIR 1"));
				savasss.add(new SavoirAssocie(s1, 2, "S-SAVOIR 2"));
				savasss.add(new SavoirAssocie(s2, 1, "S-SAVOIR 3"));
				savasss.add(new SavoirAssocie(s2, 1, "S-SAVOIR 4"));

				// Creation des sous savoirs associes
				new SousSavoirAssocie(savasss.get(0), 1, "S-S-SAVOIR 1", 1);
				new SousSavoirAssocie(savasss.get(1), 1, "S-S-SAVOIR 2", 2);
				new SousSavoirAssocie(savasss.get(2), 1, "S-S-SAVOIR 3", 3);
				new SousSavoirAssocie(savasss.get(3), 1, "S-S-SAVOIR 4", 1);

				// Association des savoirs aux competences
				comp1.addSavoir(s1);
				comp2.addSavoir(s2);
				comp3.addSavoir(s1);
				comp3.addSavoir(s2);

				// Association des taches aux competences
				comp1.addTache(tac1);
				comp2.addTache(tac2);
				comp3.addTache(tac3);
				comp3.addTache(tac4);
				comp3.addTache(tac5);

			}

			return ref;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
