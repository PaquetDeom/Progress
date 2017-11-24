package fr.paquet.ihm.sequence;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Resource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import fr.paquet.dataBase.Connect;
import fr.paquet.framework.ui.ProgView;
import fr.paquet.ihm.AlertListener;
import fr.paquet.ihm.AlertWindow;
import fr.paquet.progression.Progression;
import fr.paquet.progression.ProgressionFactory;
import fr.paquet.referentiel.Referentiel;
import fr.paquet.referentiel.ReferentielFactory;
import fr.paquet.sequence.Sequence;
import fr.paquet.sequence.SequenceFactory;

@SuppressWarnings("serial")
public class SequenceView extends AbsoluteLayout implements ProgView, AlertListener {

	private VerticalLayout sequenceViewPanelContent = null;
	private boolean b1Click = false;
	private String titre = null;
	private Referentiel referentiel = null;
	private List<Referentiel> referentiels = null;
	private Progression progression = null;
	private List<Progression> progressions = null;
	private Sequence sequence = null;
	private TextField titreField = null;

	private void buildPanelContent() {

		sequenceViewPanelContent = new VerticalLayout();

	}

	/**
	 * 
	 * @return Le layout pricipal de SequenceView<br/>
	 */

	public VerticalLayout getSequenceViewPanelContent() {

		if (sequenceViewPanelContent == null)
			buildPanelContent();

		return sequenceViewPanelContent;
	}

	/**
	 * @author Nathanaël
	 * 
	 *         Ecran d'accueil des Sequences<br/>
	 */
	@Override
	public void enter(ViewChangeEvent event) {
		
	}

	@Override
	public String getName() {
		return "Séquence";
	}

	@Override
	public String getCaption() {
		return "Séquence";
	}

	@Override
	public Resource getIcon() {
		return null;
	}

	public SequenceView() {
		super();
		buildView();
	}

	/**
	 * Construction de l'ecran<br/>
	 */
	public void buildView() {

		removeAllComponents();
		addStyleName("sequenceView");

		setSizeFull();

		VerticalLayout layout = new VerticalLayout();
		layout.addComponent(getDetail());
		addComponent(layout);

	}

	/**
	 * 
	 * @return contenu de la fenetre d'accueil de sequence<br/>
	 */

	public Component getDetail() {

		Panel pan = new Panel();
		VerticalLayout layout = getSequenceViewPanelContent();
		pan.setCaption("Accueil - Sequences");

		pan.setContent(layout);

		HorizontalLayout layoutCreation = new HorizontalLayout();
		layoutCreation.setCaption("Creation d'une sequence");

		Button b1 = new Button();
		b1.setCaption("Créer une nouvelle séquence");
		layoutCreation.addComponent(b1);
		b1.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {

				try {

					setB1Click(true);

					if (getReferentiels() != null || getReferentiels().isEmpty())
						getSequenceViewPanelContent().getUI().addWindow(new CreationWindow(SequenceView.this));

				} catch (Exception e) {

					getSequenceViewPanelContent().getUI().getUI()
							.addWindow(new AlertWindow("Erreur !!!", e.getMessage()).show());
					e.printStackTrace(System.err);
				}
			}

		});

		HorizontalLayout layoutConsultation = new HorizontalLayout();
		layoutConsultation.setCaption("Consultation de sequence");

		Button b2 = new Button();
		b2.setCaption("Consulter");

		b2.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				try {
					getSequenceViewPanelContent().getUI().getUI().addWindow(getConsultation());
				} catch (Exception e) {

					getSequenceViewPanelContent().getUI().getUI()
							.addWindow(new AlertWindow("Erreur !!!", e.getMessage()).show());

				}

			}
		});

		layoutConsultation.addComponent(b2);

		layout.addComponent(layoutCreation);
		layout.addComponent(layoutConsultation);

		return pan;
	}

	

	/**
	 * 
	 * @return La liste de selection d'une progression<br/>
	 * @throws Exception
	 *             si il n'y a pas de progression dans la base<br/>
	 *
	 */

	public Component getListProgression() throws Exception {

		IntStream.range(0, getProgressions().size()).mapToObj(i -> {
			try {
				return getProgressions().get(i);
			} catch (Exception e) {

				getSequenceViewPanelContent().getUI().getUI()
						.addWindow(new AlertWindow("Erreur !!!", e.getMessage()).show());

			}
			return null;
		}).collect(Collectors.toList());

		ListSelect choixProgression = new ListSelect("Selectionner une progression", getProgressions());
		choixProgression.setRows(1);
		choixProgression.select(getProgressions().get(0));
		choixProgression.setWidth(100.0f, Unit.PERCENTAGE);
		choixProgression.setMultiSelect(false);
		if (getProgressions() != null)
			setProgression(getProgressions().get(0));

		choixProgression.addValueChangeListener(new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {

				Progression prog = (Progression) event.getProperty().getValue();
				setProgression(prog);

			}
		});

		return choixProgression;
	}

	/**
	 * 
	 * @return La liste de sequence d'une progression donnee<br/>
	 * @throws Exception
	 *             La liste de sequence est vide<br/>
	 */
	private Component getListSequences() {

		if (getProgression() != null) {
			List<Sequence> seqs = new SequenceFactory()
					.findSequences(getProgression());

			if (seqs == null || seqs.isEmpty())
				return null;

			else
				IntStream.range(0, seqs.size()).mapToObj(i -> {
					try {
						return seqs.get(i);
					} catch (Exception e) {

						getSequenceViewPanelContent().getUI().getUI()
								.addWindow(new AlertWindow("Erreur !!!", e.getMessage()).show());

					}
					return null;
				}).collect(Collectors.toList());

			ListSelect choixsequence = new ListSelect("Selectionner une sequence", seqs);
			choixsequence.setRows(1);
			choixsequence.select(seqs.get(0));
			choixsequence.setWidth(100.0f, Unit.PERCENTAGE);
			choixsequence.setMultiSelect(false);
			setSequence(seqs.get(0));

			choixsequence.addValueChangeListener(new ValueChangeListener() {

				@Override
				public void valueChange(ValueChangeEvent event) {

					Sequence set01 = (Sequence) event.getProperty().getValue();

					if (set01 != null) {

						try {

							setSequence(set01);

						} catch (Exception e) {

							getSequenceViewPanelContent().getUI().getUI()
									.addWindow(new AlertWindow("Erreur !!!", e.getMessage()).show());
						}
					}

				}
			});

			return choixsequence;
		}

		return null;
	}

	private void setProgression(Progression prog) {
		this.progression = prog;
	}

	/**
	 * 
	 * @return Une progression<br/>
	 */
	public Progression getProgression() {
		return progression;
	}

	/**
	 * 
	 * @return La liste de selection d'un referentiel<br/>
	 */
	public Component getListReferentiel() throws Exception {

		if (getReferentiels() == null || getReferentiels().isEmpty())
			return null;

		IntStream.range(0, getReferentiels().size()).mapToObj(i -> {
			try {
				return getReferentiels().get(i);
			} catch (Exception e) {
				getSequenceViewPanelContent().getUI().getUI()
						.addWindow(new AlertWindow("Erreur !!!", e.getMessage()).show());
			}
			return null;
		}).collect(Collectors.toList());

		ListSelect choixReferentiel = new ListSelect("Selectionner un referentiel", getReferentiels());
		choixReferentiel.setRows(1);
		choixReferentiel.select(getReferentiels().get(0));
		choixReferentiel.setWidth(100.0f, Unit.PERCENTAGE);
		choixReferentiel.setMultiSelect(false);
		setReferentiel(getReferentiels().get(0));

		choixReferentiel.addValueChangeListener(new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {

				Referentiel ref = (Referentiel) event.getProperty().getValue();
				setReferentiel(ref);
			}

		});

		return choixReferentiel;

	}

	private void setReferentiel(Referentiel ref) {
		this.referentiel = ref;
	}

	/**
	 * 
	 * @return Un referentiel<br/>
	 */
	public Referentiel getReferentiel() {
		return referentiel;
	}

	/**
	 * 
	 * @return La fenetre de consultation<br/>
	 * @throws Exception
	 */
	private Window getConsultation() throws Exception {

		final Window window = new Window("Consutation de séquences");
		window.setSizeUndefined();
		window.setWidth(600.0f, Unit.PIXELS);
		window.center();
		window.setModal(true);
		final FormLayout content = new FormLayout();
		content.setMargin(true);
		HorizontalLayout hlayout = new HorizontalLayout();
		VerticalLayout layoutg = new VerticalLayout();
		VerticalLayout layoutd = new VerticalLayout();
		hlayout.addComponent(layoutg);
		hlayout.addComponent(layoutd);
		hlayout.setSpacing(true);
		layoutg.addComponent(getListReferentiel());
		layoutg.addComponent(getListProgression());
		if (getListSequences() != null)
			layoutg.addComponent(getListSequences());

		Button Ouvrir = new Button("Ouvrir");
		Ouvrir.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				

			}
		});

		Button Nouveau = new Button("Nouveau");
		Nouveau.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				try {

					window.close();
					getSequenceViewPanelContent().getUI().getUI().addWindow(new CreationWindow(SequenceView.this));

				} catch (Exception e) {

					getSequenceViewPanelContent().getUI().getUI()
							.addWindow(new AlertWindow("Erreur !!!", e.getMessage()).show());
				}

			}
		});

		Button Suppression = new Button("Suppression");
		Suppression.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				window.close();
				/*
				 * getSequenceViewPanelContent().getUI().getUI().addWindow(
				 * AvertissementScreen.
				 * getAvertissement("Etes vous sûr de vouloir supprimer",
				 * getRemove()));
				 */
				getSequenceViewPanelContent().getUI().getUI().addWindow(new AlertWindow("Alerte !!",
						"Etes vous sûr de vouloir supprimer", new String[] { "Oui", "Non" }, SequenceView.this));
			}
		});

		layoutd.addComponent(Nouveau);
		layoutd.addComponent(Ouvrir);
		if (getListSequences() != null)
			layoutd.addComponent(Suppression);
		content.addComponent(hlayout);
		window.setContent(content);
		window.setVisible(true);

		setB1Click(false);

		return window;

	}

	/**
	 * 
	 * @return true ou false pour le boutton "A partir d'une progression"<br/>
	 */
	public boolean isB1Click() {
		return b1Click;
	}

	private void setB1Click(boolean bClick) {
		this.b1Click = bClick;
	}

	/**
	 * 
	 * @return La liste des referentiels de la Db<br/>
	 * @throws Exception
	 *             La base de donnee ne contient aucun referentiel<br/>
	 */
	public List<Referentiel> getReferentiels() throws Exception {
		if (referentiels == null)
			referentiels = new ReferentielFactory().findReferentiels();
		return referentiels;
	}

	/**
	 * 
	 * @return La liste de progression du referentiel<br/>
	 * @throws Exception
	 *             Si la liste de progression est null ou vide<br/>
	 */
	public List<Progression> getProgressions() throws Exception {
		if (progressions == null)
			progressions = new ProgressionFactory()
					.findProgressions(getReferentiel());

		return progressions;
	}

	public void setTitreField(TextField tx) {
		this.titreField = tx;
	}

	public TextField getTitreField() {
		return titreField;
	}

	/**
	 * 
	 * @return Le titre de la sequence sans espace a droite et a gauche<br/>
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * 
	 * @param titre
	 * @throws Exception
	 *             Le titre propose est deja utilise<br/>
	 */
	public void setTitre(String titre) throws Exception {

		if (titre == null || titre.equals(""))
			throw new Exception("Veuillez saisir un titre");

		titre = getTitreField().getValue();

		List<Sequence> seqs = new SequenceFactory()
				.findSequences(getProgression());

		for (int i = 0; i < seqs.size(); i++) {
			Sequence seq = seqs.get(i);
			if (seq.getTitre().equals(titre.trim().toUpperCase()))
				throw new Exception("Ce titre est déja utilisé");
		}

		this.titre = titre.trim();
	}

	/**
	 * 
	 * @return Une sequence<br/>
	 */
	private Sequence getSequence() {
		return sequence;
	}

	private void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}

	@Override
	public void buttonClick(String button) {
		if (button.equals("Oui")) {
			if (getSequence() != null) {

				new SequenceFactory().remove(getSequence());
				setSequence(null);

				try {

					removeComponent(getConsultation());
					getSequenceViewPanelContent().getUI().getUI().addWindow(getConsultation());
				} catch (Exception e) {
					getSequenceViewPanelContent().getUI().getUI()
							.addWindow(new AlertWindow("Erreur !!!", e.getMessage()).show());
				}

			} else {

				getSequenceViewPanelContent().getUI().getUI()
						.addWindow(new AlertWindow("Alert !!", "Aucune sequence à supprimer"));
			}
		}
	}

}
