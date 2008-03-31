package ru.ecom.jbossinstaller.client.wizardpanel;

import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.Window;
import ru.ecom.jbossinstaller.client.service.ConfigModel;
import ru.ecom.jbossinstaller.client.service.DatasourceInfo;
import ru.ecom.jbossinstaller.client.BaseAsyncCallback;

/**
 * Подключение к базе
 */
public class DatabasePanel extends AbstractWizardPanel {
    private static final String CSS_STYLE_DATABASE_PANEL = "databasePanel";

    public String getName() {
        return "Подключение к базе" ;
    }


    public void load(ConfigModel aModel) {
        if(isEmpty(boxHost) || isEmpty(boxName) || isEmpty(boxPort)
                || isEmpty(boxUsername) || isEmpty(boxPassword)
                || isEmpty(boxJdbcDriver)) {
            theService.findCurrentDatasource(aModel.getJbossHomeDir(), new BaseAsyncCallback() {
                public void onSuccess(Object object) {
                    DatasourceInfo info = (DatasourceInfo) object;
                    setValue(boxHost, info.getHostname()) ;
                    setValue(boxJdbcDriver, info.getJdbcDriverClassname());
                    setValue(boxPort, info.getPort()+"") ;
                    setValue(boxName, info.getDatabaseName()) ;
                    setValue(boxUsername, info.getUsername()) ;
                    setValue(boxPassword, info.getPassword()) ;
                }
            });
        }
    }

    private void setValue(TextBox aBox, String aValue) {
        if(isEmpty(aBox)) {
            aBox.setText(aValue);
        }
    }

    private boolean isEmpty(TextBox aBox) {
        return aBox.getText()==null || "".equals(aBox.getText().trim()) ;
    }

    public void save(ConfigModel aModel) {
        DatasourceInfo info = aModel.getDataSourceInfo();
        saveToDatasource(info);
    }

    private void saveToDatasource(DatasourceInfo aInfo) {
        aInfo.setHostname(boxHost.getText());
        aInfo.setDatabaseName(boxName.getText());
        aInfo.setPassword(boxPassword.getText());
        aInfo.setUsername(boxUsername.getText());
        aInfo.setPort(Integer.parseInt(boxPort.getText()));
        aInfo.setJdbcDriverClassname(boxJdbcDriver.getText());
    }

    private void add(TextBox aBox, String aName, String aDefaultValue, int aSize) {
        thePanel.add(new Label(aName+":")) ;
        aBox.setVisibleLength(aSize);
        thePanel.add(aBox) ;
    }
    
    public Panel getPanel() {
        thePanel.setStyleName(CSS_STYLE_DATABASE_PANEL);
        add(boxJdbcDriver, "JDBC драйвер", "com.intersystems.Driver", 40) ;
        add(boxHost, "IP адрес", "127.0.0.1", 20) ;
        add(boxName, "Название базы данных (namespace для Intersystems Cache)", "RIAMS", 20) ;
        add(boxPort, "TCP порт", "1273", 10) ;
        add(boxUsername, "Имя пользователя", "_sysdba", 20) ;
        add(boxPassword, "Пароль", "system", 20) ;
        thePanel.add(new Button("Протестировать подключение", new ClickListener() {
            public void onClick(Widget widget) {
                DatasourceInfo info = new DatasourceInfo();
                saveToDatasource(info);
                theService.testConnection(info, new BaseAsyncCallback() {
                    public void onSuccess(Object object) {
                        Window.alert("Подключение к базе данных работает.");
                    }
                });
            }
        })) ;
        return thePanel ;
    }

    private TextBox boxHost = new TextBox();
    private TextBox boxName = new TextBox();
    private TextBox boxPort = new TextBox();
    private TextBox boxUsername = new TextBox();
    private TextBox boxPassword = new TextBox();
    private TextBox boxJdbcDriver = new TextBox();

    private final VerticalPanel thePanel = new VerticalPanel();
}
