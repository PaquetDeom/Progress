package fr.paquet.ihm.progression;

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
import fr.paquet.progression.Progression;
import fr.paquet.progression.ProgressionFactory;

@SuppressWarnings("serial")
public class CreationWindow extends Window {

	/**
	 * @author Nathanaël
	 * 
	 *         Ecran de creation de sequence<br/>
	 */

	private ProgressionView progV = null;
	private int click = 0;

	public CreationWindow(ProgressionView progView) {
		super();
		setProgressionView(progView);
		buildWindow();

	}

	private void setClick(int b) {
		this.click = b;
	}

	/**
	 * 
	 * @return Le nombre de click sur le boutton ok<br/>
	 */
	private int getClick() {
		return click;
	}

	private void setProgressionView(ProgressionView prog) {
		this.progV = prog;
	}

	/**
	 * 
	 * @return La Sequence view qui a appele la fenetre CreationWindow()<br/>
	 */
	private ProgressionView getProgView() {

		return progV;
	}

	/**
	 * Methode de cretation de la fenetre<br/>
	 */
	private void buildWindow() {

		setCaption("Creation d'une nouvelle progression");
		setSizeUndefined();
		setWidth(600.0f, Unit.PIXELS);
		center();
		setModal(true);

		final FormLayout content = new FormLayout();
		content.setMargin(true);
		VerticalLayout layout = new VerticalLayout();

		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.addComponent(getOk());

		if (getClick() > 0)
			hLayout.addComponent(getRetour());

		hLayout.addComponent(getAnnul());

		content.addComponent(layout);
		setContent(content);

		layout.addComponent(getRef());

		if (getClick() > 0) {

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
		getProgView().setTitreField(tx);
		hLayoutTitre.addComponent(getProgView().getTitreField());

		return hLayoutTitre;

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
			if (getClick() < 1)
				hLayoutRef.addComponent(getProgView().getListReferentiel());
			else {

				TextField tx = new TextField();
				tx.setValue(getProgView().getReferentiel().toString());
				tx.setWidth(35, Unit.EM);
				tx.setReadOnly(true);
				hLayoutRef.addComponent(tx);
			}
		} catch (Exception e) {

			getProgView().getProgressionViewPanelContent().getUI().getUI()
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

				setClick(click + 1);

				if (getClick() == 1)
					buildWindow();

				if (getClick() > 1) {

					setClick(0);

					try {

						getProgView().setTitre(getProgView().getTitreField().getValue());
						Progression prog = new Progression(getProgView().getReferentiel(), getProgView().getTitre());
						new ProgressionFactory().save(prog);

						getProgView().getProgressionViewPanelContent().getUI().getUI().addWindow(new NewProgView(prog));

						close();
						progV = null;

					} catch (Exception e) {
						
						getProgView().getProgressionViewPanelContent().getUI().getUI()
								.addWindow(new AlertWindow("Erreur !!", e.getMessage()).show());
						setClick(1);
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
				setClick(getClick() - 1);
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

				setClick(0);
				close();

			}
		});

		return button;

	}

}
