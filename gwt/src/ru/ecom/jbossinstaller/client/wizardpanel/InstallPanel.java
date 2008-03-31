package ru.ecom.jbossinstaller.client.wizardpanel;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;

/**
 * Установка
 */
public class InstallPanel extends AbstractWizardPanel {
    public String getName() {
        return "Установка";
    }

    public Panel getPanel() {
        FlowPanel panel = new FlowPanel();
        Image image = new Image("loading.gif");
        panel.add(image) ;
        return panel;
    }
}
