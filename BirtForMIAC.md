## Required software ##


  1. [BIRT Report Engine](http://www.eclipse.org/downloads/download.php?file=/birt/downloads/drops/R-R1-2_2_2-200802271210/birt-runtime-2_2_2.zip)
  1. [VJDBC driver with EJB3 supports](http://code.google.com/p/miac-jboss-config/downloads/list?q=label:vjdbc)
  1. [Apache Tomcat > 5.5](http://tomcat.apache.org/)
  1. MIAC web application (riams.war)

## Download all needed software ##
```
export BIRT_DIST=/tmp/birt222
mkdir $BIRT_DIST
cd $BIRT_DIST

# BIRT 2.2.2
wget -c 'http://www.eclipse.org/downloads/download.php?file=/birt/downloads/drops/R-R1-2_2_2-200802271210/birt-runtime-2_2_2.zip&url=http://eclipse.i-logic.hu//birt/downloads/drops/R-R1-2_2_2-200802271210/birt-runtime-2_2_2.zip&mirror_id=70'

# VJDBC ejb3 data provider for MIAC
wget -c 'http://miac-jboss-config.googlecode.com/files/vjdbc3.ear'

# Russian transation for BIRT
mkdir -p org/eclipse/birt/report/resource
wget -c http://miac-jboss-config.googlecode.com/files/Messages_ru_RU.properties -O org/eclipse/birt/report/resource/Messages_ru_RU.properties

# vjdbc supports for BIRT
wget http://miac-jboss-config.googlecode.com/files/vjdbc.jar
wget http://miac-jboss-config.googlecode.com/files/vjdbc-context-1.0.2.jar
wget http://miac-jboss-config.googlecode.com/files/vjdbc-postgres.jar

# BIRT 2.2 web.xml
wget http://miac-jboss-config.googlecode.com/files/birt222-web.xml

# New version of apache commons logging 1.1.1
wget http://www.sai.msu.su/apache/commons/logging/binaries/commons-logging-1.1.1-bin.zip

# JBoss class loader to work with BIRT osgi bundle protocol
wget http://miac-jboss-config.googlecode.com/files/jboss_osgi_bundle_fix.jar

# RIAMS war
cp ${SOMEPLACE}/riams.war .

```
## Install vjdbc application on jboss ##

```
JBOSS_HOME=/opt/jboss4.2.2-GA
cp ${$BIRT_DIST}/vjdbc3.ear ${JBOSS_HOME}/server/default/deploy/ecom/
```

## Patch jboss class loader to work with BIRT osgi bundle protocol ##
```
cd $BIRT_DIST
jar xf jboss_osgi_bundle_fix.jar
jar uf ${JBOSS_HOME}/bin/run.jar ru
```

For JBoss 4.0.4-GA in file ${JBOSS\_HOME}/server/default/deploy/dynclassloader-service.xml and in JBoss 4.2.2-GA in file ${JBOSS\_HOME}/server/default/conf/jboss-service.xml replace
```
from:
<attribute name="ClassName">org.jboss.system.JBossRMIClassLoader</attribute> 
to: 
<attribute name="ClassName">ru.ecom.jboss.system.RMIClassLoaderBundleEntryFix</attribute>
```


## Install BIRT Report engine to tomcat ##
Extract birt-runtime-2\_2\_2.zip/birt-runtime-2\_2\_2/birt.war to ${TOMCAT\_HOME}/webapps/birt
```
jar xf ${BIRT_DIST}/birt-runtime-2_2_2.zip birt-runtime-2_2_2/birt.war
mkdir ${TOMCAT_HOME}/webapps/birt
cd ${TOMCAT_HOME}/webapps/birt
jar xf ${BIRT_DIST}/birt-runtime-2_2_2/birt.war
```

## Transate to russian ##

Copy Messages\_ru\_RU.properties to ${TOMCAT\_HOME}/webapps/birt/WEB-INF/lib/viewservlets.jar into /org/eclipse/birt/report/resource directory of this jar file
```
cd ${BIRT_DIST}
jar uf ${TOMCAT_HOME}/webapps/birt/WEB-INF/lib/viewservlets.jar org
```

## Install MIAC logon context ##

```
# Copy /WEB-INF/lib/*.jar from riams.war to ${TOMCAT_HOME}/webapps/birt/WEB-INF/lib/
# Note: do not copy js.jar
mkdir $BIRT_DIST/riams
cd $BIRT_DIST/riams
jar xf ../riams.war
rm $BIRT_DIST/riams/WEB-INF/lib/js.jar
rm $BIRT_DIST/riams/WEB-INF/lib/commons-logging.jar
cp WEB-INF/lib/*.jar ${TOMCAT_HOME}/webapps/birt/WEB-INF/lib/

cd $BIRT_DIST
jar xf commons-logging-1.1.1-bin.zip
cp commons-logging-1.1.1/commons-logging-1.1.1.jar ${TOMCAT_HOME}/webapps/birt/WEB-INF/lib/

cp riams/WEB-INF/lib/*.jar ${TOMCAT_HOME}/webapps/birt/WEB-INF/platform/plugins/org.eclipse.birt.report.data.oda.jdbc_2.2.2.r22x_v20071206/drivers/
cp commons-logging-1.1.1/commons-logging-1.1.1.jar ${TOMCAT_HOME}/webapps/birt/WEB-INF/platform/plugins/org.eclipse.birt.report.data.oda.jdbc_2.2.2.r22x_v20071206/drivers/

# Copy struts and dwr configs

cp $BIRT_DIST/riams/WEB-INF/config-ecom.xml ${TOMCAT_HOME}/webapps/birt/WEB-INF/
cp $BIRT_DIST/riams/WEB-INF/dwr-ecom.xml ${TOMCAT_HOME}/webapps/birt/WEB-INF/

# Copy vjdbc supports for BIRT

cp $BIRT_DIST/vjdbc*.jar ${TOMCAT_HOME}/webapps/birt/WEB-INF/lib/
cp $BIRT_DIST/vjdbc*.jar ${TOMCAT_HOME}/webapps/birt/WEB-INF/platform/plugins/org.eclipse.birt.report.data.oda.jdbc_2.2.2.r22x_v20071206/drivers/

# Copy jsp for logon screen
cp -R $BIRT_DIST/riams/WEB-INF/tiles ${TOMCAT_HOME}/webapps/birt/WEB-INF/
mkdir -p ${TOMCAT_HOME}/webapps/birt/WEB-INF/actions/ecom_login
cp $BIRT_DIST/riams/WEB-INF/actions/ecom_login/login.jsp ${TOMCAT_HOME}/webapps/birt/WEB-INF/actions/ecom_login
cp -R $BIRT_DIST/riams/index.jsp ${TOMCAT_HOME}/webapps/birt/index.jsp

# Copy riams version
cp $BIRT_DIST/riams/WEB-INF/buildnumber.txt ${TOMCAT_HOME}/webapps/birt/WEB-INF/


# Copy BIRT 2.2 web.xml to work with MIAC auth context
cp $BIRT_DIST/birt222-web.xml ${TOMCAT_HOME}/webapps/birt/WEB-INF/web.xml
```

## Create start page ##
Create ${TOMCAT\_HOME}/WEB-INF/actions/start.jsp
```
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.nuzmsh.ru/tags/msh" prefix="msh" %>

<tiles:insert page="/WEB-INF/tiles/mainLayout.jsp" flush="true">

    <tiles:put name='title' type='string'>
        <msh:title mainMenu="Report">Отчеты</msh:title>
    </tiles:put>

    <tiles:put name='body' type='string'>
        <table class='mainMenu'>
        <tr>
        <td class='col1'>
            <div class='menu'>
                <h2>Список доступных отчетов</h2>
                <ul>
                    <li><a href='frameset?__report=main.rptdesign'>Отчет по категориям льготников</a></li>
                </ul>
            </div>
        </td>
        </tr>
        </table>
    </tiles:put>
</tiles:insert>
```

## Edit main menu ##
In file ${TOMCAT\_HOME}/webapps/birt/WEB-INF/tiles/mainLayout.jsp edit all msh:sideLink tags
```
from 
 <msh:sideLink params="" styleId="mainMenuLpu" action="/entityParentList-mis_lpu.do?id=-1" name="ЛПУ"
                          title="Список ЛПУ" roles="/Policy/Mis/MisLpu/View"/>
to
 <msh:sideLink params="" styleId="mainMenuLpu" action="../../riams/entityParentList-mis_lpu.do?id=-1" name="ЛПУ"
                          title="Список ЛПУ" roles="/Policy/Mis/MisLpu/View"/>
```