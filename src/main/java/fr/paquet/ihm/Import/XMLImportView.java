package fr.paquet.ihm.Import;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;

import fr.paquet.framework.ui.ProgView;
import fr.paquet.ihm.AlertListener;
import fr.paquet.ihm.AlertWindow;

//TODO Listener OkButton
@SuppressWarnings("serial")
public class XMLImportView extends AbsoluteLayout implements ProgView, OkButtonWindowImport {
	/**
	 * 
	 */

	private Panel panel = null;
	private VerticalLayout mainLayout = null;
	private TextField rne = null;
	private String rneString = null;
	private Path pathFolder = null;
	private Path pathSiecleFolder = null;
	private Path pathEdtFolder = null;

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

		// organisation des layout
		if (getPathFolder() == null)
			vLayout.addComponent(importXml);

		// TODO Faire une Window
		if (getPathFolder() != null) {
			hLayout.setCaption("Import des fichiers de l'établissement " + getRne());
			vLayout.addComponent(hLayout);
		}

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

	public void setRne() throws Exception {

		String codeRne = getRneTextField().getValue().trim();

		// test si la valeur est nulle ou vide
		if (codeRne == null || codeRne.equals(""))
			throw new Exception("Veuillez saisir un code rne");

		// test si elle correspond a l'expression reguliere
		boolean a = false;
		a = Pattern.matches("([0-9][0-9][0-9][0-9][0-9][0-9][0-9][A-Z])", codeRne);
		if (a == false)
			throw new Exception("Code Rne invalide");

		this.rneString = codeRne;
	}

	/**
	 * 
	 * @return le code rne saisi par l'operateur<br/>
	 */
	public String getRne() {

		return rneString;
	}

	public void setPathFolder() {

		this.pathFolder = Paths
				.get(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/fileFolder/" + getRne());

	}

	public void setPathSiecleFolder() {

		this.pathSiecleFolder = Paths.get(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath()
				+ "/fileFolder/" + getRne() + "/Siecle");
	}

	public void setPathEdtFolder() {

		this.pathEdtFolder = Paths.get(
				VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/fileFolder/" + getRne() + "/EDT");
	}

	/**
	 * 
	 * @return Le répertoire d'accueil des fichiers Siecle et EDT<br/>
	 */
	public Path getPathFolder() {

		return pathFolder;
	}

	/**
	 * 
	 * @return Le repertoire d'accueil des fichiers siècles<br/>
	 */
	public Path getPathSiecleFolder() {
		return pathSiecleFolder;
	}

	/**
	 * 
	 * @return Le repertoire d'accueil des fichiers Edt<br/>
	 */
	public Path getPathEdtFolder() {
		return pathEdtFolder;
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

	@Override
	public void buttonClick(String button) {
		// TODO

	}

}
