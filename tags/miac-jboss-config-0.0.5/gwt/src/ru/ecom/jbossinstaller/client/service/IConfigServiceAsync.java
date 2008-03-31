package ru.ecom.jbossinstaller.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Сервис настройки
 */
public interface IConfigServiceAsync {

    /**
     * Поиск jboss
     */
    void findJbossHomeDir(AsyncCallback async);

    /**
     * Настройка jboss
     *
     * @param aModel
     * @return возвращает простенькое сообщение об изменениях
     * @throws ru.ecom.jbossinstaller.client.service.ConfigException
     *
     */
    void config(ConfigModel aModel, AsyncCallback async);

    /**
     * Получение текущего datasource
     *
     * @param aJbossHomeDir домашний каталог jboss
     * @return параметры подключения
     */
    void findCurrentDatasource(String aJbossHomeDir, AsyncCallback async);

    /**
     * Проверка поключения к базе.
     *
     * @param aDatasourceInfo параметры подключения
     * @throws ru.ecom.jbossinstaller.client.service.ConfigException
     *
     */
    void testConnection(DatasourceInfo aDatasourceInfo, AsyncCallback async);
}
