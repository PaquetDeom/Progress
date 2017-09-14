package fr.paquet.ihm.referentiel;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import fr.paquet.dataBase.Connect;
import fr.paquet.ihm.AlertWindow;
import fr.paquet.referentiel.Referentiel;
import fr.paquet.referentiel.ReferentielFactory;

@SuppressWarnings("serial")
public class CreationWindow extends Window {

	/**
	 * @author Nathanaël
	 * 
	 *         Ecran de creation de referentiel<br/>
	 */

	private ReferentielView refV = null;

	public CreationWindow(ReferentielView refView) {
		super();
		setReferentielView(refView);
		buildWindow();
	}

	private void setReferentielView(ReferentielView ref) {
		this.refV = ref;
	}

	/**
	 * 
	 * @return La Referentielview qui a appele la fenetre CreationWindow()<br/>
	 */
	private ReferentielView getRefView() {

		return refV;
	}

	/**
	 * Methode de cretation de la fenetre<br/>
	 */
	private void buildWindow() {

		setCaption("Saisir un nouveau référentiel");
		setSizeUndefined();
		setWidth(600.0f, Unit.PIXELS);
		center();
		setModal(true);

		final FormLayout content = new FormLayout();
		content.setMargin(true);
		VerticalLayout layout = new VerticalLayout();

		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.addComponent(getOk());
		hLayout.addComponent(getAnnul());

		content.addComponent(layout);
		setContent(content);

		layout.addComponent(getDiplome());

		layout.addComponent(hLayout);

		setVisible(true);
	}

	/**
	 * 
	 * @return La zone de choix du diplome<br/>
	 */
	private Component getDiplome() {

		HorizontalLayout hLayoutTitre = new HorizontalLayout();
		hLayoutTitre.setCaption("Diplome");
		hLayoutTitre.setSpacing(true);
		try {
			hLayoutTitre.addComponent(getRefView().getListDiplomes());
		} catch (Exception e) {
			getRefView().getReferentielViewPanelContent().getUI().getUI()
					.addWindow(new AlertWindow("Erreur !!!", e.getMessage()).show());
			e.printStackTrace();
		}

		return hLayoutTitre;

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

				try {

					// Referentiel ref = new
					// Referentiel(getRefView().getDiplome(),
					// getRefView().getIntitule());
					Referentiel ref = new Referentiel(getRefView().getDiplome());

					new ReferentielFactory(Connect.getEmf().createEntityManager()).save(ref);

					getRefView().getReferentielViewPanelContent().getUI().getUI().addWindow(new NewRefView(ref));

					close();
					refV = null;

				} catch (Exception e) {

					getRefView().getReferentielViewPanelContent().getUI().getUI()
							.addWindow(new AlertWindow("Erreur !!", e.getMessage()).show());

				}

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

				close();

			}
		});

		return button;

	}

}
