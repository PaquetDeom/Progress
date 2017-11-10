package fr.paquet.ihm.Import;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;

import fr.paquet.framework.ui.ProgView;

//TODO Listener OkButton
@SuppressWarnings("serial")
public class XMLImportView extends AbsoluteLayout implements ProgView {
	/**
	 * 
	 */

	private Panel panel = null;
	private VerticalLayout mainLayout = null;

	/**
	 * Constructeur de la class<br/>
	 */
	public XMLImportView() {
		super();
		BuildView();

	}

	/**
	 * 
	 * @return la fenetre de recherche ou de creation de repertoire<br/>
	 */
	private Window getWindowRepertoire() {
		return new WindowRepertoire(this);
	}

	/**
	 * efface ecran puis cree un VerticalLayout<br/>
	 */
	public void BuildView() {

		removeAllComponents();
		setPanel(new Panel());
		addStyleName("XMLImport");

		setSizeFull();

		VerticalLayout layout = new VerticalLayout();
		layout.addComponent(getDetail());
		addComponent(layout);

	}

	private void setPanel(Panel panel) {
		this.panel = panel;
	}

	/**
	 * 
	 * @return le Panel de getDetail<br/>
	 */
	private Panel getPanel() {
		return panel;
	}

	/**
	 * 
	 * @return Le layout principal du Panel<br/>
	 */
	public VerticalLayout getXMLImportViewPanelContent() {
		if (mainLayout == null)
			mainLayout = new VerticalLayout();
		return mainLayout;
	}

	/**
	 * 
	 * @return Le component principal de l'ecran<br/>
	 */

	private Component getDetail() {

		// creation des buttons
		Button importXml = new Button("Import");

		// creaton de layout
		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.setSpacing(true);

		// Panel principal
		getPanel().setCaption("Accueil - Import des Fichiers *.xml");

		// Layout principal
		VerticalLayout vLayout = getXMLImportViewPanelContent();

		vLayout.addComponent(importXml);
		getPanel().setContent(vLayout);

		// listener du button de creation des folders
		importXml.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				getXMLImportViewPanelContent().getUI().getUI().addWindow(getWindowRepertoire());
			}
		});

		return getPanel();
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

	@Override
	public String getName() {

		return "Import et integration XML";
	}

	@Override
	public String getCaption() {

		return "Import et integration XML";
	}

}
