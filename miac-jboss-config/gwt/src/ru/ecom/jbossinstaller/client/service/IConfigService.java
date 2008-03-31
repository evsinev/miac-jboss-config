package ru.ecom.jbossinstaller.client.service;

import com.google.gwt.user.client.rpc.RemoteService;

/**
 * Сервис настройки
 */
public interface IConfigService extends RemoteService {


    /**
     * Поиск jboss
     */
    String findJbossHomeDir() ;

    /**
     * Настройка jboss
     * @param aModel
     * @return возвращает простенькое сообщение об изменениях
     * @throws ConfigException
     */
    String config(ConfigModel aModel) throws ConfigException ;

    /**
     * Получение текущего datasource
     * @param aJbossHomeDir домашний каталог jboss
     * @return параметры подключения
     */
    DatasourceInfo findCurrentDatasource(String aJbossHomeDir) ;

    /**
     * Проверка поключения к базе.
     * @param aDatasourceInfo параметры подключения
     * @throws ConfigException
     */
    void testConnection(DatasourceInfo aDatasourceInfo) throws ConfigException ;
}
