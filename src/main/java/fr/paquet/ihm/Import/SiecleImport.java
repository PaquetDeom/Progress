package fr.paquet.ihm.Import;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import java.util.EnumSet;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

import fr.paquet.ihm.AlertWindow;
import fr.paquet.io.IntegrationMain;

import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class SiecleImport extends VerticalLayout implements SucceededListener {

	class ButtonIntegration extends Button implements RneImport.RneChangedListener, Button.ClickListener {
		RneImport rne = null;

		public ButtonIntegration(RneImport rne) {
			super("Integration");
			try {
				this.rne = rne;
				rne.addChangeListener(this);
				setEnabled(rne.isAllLoaded());
				addClickListener(this);
			} catch (Exception e) {
				e.printStackTrace();
				getXmlView().getXMLImportViewPanelContent().getUI().getUI()
						.addWindow(new AlertWindow("Erreur !!!", e.getMessage()).show());
			}
		}

		/**
		 * si tous les documents sont chargés, alors le bouton devient actif. sinon il
		 * reste grisé.
		 */
		public void rneChanged() {
			try {
				setEnabled(rne.isAllLoaded());
			} catch (Exception e) {
				e.printStackTrace();
				getXmlView().getXMLImportViewPanelContent().getUI().getUI()
						.addWindow(new AlertWindow("Erreur !!!", e.getMessage()).show());
			}
		}

		@Override
		public void buttonClick(ClickEvent event) {

			// TODO : Affichage de la ProgressBar

			// Integration des *.xml dans la base
			new IntegrationMain(getRneImport());

		}
	}

	class DocumentCheckbox extends CheckBox implements RneImport.RneChangedListener {
		RneImport rne = null;
		XMLDocuments document = null;

		public DocumentCheckbox(RneImport rne, XMLDocuments document) {
			super(document.fileName());
			try {
				this.rne = rne;
				this.document = document;
				rne.addChangeListener(this);
				setValue(rne.hasDocument(document));
				setEnabled(false);
			} catch (Exception e) {
				e.printStackTrace();
				getXmlView().getXMLImportViewPanelContent().getUI().getUI()
						.addWindow(new AlertWindow("Erreur !!!", e.getMessage()).show());
			}
		}

		public void rneChanged() {
			try {
				setValue(rne.hasDocument(document));
			} catch (Exception e) {
			}
		}
	}

	private XMLImportView xmlView = null;
	private RneImport rneImport = null;
	private VerticalLayout layout1 = null;

	public SiecleImport(XMLImportView xml, RneImport rneImport) {
		super();
		setXMLImportView(xml);
		setRneImport(rneImport);
		BuildLayout();

	}

	private void BuildLayout() {
		removeAllComponents();
		setSizeFull();
		setLayout1(new VerticalLayout());
		addComponent(getLayout1());
		Layout emptyScreen = new VerticalLayout();
		Upload upload = new Upload(null, new Upload.Receiver() {
			@Override
			public OutputStream receiveUpload(String fileName, String mimeType) {

				try {
					return new FileOutputStream(getRneImport().createFile(fileName));
				} catch (FileNotFoundException fE) {
					getXmlView().getXMLImportViewPanelContent().getUI().getUI()
							.addWindow(new AlertWindow("Erreur !!!", fE.getMessage()).show());
					fE.printStackTrace();

				} catch (Exception e) {
					getXmlView().getXMLImportViewPanelContent().getUI().getUI()
							.addWindow(new AlertWindow("Erreur !!!", e.getMessage()).show());
					e.printStackTrace();
				}
				return null;
			}
		});

		upload.addSucceededListener(this);

		addComponent(upload);
		addComponent(emptyScreen);
		setExpandRatio(emptyScreen, 0.0f);

	}

	private void setXMLImportView(XMLImportView xml) {
		this.xmlView = xml;

	}

	private XMLImportView getXmlView() {
		return xmlView;
	}

	/**
	 * 
	 * @return Le layout contenant les noms de fichier a telecharger<br/>
	 */
	public VerticalLayout getLayout1() {
		if (layout1 == null)
			layout1 = new VerticalLayout();
		return layout1;
	}

	public void setLayout1(VerticalLayout layout1) {

		Label label0 = new Label();
		Label label7 = new Label();

		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.setSpacing(true);

		Image logoOk = new Image("", new ThemeResource("img/accept.png"));

		label0.setCaption("Fichier à télécharger");
		label7.setCaption("Tous vos fichier sont téléchargés");

		hLayout.addComponent(label7);
		hLayout.addComponent(logoOk);

		layout1.addComponent(label0);

		for (XMLDocuments doc : EnumSet.allOf(XMLDocuments.class)) {
			// on crée le label des documents à télécharger
			DocumentCheckbox checkboxDoc = new DocumentCheckbox(getRneImport(), doc);
			layout1.addComponent(checkboxDoc);
		}

		layout1.addComponent(new ButtonIntegration(getRneImport()));
		this.layout1 = layout1;
	}

	/**
	 * 
	 * @return Le RneImport de l'import en cours<br/>
	 */
	private RneImport getRneImport() {
		return rneImport;
	}

	private void setRneImport(RneImport rneImport) {
		this.rneImport = rneImport;
	}

	@Override
	public void uploadSucceeded(SucceededEvent event) {
		try {
			// si le fichier ne concerne pas le bon etablissement
			if (!getRneImport().checkFile(event.getFilename())) {
				getRneImport().deleteFile(event.getFilename());
				getXmlView().getXMLImportViewPanelContent().getUI().getUI()
						.addWindow(new AlertWindow("Erreur !!!", "n° RNE non valide, fichier supprimé !!").show());
			}
		} catch (Exception e) {
			getXmlView().getXMLImportViewPanelContent().getUI().getUI()
					.addWindow(new AlertWindow("Erreur !!!", e.getMessage()).show());
			e.printStackTrace();
		}

	}
}
