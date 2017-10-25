package fr.paquet.ihm.Import;

import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

import fr.paquet.ihm.AlertWindow;

@SuppressWarnings("serial")
public class WindowRepertoire extends Window {

	private XMLImportView XMLIV = null;

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

		layout.addComponent(getXMLImportView().getRneTextField());
		layout.addComponent(hLayout);
		content.addComponent(layout);
		setContent(content);

		setVisible(true);

	}

	public Button getOkButton() {

		Button okButton = new Button("Valider");

		// Listener
		okButton.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				try {

					// prend la valeur du code rne saisi et cree un repetoire<br/>
					getXMLImportView().setRne();
					getXMLImportView().setPathFolder();

					// verifie si le repertoire existe
					if (getXMLImportView().getPathFolder().exists())
						new XMLImportView().getXMLImportViewPanelContent().getUI().getUI().addWindow(new AlertWindow(
								"message !!!",
								"Vos fichiers vont être importer dans le répertoire" + getXMLImportView().getRne())
										.show());
					else {
						getXMLImportView().getPathFolder().createNewFile();
						new XMLImportView().getXMLImportViewPanelContent().getUI().getUI().addWindow(new AlertWindow(
								"message !!!",
								"Vos fichiers vont être importer dans le répertoire" + getXMLImportView().getRne())
										.show());
					}

					close();

				} catch (Exception e) {
					new XMLImportView().getXMLImportViewPanelContent().getUI().getUI()
							.addWindow(new AlertWindow("Erreur !!!", e.getMessage()).show());
					e.printStackTrace(System.out);
				}

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

}
