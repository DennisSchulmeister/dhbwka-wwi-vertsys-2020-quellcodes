Diese Beispielanwendung orientiert sich am Tutorial von https://howtodoinjava.com/spring5/hateoas/spring-hateoas-tutorial/ , verwendet aber die aktuellere API und die Navigation vom API Root.

Es ist ein Integrationstest angehaengt, welcher den Aufruf der API demonstriert: UserToGroupExpansionTest.
Dieser erfordert, dass die Application gestartet wurde. Daher wird das Projekt auf der Konsole ohne Test ausgefuehrt mit: mvn clean package -Dmaven.test.skip=true

Die API Root fuer HAL Navigation kann im Browser aufgerufen werden ueber: http://loclahost:8080/api