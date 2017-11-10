package fr.paquet.ihm.Import;


import java.io.IOException;


import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

import fr.paquet.ihm.AlertListener;
import fr.paquet.ihm.AlertWindow;

@SuppressWarnings("serial")
public class WindowRepertoire extends Window implements AlertListener {

	private XMLImportView XMLIV = null;
	private TextField rne = null;

	/**
	 * constructeur de la Window<br/>
	 */
	public WindowRepertoire(XMLImportView xml) {

		setXMLImportView(xml);
		BuildWindow();
	}

	private void setXMLImportView(XMLImportView xml) {
		this.XMLIV = xml;

	}

	/**
	 * 
	 * @return le XMLImportView<br/>
	 */
	private XMLImportView getXMLImportView() {
		return XMLIV;
	}

	private void BuildWindow() {

		rne = null;
		setCaption("Quel est le nom du repertoire qui reçoit vos fichiers");
		setSizeUndefined();
		setWidth(600.0f, Unit.PIXELS);
		center();
		setModal(true);

		final FormLayout content = new FormLayout();
		content.setMargin(true);
		VerticalLayout layout = new VerticalLayout();

		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.addComponent(getOkButton());
		hLayout.addComponent(getAnnulButton());

		layout.addComponent(getRneTextField());
		layout.addComponent(hLayout);
		content.addComponent(layout);
		setContent(content);

		setVisible(true);

	}

	/**
	 * 
	 * @return la zone de saisi du code Rne de l'etablissement<br/>
	 */
	public TextField getRneTextField() {

		if (rne == null) {
			rne = new TextField();
			rne.setWidth(35, Unit.EM);
			rne.setCaption("Code Rne");
			rne.setInputPrompt("Saisir le code Rne de l'établissement scolaire");
		}

		return rne;
	}

	public Button getOkButton() {

		Button okButton = new Button("Valider");

		// Listener
		okButton.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				try {

					// prend la valeur du code rne saisi et cree un repetoire<br/>
					RneImport.getRne(getRneTextField().getValue());
					RneImport.CreateDirectories();

				} catch (IOException ioe) {

					getXMLImportView().getXMLImportViewPanelContent().getUI().getUI()
							.addWindow(new AlertWindow("Erreur !!!", ioe.getMessage()).show());
					ioe.printStackTrace(System.out);

				} catch (Exception e) {
					getXMLImportView().getXMLImportViewPanelContent().getUI().getUI()
							.addWindow(new AlertWindow("Erreur !!!", e.getMessage()).show());
					e.printStackTrace(System.out);
				}

				getXMLImportView().getXMLImportViewPanelContent().getUI().getUI()
						.addWindow(new AlertWindow("message !!!",
								"Vos fichiers vont être importer dans le répertoire " + RneImport.getRne(),
								new String[] { "Suite" }, WindowRepertoire.this).show());

				close();

			}
		});

		return okButton;
	}

	public Button getAnnulButton() {

		Button annulButton = new Button("Annuler");

		annulButton.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				close();
			}
		});

		return annulButton;
	}

	@Override
	public void buttonClick(String button) {
		if (button.equals("Suite")) {
			getXMLImportView().getXMLImportViewPanelContent().getUI().getUI()
					.addWindow(new WindowImport(new ChoiseImport(getXMLImportView())));
		}

		close();

	}

}
