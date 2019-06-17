# Projektarbeit_Persistenz
## Verwendete Softwareprogramme und Technologien 
##### •  Java SE 8
##### •  Eclipse IDE: 2018-12
##### •  MySQL: 8.0.16 (MySQL Community Server - GLP)
##### •  HIBERNATE : ORM 5.2
##### •  JPA: Java Persistence API 2.1
##### •  (JUnit 5)
##### •  (Swing oder Javafx)
  
## Projekt - Aufgaben

#### I.	Überblick über JPA und Hibernate

##### •	JPA ist eine Java-API-Spezifikation für die Verwaltung relationaler Daten in Anwendungen, die Java SE und Java EE verwenden.
##### •	Verwendete Version: JPA 2.1
##### •	API Package: javax.persistence
##### •	Java Persistence Query Language (JPQL)
##### •	Implementierungen: Hibernate, EclipseLink, OpenJPA…
##### •	Hibernate ist ein Object-Relational Mapping Framework (ORM), ein Anbieter von  JPA.

#### II.	Eine MySQL Datenbank erstellen

##### •	Datenbank Name:
##### •	Tabelle Name:

#### III. Java Maven Project in Eclipse einrichten 
##### •	Maven Projekt erstellen: In Eclipse File > new > Maven Project
       - Tips: nach Import projects vom GitHub Respository -> Maus Rechtsklick > Maven > Update Project
	    Projekt Name: KundenVerwaltung_Maven_JPA_MySQL (Beispiel)
	    Group Id: org.development
	    Artifact Id: KundenVerwaltung_Maven_JPA_MySQL
	   - JRE System Library anpassen: Rechtsmaus klicken > Properties > Execution enviroment : > JavaSE-1.8 auswählen > Apply
##### •	Dependencies konfigurieren für :
	 - Hibernate (ORM), Artifact Id: hibernate-core
	   MySQL Connector Java (JDBC Driver), Artifact Id: mysql-connector-java
	   pmo.xml > Dependencies
         Browser: https://search.maven.org/ > search> hibernate-core
         org.hibernate:hibernate-core:5.2.12.Final
         
     - mysql:mysql-connector-java:8.0.16

#### IV. Java Model Class +  JPA Annotations erstellen
##### •	Verwendung der JPA Annotations
	   @Entity
	   @Id
	   GeneratedValue
	   @Table
	   @Column

#### V.	JPA Configuration File erstellen
##### •	persistence.xml erstellen
      META-INF Datei in src/main/resources erstellen
##### •	META-INF > new > XML File > File nmae: persistence.xml > Source
     JPA persistence schemas einfügen:
      <persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence"
	            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence
          http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd"
	   version="2.1">

#### VI.	Verwendung der EntityManager and EntityManagerFactory
#### VII.	Testprogramm


