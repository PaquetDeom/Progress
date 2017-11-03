package fr.paquet.ihm.Import;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class SiecleImport extends VerticalLayout implements SucceededListener {

	private XMLImportView xmlView = null;

	private File CommunsFile = null;
	private File EleveAvecAdresseFile = null;
	private File EtablissementsFile = null;
	private File GeographiqueFile = null;
	private File NomenclatureFile = null;
	private File StructuresFile = null;

	public SiecleImport(XMLImportView xml) {
		super();
		setXMLImportView(xml);
		setSizeFull();

		for (int i = 0; i < 5; i++) {

			Label label = new Label();
			Layout emptyScreen = new VerticalLayout();
			Upload upload = new Upload(null, new Upload.Receiver() {

				@Override
				public OutputStream receiveUpload(String filename, String mimeType) {

					File file = new File(getXmlView().getPathSiecleFolder() + filename);

					try {
						file.createNewFile();
						System.out.println("Creation du fichier " + file.getAbsolutePath());
						return new FileOutputStream(file);

					} catch (Exception e) {
						e.printStackTrace();
						return null;
					}
				}
			});

			switch (i) {
			case 0:
				label.setCaption("import du fichier (Communs.xml)");
				upload.addSucceededListener(this);
				addComponent(label);
				addComponent(upload);
				addComponent(emptyScreen);
				setExpandRatio(emptyScreen, 1.0f);
				break;
			case 1:
				label.setCaption("import du fichier (EleveAvecAdresse.xml)");
				upload.addSucceededListener(this);
				addComponent(label);
				addComponent(upload);
				addComponent(emptyScreen);
				setExpandRatio(emptyScreen, 1.0f);
				break;
			case 2:
				label.setCaption("import du fichier (Etablissements.xml)");
				upload.addSucceededListener(this);
				addComponent(label);
				addComponent(upload);
				addComponent(emptyScreen);
				setExpandRatio(emptyScreen, 1.0f);
				break;
			case 3:
				label.setCaption("import du fichier (Geographique.xml)");
				upload.addSucceededListener(this);
				addComponent(label);
				addComponent(upload);
				addComponent(emptyScreen);
				setExpandRatio(emptyScreen, 1.0f);
				break;
			case 4:
				label.setCaption("import du fichier (Nomenclature.xml)");
				upload.addSucceededListener(this);
				addComponent(label);
				addComponent(upload);
				addComponent(emptyScreen);
				setExpandRatio(emptyScreen, 1.0f);
				break;
			case 5:
				label.setCaption("import du fichier (Structures.xml)");
				upload.addSucceededListener(this);
				addComponent(label);
				addComponent(upload);
				addComponent(emptyScreen);
				setExpandRatio(emptyScreen, 1.0f);
				break;
			}

		}

	}

	private void setXMLImportView(XMLImportView xml) {
		this.xmlView = xml;

	}

	private XMLImportView getXmlView() {
		return xmlView;
	}

	@Override
	public void uploadSucceeded(SucceededEvent event) {
		// TODO Auto-generated method stub

	}

}
