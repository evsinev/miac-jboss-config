## Install jdk 1.5.0 ##
```
cd /opt
JDKURL1='http://192.18.108.137/ECom/EComTicketServlet/BEGINC0A36368669DA02DE4700BE423A6B156/'
JDKURL2='-2147483648/2611528743/1/878882/878738/2611528743/2ts+/westCoastFSEND/'
JDKURL3='jdk-1.5.0_15-B-oth-JPR/jdk-1.5.0_15-B-oth-JPR:5/jdk-1_5_0_15-linux-i586.bin'
wget -c -O jdk-1_5_0_15-linux-i586.bin "${JDKURL1}${JDKURL2}${JDKURL3}"
sh ./jdk-1_5_0_15-linux-i586.bin
```

## Install daemontools ##

```
mkdir -p /package
chmod 1755 /package
cd /package
wget http://cr.yp.to/daemontools/daemontools-0.76.tar.gz
wget -O daemontools-0.76.errno.patch http://miac-jboss-config.googlecode.com/files/daemontools-0.76.errno.patch?id=2
tar -xzpf daemontools-0.76.tar.gz
cd admin
patch -p0 < ../daemontools-0.76.errno.patch
cd daemontools-0.76
package/install
```

## Install jboss 4.2.2 ##
```
cd /opt
wget http://garr.dl.sourceforge.net/sourceforge/jboss/jboss-4.2.2.GA.zip
jar xf jboss-4.2.2.GA.zip
```

## Create jboss service for daemontools ##

```
groupadd jboss
useradd -g jboss jboss
chown -R jboss:jboss /opt/jboss-4.2.2.GA
mkdir /var/log/jboss
chown -R jboss:jboss /var/log/jboss
```

**/service/jboss/run**
```
#!/bin/sh
export JAVA_HOME="/opt/jdk1.5.0_15"
export JAVA_OPTS="-Xmx1024M -Xms256M -server" 
exec 2>&1
exec setuidgid jboss /opt/jboss-4.2.2.GA/bin/run.sh
```

**/service/jboss/log/run**
```
#!/bin/sh
exec setuidgid jboss multilog /var/log/jboss
```