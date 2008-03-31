package ru.ecom.jbossinstaller.client.service;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Данные о настройки
 */
public class ConfigModel implements IsSerializable {

    public String getJbossHomeDir() {
        return theJbossHomeDir;
    }

    public void setJbossHomeDir(String aJbossHomeDir) {
        theJbossHomeDir = aJbossHomeDir;
    }


    public DatasourceInfo getDataSourceInfo() {
        if(theDataSourceInfo==null) {
            theDataSourceInfo = new DatasourceInfo();
        }
        return theDataSourceInfo;
    }

    public void setDataSourceInfo(DatasourceInfo aDataSourceInfo) {
        theDataSourceInfo = aDataSourceInfo;
    }

    private DatasourceInfo theDataSourceInfo ;
    private String theJbossHomeDir;
}
