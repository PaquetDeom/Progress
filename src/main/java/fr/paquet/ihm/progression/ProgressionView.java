package fr.paquet.ihm.progression;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import fr.paquet.dataBase.Connect;
import fr.paquet.framework.ui.ProgView;
import fr.paquet.ihm.AlertWindow;
import fr.paquet.ihm.progression.CreationWindow;
import fr.paquet.progression.Progression;
import fr.paquet.progression.ProgressionFactory;
import fr.paquet.referentiel.Referentiel;
import fr.paquet.referentiel.ReferentielFactory;

@SuppressWarnings("serial")
public class ProgressionView extends AbsoluteLayout implements ProgView {

	private VerticalLayout progressionPanelContent = null;
	private List<Referentiel> referentiels = null;
	private Referentiel referentiel = null;
	private TextField titreField = null;
	private String titre = null;

	public VerticalLayout getProgressionViewPanelContent() {
		if (progressionPanelContent == null)
			BuildProgressionViewPanelContent(new VerticalLayout());
		return progressionPanelContent;
	}

	private void BuildProgressionViewPanelContent(VerticalLayout progressionPanelContent) {
		this.progressionPanelContent = progressionPanelContent;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getName() {
		return "Progression";
	}

	@Override
	public String getCaption() {
		return "Progression";
	}

	public ProgressionView() {
		super();
		buildView();
	}

	public void buildView() {

		removeAllComponents();
		addStyleName("progressionView");

		setSizeFull();

		VerticalLayout layout = new VerticalLayout();
		layout.addComponent(getDetail());
		addComponent(layout);

	}

	/**
	 * 
	 * @return contenu de la fenetre d'accueil de progression<br/>
	 */
	public Component getDetail() {

		Panel pan = new Panel();
		pan.setCaption("Accueil - Progression");
		VerticalLayout panelContent = getProgressionViewPanelContent();

		pan.setContent(panelContent);

		HorizontalLayout layoutCreation = new HorizontalLayout();
		layoutCreation.setCaption("Creation d'une progression");

		Button creer = new Button();
		creer.setCaption("Créer une progression");
		layoutCreation.addComponent(creer);

		creer.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				try {

					if (getReferentiels() != null || getReferentiels().isEmpty())
						getProgressionViewPanelContent().getUI().addWindow(new CreationWindow(ProgressionView.this));

				} catch (Exception e) {

					getProgressionViewPanelContent().getUI().getUI()
							.addWindow(new AlertWindow("Erreur !!!", e.getMessage()).show());
				}

			}
		});

		HorizontalLayout layoutConsultation = new HorizontalLayout();
		layoutConsultation.setCaption("Consultation de progression");

		Button b3 = new Button();
		b3.setCaption("Consulter");
		layoutConsultation.addComponent(b3);

		panelContent.addComponent(layoutCreation);
		panelContent.addComponent(layoutConsultation);

		return pan;
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
	 * @return La liste de selection d'un referentiel<br/>
	 */
	public Component getListReferentiel() throws Exception {

		if (getReferentiels() == null || getReferentiels().isEmpty())
			return null;

		IntStream.range(0, getReferentiels().size()).mapToObj(i -> {
			try {
				return getReferentiels().get(i);
			} catch (Exception e) {
				getProgressionViewPanelContent().getUI().getUI()
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
	 * @return le referentiel choisi dans la liste<br/>
	 */
	public Referentiel getReferentiel() {
		return referentiel;
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

		List<Progression> progs = new ProgressionFactory()
				.findProgressions(getReferentiel());

		for (int i = 0; i < progs.size(); i++) {
			Progression prog = progs.get(i);
			if (prog.getTitre().equals(titre.trim().toUpperCase()))
				throw new Exception("Ce titre est déja utilisé");
		}

		this.titre = titre.trim();
	}
}
