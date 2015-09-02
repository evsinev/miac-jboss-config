#Отладка приложения на стороне jboss из eclipse

## Включение отладчика на стороне jboss ##
В файле jboss/bin/run.sh
добавить
```
export JAVA_OPTS="-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=12345"
```


## Подключение отладчиком eclipse к jboss ##
  1. Выбираем пункт меню **Run/Open Run Dialog...**
  1. Выбираем **Remote Java Application**
  1. Создаем новую конфигурацию, нажимая на кнопку **New launch configuration**
  1. В **Connection Properties** в поле **Port** устанавливаем **12345**
  1. Выбираем проект для отладки
  1. Запускаем

## Работа с отладчиком ##
Добавляем точку останова **CTRL-SHIFT-B** в коде.

http://miac-jboss-config.googlecode.com/files/miac-debug.PNG