package fr.paquet.ihm.sequence;

import java.util.List;

import com.vaadin.ui.*;
import fr.paquet.referentiel.Competence;
import fr.paquet.referentiel.CompetenceIntermediaire;
import fr.paquet.referentiel.Condition;
import fr.paquet.referentiel.CritereEvaluation;

@SuppressWarnings("serial")
public class CompetenceLayout extends VerticalLayout {

	private boolean testCond = false;
	private boolean testCrit = false;

	public CompetenceLayout(Competence comp, List<CompetenceIntermediaire> compInts) {
		super();
		addStyleName("competence");
		setCaption(comp.getCode() + " : " + comp.getIntitule());
		addCITitres();

		addComponent(listCILayout);
		for (CompetenceIntermediaire ci : compInts) {
			add(ci);
		}

	}

	private void addCITitres() {
		HorizontalLayout h1Layout = new HorizontalLayout();
		addStyleName("TitreCI");
		h1Layout.setSizeFull();
		h1Layout.setSpacing(true);
		h1Layout.addComponent(new Label("Competences intermediaires"));
		h1Layout.addComponent(new Label("Conditions"));
		h1Layout.addComponent(new Label("Criteres évaluation"));
		addComponent(h1Layout);
	}

	/**
	 * renvoie le layout d'affichage d'un CI avec UN CR Général
	 * 
	 * @return
	 */
	Layout listCILayout = new VerticalLayout();
	Layout actualCILayout = new VerticalLayout();
	CompetenceIntermediaire ciOld = null;

	private void add(CompetenceIntermediaire ci) {
		// si on insere le premier ou que la condition est spé ou que le critere
		// est spé
		// ou que le ci precedent à une condition spé ou que le ci precedent à
		// un cCR spe
		// on créé une nouvelle ligne
		if (ciOld == null || !ci.isConditionsGenerale() || !ci.isCriteresGeneraux() || !ciOld.isConditionsGenerale()
				|| !ciOld.isCriteresGeneraux()) {

			ciOld = ci;
			// création du layout
			HorizontalLayout layout = new HorizontalLayout();
			layout.addStyleName("ligneCI");
			layout.setSizeFull();
			layout.setSpacing(true);
			// ajout du libellé de la competence intermediaire
			actualCILayout = new VerticalLayout();
			actualCILayout.addStyleName("paveCI");
			actualCILayout.addComponent(new Label(ci.getCode() + ":" + ci.getIntitule()));
			layout.addComponent(actualCILayout);

			// ajout des condition de la competence intermediaire
			VerticalLayout layoutConditions = new VerticalLayout();
			actualCILayout.addStyleName("paveCD");
			if (!ci.isConditionsGenerale() || testCond == false) {
				for (Condition cd : ci.getConditions()) {
					layoutConditions.addComponent(new Label(cd.getCondition()));
				}
				testCond = true;
			}
			layout.addComponent(layoutConditions);

			// ajout du layout critere d'evaluation
			VerticalLayout layoutCEval = new VerticalLayout();
			actualCILayout.addStyleName("paveCR");
			if (!ci.isCriteresGeneraux() || testCrit == false) {
				for (CritereEvaluation cr : ci.getCritereEvaluations()) {
					layoutCEval.addComponent(new Label(cr.getCritere()));
				}
				testCrit = true;
			}
			layout.addComponent(layoutCEval);

			// ajout de la nouvelle ligne a la liste des lignes
			listCILayout.addComponent(layout);
		} else
			actualCILayout.addComponent(new Label(ci.getCode() + ":" + ci.getIntitule()));
	}

}
