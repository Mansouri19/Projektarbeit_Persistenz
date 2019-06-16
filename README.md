# Projektarbeit_Persistenz
## Verwendete Softwareprogramme und Technologien 
-	Java SE 8
-	Eclipse IDE: 2018-12
-	MySQL: 8.0.16 (MySQL Community Server - GLP)
-	HIBERNATE : ORM 5.2
-	JPA: Java Persistence API 2.1

## Projekt - Aufgaben

### I.	Überblick über JPA und Hibernate

##### •	JPA ist eine Java-API-Spezifikation für die Verwaltung relationaler Daten in Anwendungen, die Java SE und Java EE verwenden.
##### •	Verwendete Version: JPA 2.1
##### •	API Package: javax.persistence
##### •	Java Persistence Query Language (JPQL)
##### •	Implementierungen: Hibernate, EclipseLink, OpenJPA…
##### •	Hibernate ist ein Object-Relational Mapping Framework (ORM), ein Anbieter von  JPA.

### II.	Eine MySQL Datenbank erstellen

##### •	Datenbank Name:
##### •	Tabelle Name:

### III.	Java Maven Project in Eclipse einrichten 
##### •	Maven Projekt erstellen: In Eclipse File > new > Maven Project
###### o	Projekt Name: KundenVerwaltung_Maven_JPA_MySQL
###### o	Group Id: org.development
###### o	Artifact Id: KundenVerwaltung_Maven_JPA_MySQL
###### o	JRE System Library anpassen: Rechtsmaus klicken > Properties > Execution enviroment : > JavaSE-1.8 auswählen > Apply
##### •	Dependencies konfigurieren für :
###### o	Hibernate (ORM), Artifact Id: hibernate-core
###### o	MySQL Connector Java (JDBC Driver), Artifact Id: mysql-connector-java
###### o	pmo.xml > Dependencies
#######  Browser: https://search.maven.org/ > search> hibernate-core
       org.hibernate:hibernate-core:5.2.12.Final
#######  mysql:mysql-connector-java:8.0.16

### IV.	Java Model Class +  JPA Annotations erstellen
##### •	Verwendung der JPA Annotations
###### o	@Entity
###### o	@Id
###### o	GeneratedValue
###### o	@Table
###### o	@Column

### V.	JPA Configuration File erstellen
##### •	persistence.xml erstellen
##### •	META-INF Datei in src/main/resources erstellen
##### •	META-INF > new > XML File > File nmae: persistence.xml > Source
     JPA persistence schemas einfügen:
      <persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence"
	            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence
          http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd"
	   version="2.1">

### VI.	Verwendung der EntityManager and EntityManagerFactory
### VII.	Testprogramm


