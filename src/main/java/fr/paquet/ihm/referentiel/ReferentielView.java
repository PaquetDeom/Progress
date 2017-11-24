package fr.paquet.ihm.referentiel;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

import fr.paquet.commun.Diplome;
import fr.paquet.commun.DiplomeFactory;
import fr.paquet.dataBase.Connect;
import fr.paquet.framework.ui.ProgView;
import fr.paquet.ihm.AlertWindow;
import fr.paquet.ihm.referentiel.CreationWindow;
import fr.paquet.sequence.Sequence;
import fr.paquet.sequence.SequenceFactory;

@SuppressWarnings("serial")
public class ReferentielView extends AbsoluteLayout implements ProgView {

	private VerticalLayout referentielViewPanelContent = null;

	private Diplome diplome = null;

	private void buildPanelContent() {

		referentielViewPanelContent = new VerticalLayout();

	}

	/**
	 * 
	 * @return Le layout pricipal de ReferentielView<br/>
	 */
	public VerticalLayout getReferentielViewPanelContent() {

		if (referentielViewPanelContent == null)
			buildPanelContent();

		return referentielViewPanelContent;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {

		return "Referentiel";
	}

	@Override
	public String getCaption() {
		return "Referentiel";
	}

	public ReferentielView() {
		super();
		buildView();
	}

	public void buildView() {

		setSizeFull();

		removeAllComponents();

		VerticalLayout layout = new VerticalLayout();
		layout.addComponent(getDetail());
		addComponent(layout);

	}

	public Component getDetail() {

		Panel pan = new Panel();
		pan.setCaption("Accueil - Referentiel");
		VerticalLayout panelContent = getReferentielViewPanelContent();
		pan.setContent(panelContent);

		HorizontalLayout layoutCreation = new HorizontalLayout();
		layoutCreation.setCaption("Saisi d'un referentiel");

		Button saisir = new Button();
		saisir.setCaption("Saisir");
		layoutCreation.addComponent(saisir);

		saisir.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				try {

					getReferentielViewPanelContent().getUI().addWindow(new CreationWindow(ReferentielView.this));

				} catch (Exception e) {

					getReferentielViewPanelContent().getUI().getUI()
							.addWindow(new AlertWindow("Erreur !!!", e.getMessage()).show());
				}

			}
		});

		HorizontalLayout layoutConsultation = new HorizontalLayout();
		layoutConsultation.setCaption("Consultation de referentiel");

		Button consulter = new Button();
		consulter.setCaption("Consulter");
		layoutConsultation.addComponent(consulter);

		panelContent.addComponent(layoutCreation);
		panelContent.addComponent(layoutConsultation);

		return pan;
	}

	public Component getListDiplomes() throws Exception {

		List<Diplome> dips = new DiplomeFactory().findDiplomes();
		
		IntStream.range(0, dips.size()).mapToObj(i -> {
			try {
				return dips.get(i);
			} catch (Exception e) {

				getReferentielViewPanelContent().getUI().getUI()
						.addWindow(new AlertWindow("Erreur !!!", e.getMessage()).show());

			}
			return null;
		}).collect(Collectors.toList());

		ListSelect choixDiplome = new ListSelect("Selectionner un diplome", dips);
		choixDiplome.setRows(1);
		choixDiplome.select(dips.get(0));
		choixDiplome.setWidth(100.0f, Unit.PERCENTAGE);
		choixDiplome.setMultiSelect(false);
		setDiplome(dips.get(0));

		choixDiplome.addValueChangeListener(new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {

				Diplome set01 = (Diplome) event.getProperty().getValue();

				if (set01 != null) {

					try {

						setDiplome(set01);

					} catch (Exception e) {

						getReferentielViewPanelContent().getUI().getUI()
								.addWindow(new AlertWindow("Erreur !!!", e.getMessage()).show());
					}

				}
			}
		});

		return choixDiplome;

	}

	public void setDiplome(Diplome dip) {
		this.diplome = dip;
	}

	public Diplome getDiplome() {

		return diplome;
	}

}
