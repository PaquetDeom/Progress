package fr.paquet.ihm.sequence;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import fr.paquet.ihm.AlertWindow;
import fr.paquet.referentiel.Competence;
import fr.paquet.referentiel.CompetenceIntermediaire;
import fr.paquet.referentiel.Savoir;
import fr.paquet.referentiel.SavoirAssocie;
import fr.paquet.referentiel.Tache;
import fr.paquet.sequence.Sequence;

@SuppressWarnings("serial")
public class NewSeqView extends Window {

	Sequence sequence = null;
	Accordion accordion = null;
	TextArea typeIntro = new TextArea();
	RichTextArea introduction = new RichTextArea();
	RichTextArea intentionPeda = new RichTextArea();
	TextArea prerequis = new TextArea();
	DateField dateDebut = null;
	DateField dateFin = null;
	RichTextArea descDecouverte = null;
	private boolean b1Click = false;

	public NewSeqView() {
		super();

	}

	public NewSeqView(Sequence sequence) {

		this.sequence = sequence;
		buildWindow();

	}

	/**
	 * Construction de la vue Fenetre<br/>
	 */
	public void buildWindow() {

		if (sequence == null)
			setCaption("Construction d'une séquence sans referentiel");
		setCaption("Construction de séquence à partir d'une progression");
		setSizeFull();
		center();
		setModal(false);
		final FormLayout content = new FormLayout();
		if (sequence != null)
			content.addComponent(getDetailProg());

		content.setMargin(true);
		setContent(content);
		show();

	}

	private void setB1Click(boolean b1) {
		this.b1Click = b1;
	}

	private boolean isB1Click() {
		return b1Click;
	}

	/**
	 * 
	 * @return L'ecran de fabrication d'une sequence à partir d'une
	 *         progression<br/>
	 */
	private Component getDetailProg() {

		Panel panel = new Panel();

		panel.setCaption("Progress - " + this.sequence.getTitre());

		panel.addStyleName("sequence");
		VerticalLayout panelContent = new VerticalLayout();
		panel.setContent(panelContent);

		panelContent.addComponent(getEntete());

		panelContent.addComponent(getZoneDuree());

		if (isB1Click() == true) {

			setB1Click(false);

			accordion = null;
			accordion = new Accordion();
			panelContent.addComponent(accordion);

			VerticalLayout tab = new VerticalLayout();
			tab.addComponent(getZoneIntentionPeda());
			tab.addComponent(getZonePrerequis());
			accordion.addTab(tab, "Séquence");

			tab = new VerticalLayout();
			tab.addComponent(getZoneIntroduction());
			accordion.addTab(tab, "Introduction");

			tab = new VerticalLayout();
			tab.addComponent(getZoneDecouverte());
			tab.addComponent(getDecouverteReferentiel());
			accordion.addTab(tab, "Decouverte");

			tab = new VerticalLayout();
			// tab.addComponent(getZoneApplication());
			tab.addComponent(getApplicationReferentiel());
			accordion.addTab(tab, "Application");

			tab = new VerticalLayout();
			// tab.addComponent(getZoneApprofondissement());
			tab.addComponent(getApprofondissementReferentiel());
			accordion.addTab(tab, "Approfondissement");

			tab = new VerticalLayout();
			// tab.addComponent(getZoneValidation());
			tab.addComponent(getValidationReferentiel());
			accordion.addTab(tab, "Validation");

		} else
			panelContent.addComponent(new Label("-- CHOISIR DATE DE DEBUT ET DE FIN --"));

		return panel;
	}

	/**
	 * 
	 * @return L'entete de l'ecran qui comprend :<br/>
	 *         La zone de saisi du titre<br/>
	 *         Le boutton pour afficher la fiche sequence<br/>
	 *         Le boutton pour afficher les fiches seances<br/>
	 */
	private Component getEntete() {

		VerticalLayout layout = new VerticalLayout();
		HorizontalLayout layout1 = new HorizontalLayout();
		layout.addComponent(layout1);

		Button b4 = new Button("Fiche Séquence");
		Button b5 = new Button("Fiches Séances");

		layout1.addComponent(b4);
		layout1.addComponent(b5);

		return layout;
	}

	/**
	 * 
	 * @return Les competences, competences intermediaire, conditions, criteres
	 *         d'eval de la phase de validation<br/>
	 */
	private Component getValidationReferentiel() {

		Panel panel = new Panel();
		panel.setSizeFull();
		panel.addStyleName("validation");

		VerticalLayout layout0 = new VerticalLayout();
		layout0.setSizeFull();
		layout0.setSpacing(true);

		Button b1 = new Button("Créer une Evaluation");

		layout0.addComponent(b1);
		panel.setContent(layout0);

		for (int i = 0; i < sequence.getPhase().getValidation().getCompetences().size(); i++) {

			List<CompetenceIntermediaire> compInts = new ArrayList<CompetenceIntermediaire>();
			Competence comp = sequence.getPhase().getValidation().getCompetences().get(i);

			for (int n = 0; n < sequence.getPhase().getValidation().getCompetenceIntermediaires().size(); n++) {
				CompetenceIntermediaire compInt = sequence.getPhase().getValidation().getCompetenceIntermediaires()
						.get(n);
				if (comp == compInt.getCompetence())
					compInts.add(compInt);
				else
					compInt = null;
			}
			layout0.addComponent(new CompetenceLayout(comp, compInts));
		}
		return panel;
	}

	/**
	 * 
	 * @return Les competences, competences intermediaire, conditions, criteres
	 *         d'eval de la phase d'approfondissement<br/>
	 */
	private Component getApprofondissementReferentiel() {

		HorizontalLayout layout0 = new HorizontalLayout();
		layout0.setSizeFull();
		layout0.setSpacing(true);
		VerticalLayout layout = new VerticalLayout();
		VerticalLayout layout1 = new VerticalLayout();

		layout0.addComponent(layout);
		layout0.addComponent(layout1);

		layout.setCaption("Référentiel de certification :");
		for (int i = 0; i < sequence.getPhase().getApprofondissement().getCompetences().size(); i++) {
			Competence comp = sequence.getPhase().getApprofondissement().getCompetences().get(i);
			layout.addComponent(new Label(comp.getCode() + " :" + comp.getIntitule()));
			for (int n = 0; n < sequence.getPhase().getApprofondissement().getCompetenceIntermediaires().size(); n++) {
				CompetenceIntermediaire compInt = sequence.getPhase().getApprofondissement()
						.getCompetenceIntermediaires().get(n);
				if (compInt.getCompetence() == comp)
					layout.addComponent(new Label(compInt.getCode() + " : " + compInt.getIntitule()));
				else
					compInt = null;
			}

		}

		List<Tache> data = IntStream.range(0, sequence.getPhase().getApprofondissement().getTachePossibles().size())
				.mapToObj(i -> sequence.getPhase().getApprofondissement().getTachePossibles().get(i))
				.collect(Collectors.toList());

		final TwinColSelect select = new TwinColSelect("Selectionner les taches à mettre en oeuvre", data);

		select.setRows(sequence.getPhase().getApplication().getTachePossibles().size());
		select.setLeftColumnCaption("Tâches possibles");
		select.setRightColumnCaption("Tâches sélectionnées");
		select.setSizeFull();

		layout1.addComponent(select);

		return layout0;
	}

	/**
	 * 
	 * @return Les competences, competences intermediaire, conditions, criteres
	 *         d'eval de la phase d'application<br/>
	 */
	private Component getApplicationReferentiel() {

		HorizontalLayout layout = new HorizontalLayout();
		layout.setSizeFull();
		layout.setSpacing(true);
		VerticalLayout layout1 = new VerticalLayout();
		VerticalLayout layout2 = new VerticalLayout();

		layout1.setCaption("Compétences - Compétences intermédiaires");

		layout.addComponent(layout1);
		layout.addComponent(layout2);

		for (int i = 0; i < sequence.getPhase().getApplication().getCompetences().size(); i++) {
			Competence comp = sequence.getPhase().getApplication().getCompetences().get(i);
			layout1.addComponent(new Label(comp.getCode() + " : " + comp.getIntitule()));
			for (int n = 0; n < sequence.getPhase().getApplication().getCompInts().size(); n++) {
				CompetenceIntermediaire compInt = sequence.getPhase().getApplication().getCompInts().get(n);
				if (compInt.getCompetence() == comp)
					layout1.addComponent(new Label(compInt.getCode() + " : " + compInt.getIntitule()));
				else
					compInt = null;
			}
		}

		List<Tache> data = IntStream.range(0, sequence.getPhase().getApplication().getTachePossibles().size())
				.mapToObj(i -> sequence.getPhase().getApplication().getTachePossibles().get(i))
				.collect(Collectors.toList());

		final TwinColSelect select = new TwinColSelect("Selectionner les taches à mettre en oeuvre", data);

		select.setRows(sequence.getPhase().getApplication().getTachePossibles().size());
		select.setLeftColumnCaption("Tâches possibles");
		select.setRightColumnCaption("Tâches sélectionnées");
		select.setSizeFull();

		layout2.addComponent(select);
		return layout;
	}

	/**
	 * 
	 * @return La description de la zone de decouverte<br/>
	 */
	private Component getZoneDecouverte() {

		Panel p = new Panel();
		p.setCaption("Description :");
		if (descDecouverte == null)
			descDecouverte = new RichTextArea();

		descDecouverte.setSizeFull();
		p.setContent(descDecouverte);

		return p;
	}

	/**
	 * 
	 * @return Les savoirs de la phase de decouverte<br/>
	 */
	private Component getDecouverteReferentiel() {

		HorizontalLayout layout0 = new HorizontalLayout();
		layout0.setSizeFull();
		layout0.setSpacing(true);
		VerticalLayout layout = new VerticalLayout();
		VerticalLayout layout1 = new VerticalLayout();

		layout0.addComponent(layout);
		layout0.addComponent(layout1);

		for (int i = 0; i < sequence.getPhase().getDecouverte().getSavoirPossibles().size(); i++) {
			Savoir sav = sequence.getPhase().getDecouverte().getSavoirPossibles().get(i);
			layout.addComponent(new Label(sav.getCode() + " : " + sav.getIntitule()));

		}

		List<SavoirAssocie> data = IntStream
				.range(0, sequence.getPhase().getDecouverte().getSavoirAssociePossibles().size())
				.mapToObj(i -> sequence.getPhase().getDecouverte().getSavoirAssociePossibles().get(i))
				.collect(Collectors.toList());

		final TwinColSelect select = new TwinColSelect("Selectionner les savoirs associes à mettre en oeuvre", data);

		select.setRows(sequence.getPhase().getDecouverte().getSavoirAssociePossibles().size());
		select.setLeftColumnCaption("Savoirs associes possibles");
		select.setRightColumnCaption("Savoirs associes sélectionnés");
		select.setSizeFull();

		layout1.addComponent(select);

		return layout0;

	}

	/**
	 * 
	 * @return Le type et la description de la phase introduction<br/>
	 */
	private Component getZoneIntroduction() {

		VerticalLayout layout = new VerticalLayout();
		typeIntro.setSizeFull();
		introduction.setSizeFull();

		Panel p = new Panel();
		p.setCaption("Type :");
		p.setContent(typeIntro);

		Panel p1 = new Panel();
		p1.setCaption("Introduction :");
		p1.setContent(introduction);

		layout.addComponent(p);
		layout.addComponent(p1);

		return layout;

	}

	/**
	 * 
	 * @return La description de l'intention pedagogique<br/>
	 */
	private Component getZoneIntentionPeda() {

		Panel p = new Panel();
		p.setCaption("Intention Pédagogique :");
		intentionPeda.setSizeFull();
		p.setContent(intentionPeda);

		return p;

	}

	/**
	 * 
	 * @return La description des prerequis<br/>
	 */
	private Component getZonePrerequis() {

		Panel p = new Panel();
		p.setCaption("Prérequis :");
		prerequis.setSizeFull();
		p.setContent(prerequis);
		return p;
	}

	/**
	 * 
	 * @return les dates de debut et de fin de la sequence<br/>
	 */
	private Component getZoneDuree() {
		CssLayout layout = new CssLayout();
		layout.addComponent(getDateDebut());
		layout.addComponent(getDateFin());
		layout.addComponent(getNombreDeSeance());
		Button b1 = new Button("Exécuter");

		b1.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				int numDebut = sequence.getProgression().getNumSceance(getDateDebut().getValue());
				int numFin = sequence.getProgression().getNumSceance(getDateFin().getValue());
				try {

					sequence.setDebut(numDebut);
					sequence.setFin(numFin);
					setB1Click(true);
					buildWindow();

				} catch (Exception e) {

					new SequenceView().getSequenceViewPanelContent().getUI().getUI()
							.addWindow(new AlertWindow("Erreur", e.getMessage()));

				}
			}
		});

		layout.addComponent(b1);

		return layout;
	}

	Label nombreDeSeance = new Label();

	/**
	 * 
	 * @return Le nombre de seances<br/>
	 */
	private Component getNombreDeSeance() {

		HorizontalLayout layout = new HorizontalLayout();
		layout.setCaption("Nombre de Séances : ");
		if (isB1Click() == true) {
			nombreDeSeance.setValue(sequence.getNbSeance());
			layout.addComponent(nombreDeSeance);
		} else
			layout.addComponent(new Label("-- --"));

		return layout;
	}

	private DateField getDateDebut() {
		if (dateDebut == null) {
			dateDebut = new DateField();
			dateDebut.setCaption("Date de début");
		}
		return dateDebut;
	}

	private DateField getDateFin() {
		if (dateFin == null) {
			dateFin = new DateField();
			dateFin.setCaption("Date de fin");
		}
		return dateFin;
	}

	public Window show() {
		setVisible(true);
		return this;
	}

}
