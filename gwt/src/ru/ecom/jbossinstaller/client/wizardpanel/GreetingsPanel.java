package ru.ecom.jbossinstaller.client.wizardpanel;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * Приветствие
 */
public class GreetingsPanel extends AbstractWizardPanel {

    public String getName() {
        return "Приветствие" ;
    }

    public Panel getPanel() {
        Panel panel = new FlowPanel() ;
        Label label = new Label("Установка");
        panel.add(label) ;
        return panel ;
    }

}
