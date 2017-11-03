package fr.paquet.ihm.Import;

import java.io.File;
import java.nio.file.Files;

import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;

import fr.paquet.ihm.AlertWindow;

public class ChoiseImport extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private XMLImportView xmlView = null;

	public ChoiseImport(XMLImportView xml) {
		super();
		setXMLImportView(xml);

		// construction du layout
		setSizeFull();

		// Creation des Components
		HorizontalLayout hLayout = new HorizontalLayout();
		Button siecle = new Button("Import des Fichiers Siecle");
		Button edt = new Button("Import des Fichiers Edt");

		// Organisation du Layout
		hLayout.addComponent(siecle);
		hLayout.addComponent(edt);
		this.addComponent(hLayout);

		// listener du button d'import de siecle

		siecle.addClickListener(new Button.ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				try {
					getXmlView().setPathSiecleFolder();
					File siecleFile = getXmlView().getPathSiecleFolder().toFile();
					if (!siecleFile.exists()) {
						Files.createDirectories(getXmlView().getPathSiecleFolder());
						System.out.println("Repertoire créé " + getXmlView().getPathSiecleFolder().toString());
					}

					getXmlView().getXMLImportViewPanelContent().getUI().getUI()
							.addWindow(new WindowImport(new SiecleImport(getXmlView()), "Valider"));
				} catch (Exception e) {
					getXmlView().getXMLImportViewPanelContent().getUI().getUI()
							.addWindow(new AlertWindow("Erreur !!!", e.getMessage()).show());
					e.printStackTrace(System.out);
				}

			}
		});

		// listener du button de edt

		edt.addClickListener(new Button.ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				try {
					getXmlView().setPathEdtFolder();
					File edtFile = getXmlView().getPathEdtFolder().toFile();
					if (!edtFile.exists()) {
						Files.createDirectories(getXmlView().getPathEdtFolder());
						System.out.println("Repertoire créé " + getXmlView().getPathEdtFolder().toString());

					}

					getXmlView().getXMLImportViewPanelContent().getUI().getUI()
							.addWindow(new WindowImport(new EdtImport(getXmlView()), "Valider"));
				} catch (Exception e) {
					getXmlView().getXMLImportViewPanelContent().getUI().getUI()
							.addWindow(new AlertWindow("Erreur !!!", e.getMessage()).show());
					e.printStackTrace(System.out);

				}

			}
		});

	}

	private void setXMLImportView(XMLImportView xml) {
		this.xmlView = xml;

	}

	private XMLImportView getXmlView() {
		return xmlView;
	}

}
