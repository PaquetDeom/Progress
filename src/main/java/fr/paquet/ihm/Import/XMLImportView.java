package fr.paquet.ihm.Import;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;
import java.util.regex.Pattern;

import javax.naming.directory.DirContext;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

import fr.paquet.dataBase.Connect;
import fr.paquet.framework.ui.ProgView;
import fr.paquet.ihm.AlertWindow;
import fr.paquet.ihm.sequence.CreationWindow;
import fr.paquet.ihm.sequence.SequenceView;
import fr.paquet.sequence.Sequence;
import fr.paquet.sequence.SequenceFactory;

public class XMLImportView extends AbsoluteLayout implements ProgView, SucceededListener {
	/**
	 * 
	 */

	private VerticalLayout mainLayout = null;
	private TextField rne = null;
	private String rneString = null;
	private File pathFolder = null;
	private File pathSiecleFolder = null;
	private File pathEdtFolder = null;

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
	private void BuildView() {

		removeAllComponents();
		addStyleName("XMLImport");

		setSizeFull();

		VerticalLayout layout = new VerticalLayout();
		layout.addComponent(getDetail());
		addComponent(layout);

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
	@SuppressWarnings("serial")
	private Component getDetail() {

		// creation des buttons
		Button importXml = new Button("Import");
		Button siecle = new Button("Siecle");

		// Panel principal
		Panel pan = new Panel();
		pan.setCaption("Accueil - Import des Fichiers *.xml");

		// Layout principal
		VerticalLayout vLayout = getXMLImportViewPanelContent();

		// organisation des layout
		vLayout.addComponent(importXml);
		vLayout.addComponent(siecle);

		pan.setContent(vLayout);

		importXml.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				getXMLImportViewPanelContent().getUI().getUI().addWindow(getWindowRepertoire());

			}
		});
		
		siecle.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				getXMLImportViewPanelContent().getUI().getUI().addWindow(new WindowImport(new SiecleImport(XMLImportView.this)));
				
			}
		});

		return pan;
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

		this.pathFolder = new File(
				VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/fileFolder/" + getRne());
	}

	public void setPathSiecleFolder() {

		this.pathSiecleFolder = new File(
				VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/fileFolder/" + getRne() + "Siecle/");
	}

	public void setPathEdtFolder() {

		this.pathEdtFolder = new File(
				VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/fileFolder/" + getRne() + "EDT/");
	}

	/**
	 * 
	 * @return Le répertoire d'accueil des fichiers Siecle et EDT<br/>
	 */
	public File getPathFolder() {

		return pathFolder;
	}
	
	/**
	 * 
	 * @return Le repertoire d'accueil des fichiers siècles<br/>
	 */
	public File getPathSiecleFolder() {
		return pathSiecleFolder;
	}
	
	/**
	 * 
	 * @return Le repertoire d'accueil des fichiers Edt<br/>
	 */ 
	public File getPathEdtFolder() {
		return pathEdtFolder;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Integration XML";
	}

	@Override
	public String getCaption() {
		// TODO Auto-generated method stub
		return "Integration XML";
	}

	private void showNotification(Notification notification) {
		// keep the notification visible a little while after moving the
		// mouse, or until clicked
		notification.setDelayMsec(10000);
		notification.show(Page.getCurrent());
	}

	@Override
	public void uploadSucceeded(SucceededEvent event) {
		// TODO Auto-generated method stub

	}

}
