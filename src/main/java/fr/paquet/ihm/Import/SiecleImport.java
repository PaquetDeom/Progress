package fr.paquet.ihm.Import;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

import fr.paquet.ihm.AlertWindow;
import fr.paquet.io.RecursiveNodes;
import fr.paquet.io.siecle.SiecleIntegration;

import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class SiecleImport extends VerticalLayout implements SucceededListener {

	private XMLImportView xmlView = null;

	private VerticalLayout layout1 = null;

	private String dateExport = null;

	public SiecleImport(XMLImportView xml) {
		super();
		setXMLImportView(xml);
		BuildLayout();

	}
//essai
	private void BuildLayout() {

		removeAllComponents();
		setSizeFull();
		setLayout1(new VerticalLayout());
		addComponent(getLayout1());
		Layout emptyScreen = new VerticalLayout();
		Upload upload = new Upload(null, new Upload.Receiver() {

			@Override
			public OutputStream receiveUpload(String filename, String mimeType) {

				if (!filename.equals("Communs.xml") && !filename.equals("ElevesAvecAdresses.xml")
						&& !filename.equals("Nomenclature.xml") && !filename.equals("Structures.xml")
						&& !filename.equals("Etablissements.xml") && !filename.equals("Geographique.xml")) {

					try {

						throw new Exception("Fichier non admis !");

					} catch (Exception e) {
						getXmlView().getXMLImportViewPanelContent().getUI().getUI()
								.addWindow(new AlertWindow("!!! Erreur !!!", e.getMessage()).show());
						e.printStackTrace();
					}

				} else {

					File file = new File(RneImport.getPathFolder().toString() + "/" + filename);

					try {
						file.createNewFile();
						System.out.println("Creation du fichier " + file.getAbsolutePath());
						return new FileOutputStream(file);

					} catch (Exception e) {
						e.printStackTrace(System.out);
						getXmlView().getXMLImportViewPanelContent().getUI().getUI()
								.addWindow(new AlertWindow("Erreur !!!", e.getMessage()).show());
						return null;
					}
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

	public boolean isCommuns() {
		return communs;
	}

	public void setCommuns(boolean communs) {
		this.communs = communs;
	}

	public boolean isEleveAvecAdresse() {
		return eleveAvecAdresse;
	}

	public void setEleveAvecAdresse(boolean eleveAvecAdresse) {
		this.eleveAvecAdresse = eleveAvecAdresse;
	}

	public boolean isEtablissements() {
		return etablissements;
	}

	public void setEtablissements(boolean etablissements) {
		this.etablissements = etablissements;
	}

	public boolean isGeographique() {
		return geographique;
	}

	public void setGeographique(boolean geographique) {
		this.geographique = geographique;
	}

	public boolean isNomenclature() {
		return nomenclature;
	}

	public void setNomenclature(boolean nomenclature) {
		this.nomenclature = nomenclature;
	}

	public boolean isStructures() {
		return structures;
	}

	public void setStructures(boolean structures) {
		this.structures = structures;
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
		Label label1 = new Label();
		Label label2 = new Label();
		Label label3 = new Label();
		Label label4 = new Label();
		Label label5 = new Label();
		Label label6 = new Label();
		Label label7 = new Label();

		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.setSpacing(true);

		Image logoOk = new Image("", new ThemeResource("img/accept.png"));

		label0.setCaption("Fichier à télécharger");
		label1.setCaption("Communs.xml");
		label2.setCaption("EleveAvecAdresse.xml");
		label3.setCaption("Etablissements.xml");
		label4.setCaption("Geographique.xml");
		label5.setCaption("Nomenclature.xml");
		label6.setCaption("Structures.xml");
		label7.setCaption("Tous vos fichier sont téléchargés");

		hLayout.addComponent(label7);
		hLayout.addComponent(logoOk);

		layout1.addComponent(label0);

		if (isCommuns() == false)
			layout1.addComponent(label1);
		if (isEleveAvecAdresse() == false)
			layout1.addComponent(label2);
		if (isEtablissements() == false)
			layout1.addComponent(label3);
		if (isGeographique() == false)
			layout1.addComponent(label4);
		if (isNomenclature() == false)
			layout1.addComponent(label5);
		if (isStructures() == false)
			layout1.addComponent(label6);
		if (isCommuns() == true && isEleveAvecAdresse() == true && isEtablissements() == true
				&& isGeographique() == true && isNomenclature() == true && isStructures() == true)
			layout1.addComponent(hLayout);

		this.layout1 = layout1;
	}

	private String getDateExport() {
		return dateExport;
	}

	private void setDateExport(String dateExport) {
		this.dateExport = dateExport;
	}

	private void DeleteFile(String fileName) {

		Path path = Paths.get(getXmlView().getPathSiecleFolder().toString() + "/" + fileName);

		try {

			Files.delete(path);

		} catch (NoSuchFileException nsfe) {
			nsfe.printStackTrace();
			System.err.println("Fichier ou repertoire " + path + " n'existe pas");

		} catch (DirectoryNotEmptyException dnee) {
			dnee.printStackTrace();
			System.err.println("Le repertoire " + path + " n'est pas vide");

		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.err.println("Impossible de supprimer " + path + " : " + ioe);

		}

	}

	@Override
	public void uploadSucceeded(SucceededEvent event) {

		ArrayList<Element> list = new ArrayList<Element>();

		if (event.getFilename().equals("Communs.xml")) {
			try {

				RecursiveNodes.getNodes(SiecleIntegration.getCommunsDocument().getDocumentElement(), list,
						"PARAMETRES");

			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}

			String exp1 = "UAJ";
			String rne = list.get(0).getElementsByTagName(exp1).item(0).getTextContent();

			if (rne != getXmlView().getRne()) {

				getXmlView().getXMLImportViewPanelContent().getUI().getUI().addWindow(new AlertWindow("!!! Erreur !!!",
						"Le fichier que vous voulez télécharger ne correspond pas à votre établissement scolaire"));

				DeleteFile(event.getFilename());

			} else {

				String exp2 = "DATE_EXPORT";
				String dateExport = list.get(0).getElementsByTagName(exp2).item(0).getTextContent();

				if (getDateExport() == null) {
					setDateExport(dateExport);
					setCommuns(true);
					BuildLayout();
				}

				if (dateExport == getDateExport()) {
					setCommuns(true);
					BuildLayout();
				} else {

					getXmlView().getXMLImportViewPanelContent().getUI().getUI().addWindow(new AlertWindow(
							"!!! Erreur !!!", "Le fichier que vous voulez télécharger ne possède pas la bonne date"));

					DeleteFile(event.getFilename());

				}
			}
		}

		if (event.getFilename().equals("ElevesAvecAdresses.xml")) {
			setEleveAvecAdresse(true);
			BuildLayout();
		}

		if (event.getFilename().equals("Nomenclature.xml")) {
			setNomenclature(true);
			BuildLayout();
		}

		if (event.getFilename().equals("Structures.xml")) {
			setStructures(true);
			BuildLayout();
		}

		if (event.getFilename().equals("Etablissements.xml")) {
			setEtablissements(true);
			BuildLayout();
		}

		if (event.getFilename().equals("Geographique.xml")) {
			setGeographique(true);
			BuildLayout();
		}

	}

}
