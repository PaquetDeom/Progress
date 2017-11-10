package fr.paquet.ihm.Import;

import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

import fr.paquet.ihm.AlertListener;

@SuppressWarnings("serial")
public class WindowImport extends Window {

	private VerticalLayout mainLayout = null;
		

	public WindowImport(VerticalLayout layout) {
		super();		
		setMainLayout(layout);
		BuildWindow();
	}
	
	private void setMainLayout(VerticalLayout layout) {
		this.mainLayout = layout;
	}

	private VerticalLayout getMainLayout() {
		return mainLayout;
	}

	private void BuildWindow() {

		setCaption("integration des fichiers dans base");
		setSizeUndefined();
		setWidth(600.0f, Unit.PIXELS);
		center();
		setModal(true);

		final FormLayout content = new FormLayout();
		content.setMargin(true);
		VerticalLayout layout = new VerticalLayout();

		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.setSpacing(true);
		
		hLayout.addComponent(getOkButton());
		hLayout.addComponent(getAnnulButton());

		layout.addComponent(getMainLayout());
		layout.addComponent(hLayout);
		content.addComponent(layout);
		setContent(content);

		setVisible(true);

	}

	private Button getOkButton() {
		
		Button button = new Button("Valider");
		
		button.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		});

		return button;
	}

	private Button getAnnulButton() {

		Button button = new Button("Annuler");

		button.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				close();

			}
		});

		return button;
	}

}
