package ru.ecom.jbossinstaller.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.Window;

import java.util.List;
import java.util.ArrayList;

import ru.ecom.jbossinstaller.client.wizardpanel.*;
import ru.ecom.jbossinstaller.client.service.ConfigModel;
import ru.ecom.jbossinstaller.client.service.IConfigServiceAsync;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class JBossInstaller implements EntryPoint {

    private static final String CSS_STYLE_SELECTED    = "selected"      ;
    private static final String CSS_ID_WIZARD         = "wizard"        ;
    private static final String CSS_ID_SIDE_LABELS    = "sideLabels"    ;
    private static final String CSS_ID_TITLE_LABEL    = "titleLabel"    ;
    private static final String CSS_ID_WIZARD_BUTTONS = "wizardButtons" ;


    public JBossInstaller() {
        theConfigService = new GwtInjection().getService() ;

        theWizardPanels = new ArrayList();
        theWizardPanels.add(new GreetingsPanel()) ;
        theWizardPanels.add(new JBossPathPanel()) ;
        theWizardPanels.add(new DatabasePanel()) ;
        theWizardPanels.add(new InstallPanel()) ;

        theDeckPanel = new DeckPanel();
        theSideLabelsPanel = new VerticalPanel();
        for(int i=0; i<theWizardPanels.size(); i++) {
            IWizardPanel wp = (IWizardPanel) theWizardPanels.get(i);
            theDeckPanel.add(wp.getPanel());
            theSideLabelsPanel.add(new Label(wp.getName()));
            wp.setService(theConfigService);
        }

        theTitleLabel = new Label("JBossInstaller");

    }

    private void showNext() {
        if(theCurrent < theWizardPanels.size() - 1 ) {
            show(theCurrent+1) ;
        }
    }

    private void showPrevious() {
        if(theCurrent > 0) {
            show(theCurrent-1) ;
        }
    }

    private void show(int aIndex) {
        // save previous
        IWizardPanel previousWp = (IWizardPanel) theWizardPanels.get(theCurrent);
        previousWp.save(theModel);

        theDeckPanel.showWidget(aIndex);
        for(int i=0; i<theSideLabelsPanel.getWidgetCount(); i++) {
            Label label = (Label) theSideLabelsPanel.getWidget(i);
            label.removeStyleName(CSS_STYLE_SELECTED);
        }
        Label label = (Label) theSideLabelsPanel.getWidget(aIndex);
        label.setStyleName(CSS_STYLE_SELECTED);
        IWizardPanel wp = (IWizardPanel) theWizardPanels.get(aIndex);
        wp.load(theModel);
        theTitleLabel.setText(wp.getName());
        theCurrent = aIndex ;
        thePreviousButton.setEnabled(true);
        theNextButton.setEnabled(true);
        theNextButton.setText("Дальше");
        if(theCurrent==0) {
            thePreviousButton.setEnabled(false);
        } else if(theCurrent==theWizardPanels.size()-1 ) {
            theNextButton.setEnabled(false);
            thePreviousButton.setEnabled(false);
            theNextButton.setText("Установка...");
            theConfigService.config(theModel, new DoConfigAction());
        } else if(theCurrent==theWizardPanels.size()-2 ) {
            theNextButton.setText("Установить");
        }
    }

    private void installWizard() {
        show(0) ;
        RootPanel.get(CSS_ID_WIZARD).add(theDeckPanel) ;
        RootPanel.get(CSS_ID_SIDE_LABELS).add(theSideLabelsPanel) ;
        RootPanel.get(CSS_ID_TITLE_LABEL).add(theTitleLabel) ;

        HorizontalPanel buttonPanel = new HorizontalPanel();
        buttonPanel.add(thePreviousButton) ;
        buttonPanel.add(theNextButton) ;
        RootPanel.get(CSS_ID_WIZARD_BUTTONS).add(buttonPanel) ;

    }
    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        installWizard();
    }

    private class NextAction implements ClickListener {
        public void onClick(Widget sender) {
            showNext() ;
        }
    }

    private class PreviousAction implements ClickListener {
        public void onClick(Widget sender) {
            showPrevious() ;
        }
    }

    private class DoConfigAction extends BaseAsyncCallback {
        public void onSuccess(Object object) {
            show(0);
//            thePreviousButton.setEnabled(true);
//            theNextButton.setText("Завершить");
            Window.alert(""+object);
        }
    }

    private final List theWizardPanels ;
    private final DeckPanel theDeckPanel ;
    private final VerticalPanel theSideLabelsPanel;
    private final Label theTitleLabel ;
    private int theCurrent = 0 ;
    private final Button theNextButton = new Button("Дальше", new NextAction());
    private final Button thePreviousButton = new Button("Назад", new PreviousAction());
    private final ConfigModel theModel = new ConfigModel();
    private final IConfigServiceAsync theConfigService ;
}
