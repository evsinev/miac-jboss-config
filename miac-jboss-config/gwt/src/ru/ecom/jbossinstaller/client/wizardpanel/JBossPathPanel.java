package ru.ecom.jbossinstaller.client.wizardpanel;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.core.client.GWT;
import ru.ecom.jbossinstaller.client.service.ConfigModel;
import ru.ecom.jbossinstaller.client.GwtInjection;
import ru.ecom.jbossinstaller.client.BaseAsyncCallback;

/**
 * Путь к JBoss
 */
public class JBossPathPanel extends AbstractWizardPanel {

    public String getName() {
        return "JBoss" ;
    }


    public void load(ConfigModel aModel) {
        if(box.getText()==null || "".equals(box.getText())) {
            theService.findJbossHomeDir(new BaseAsyncCallback() {
                public void onSuccess(Object object) {
                    box.setText((String) object);
                }
            });
        }
    }

    public void save(ConfigModel aModel) {
        aModel.setJbossHomeDir(box.getText());
        GWT.log(box.getText(), null);
    }

    public Panel getPanel() {
        FlowPanel panel = new FlowPanel();
        panel.add(new Label("Домашний каталог JBoss:")) ;
        //box.setText("/opt/jboss-4.2.2GA");
        box.setVisibleLength(40);
        panel.add(box) ;
        return panel;
    }

    private final TextBox box = new TextBox() ;
}
