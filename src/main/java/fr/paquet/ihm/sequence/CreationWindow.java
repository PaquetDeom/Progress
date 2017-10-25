package fr.paquet.ihm.sequence;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import fr.paquet.dataBase.Connect;
import fr.paquet.ihm.AlertWindow;
import fr.paquet.sequence.Sequence;
import fr.paquet.sequence.SequenceFactory;

@SuppressWarnings("serial")
public class CreationWindow extends Window {

	/**
	 * @author Nathanaël
	 * 
	 *         Ecran de creation de sequence<br/>
	 */

	private SequenceView seqV = null;
	private int buildView = 0;

	public CreationWindow(SequenceView seqView) {
		super();
		setSequenceView(seqView);
		buildWindow();

	}

	private void setBuildView(int b) {
		this.buildView = b;
	}

	/**
	 * 
	 * @return Le nombre de passage par la methode BuidWindow()<br/>
	 */
	private int getBuildView() {
		return buildView;
	}

	private void setSequenceView(SequenceView seq) {
		this.seqV = seq;
	}

	/**
	 * 
	 * @return La Sequence view qui a appele la fenetre CreationWindow()<br/>
	 */
	private SequenceView getSeqView() {

		return seqV;
	}

	/**
	 * Methode de creation de la fenetre<br/>
	 */
	private void buildWindow() {

		setBuildView(getBuildView() + 1);
		setCaption("Creation d'une nouvelle sequence");
		setSizeUndefined();
		setWidth(600.0f, Unit.PIXELS);
		center();
		setModal(true);

		final FormLayout content = new FormLayout();
		content.setMargin(true);
		VerticalLayout layout = new VerticalLayout();

		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.addComponent(getOk());

		if (getBuildView() > 1)
			hLayout.addComponent(getRetour());

		hLayout.addComponent(getAnnul());

		content.addComponent(layout);
		setContent(content);

		layout.addComponent(getRef());

		if (getBuildView() > 1) {

			layout.addComponent(getProg());
		}

		if (getBuildView() == 3) {

			layout.addComponent(getTitre());
		}

		layout.addComponent(hLayout);

		setVisible(true);
	}

	/**
	 * 
	 * @return La zone de saisi du titre<br/>
	 */
	private Component getTitre() {

		HorizontalLayout hLayoutTitre = new HorizontalLayout();
		hLayoutTitre.setCaption("Titre");
		hLayoutTitre.setSpacing(true);
		TextField tx = new TextField();
		tx.setInputPrompt("Veuillez saisir un titre");
		tx.setWidth(35, Unit.EM);
		getSeqView().setTitreField(tx);
		hLayoutTitre.addComponent(getSeqView().getTitreField());

		return hLayoutTitre;

	}

	/**
	 * 
	 * @return La zone de choix de la progression<br/>
	 */
	private Component getProg() {

		HorizontalLayout hLayoutProg = new HorizontalLayout();
		hLayoutProg.setCaption("Progression");
		hLayoutProg.setSpacing(true);

		try {
			if (getBuildView() < 3)
				hLayoutProg.addComponent(getSeqView().getListProgression());
			else {
				TextField tx = new TextField();
				tx.setValue(getSeqView().getProgression().toString());
				tx.setWidth(35, Unit.EM);
				tx.setReadOnly(true);
				hLayoutProg.addComponent(tx);
			}
		} catch (Exception e) {
			getSeqView().getSequenceViewPanelContent().getUI().getUI()
					.addWindow(new AlertWindow("Erreur !!", e.getMessage()).show());
		}

		return hLayoutProg;

	}

	/**
	 * 
	 * @return La zone de choix du referentiel<br/>
	 */
	private Component getRef() {
		HorizontalLayout hLayoutRef = new HorizontalLayout();
		hLayoutRef.setCaption("Référentiel");
		hLayoutRef.setSpacing(true);

		try {
			if (getBuildView() < 2)
				hLayoutRef.addComponent(getSeqView().getListReferentiel());
			else {

				TextField tx = new TextField();
				tx.setValue(getSeqView().getReferentiel().toString());
				tx.setWidth(35, Unit.EM);
				tx.setReadOnly(true);
				hLayoutRef.addComponent(tx);
			}
		} catch (Exception e) {

			getSeqView().getSequenceViewPanelContent().getUI().getUI()
					.addWindow(new AlertWindow("Erreur !!", e.getMessage()).show());
		}

		return hLayoutRef;
	}

	/**
	 * 
	 * @return Le boutton ok<br/>
	 */
	private Button getOk() {

		Button button = new Button("OK");

		button.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				if (getBuildView() < 4)
					buildWindow();

				if (getBuildView() > 3) {

					setBuildView(0);

					try {

						getSeqView().setTitre(getSeqView().getTitreField().getValue());
						Sequence seq = new Sequence(getSeqView().getProgression(), getSeqView().getTitre());
						new SequenceFactory(Connect.getEmf().createEntityManager()).save(seq);

						getSeqView().getSequenceViewPanelContent().getUI().getUI().addWindow(new NewSeqView(seq));

						close();
						seqV = null;

					} catch (Exception e) {
						getSeqView().getSequenceViewPanelContent().getUI().getUI()
								.addWindow(new AlertWindow("Erreur !!", e.getMessage()).show());
						setBuildView(2);
						buildWindow();
					}

				}

			}
		});

		return button;

	}

	/**
	 * 
	 * @return Le boutton retour<br/>
	 */
	private Button getRetour() {

		Button button = new Button("Retour");

		button.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				setBuildView(getBuildView() - 2);
				buildWindow();

			}
		});

		return button;
	}

	/**
	 * 
	 * @return Le boutton annuler<br/>
	 */
	private Button getAnnul() {

		Button button = new Button("Annuler");

		button.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				setBuildView(0);
				close();

			}
		});

		return button;

	}

}
