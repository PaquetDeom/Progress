package fr.paquet.ihm.Import;

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
	private RneImport rneImport = null;

	public ChoiseImport(XMLImportView xml, RneImport rneImport) {
		super();
		setXMLImportView(xml);
		setRneImport(rneImport);

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

					getXmlView().getXMLImportViewPanelContent().getUI().getUI()
							.addWindow(new WindowImport(new SiecleImport(getXmlView(), getRneImport())));
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
/**
					getXmlView().getXMLImportViewPanelContent().getUI().getUI()
							.addWindow(new WindowImport(new EdtImport(getXmlView())));*/

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

	private RneImport getRneImport() {
		return rneImport;
	}

	private void setRneImport(RneImport rneImport) {
		this.rneImport = rneImport;
	}

}
