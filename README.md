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
     
##### •	Dependencies konfiguration in pom.xml sieht so aus :
        <project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>net.codejava</groupId>
	<artifactId>BooksManager</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<dependencies>
		<dependency>
			<groupId>org.hibernate</groupId>
			<artifactId>hibernate-core</artifactId>
			<version>5.2.12.Final</version>
		</dependency>
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>8.0.16</version>
		</dependency>
	</dependencies>
</project>

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
##### •	META-INF > new > XML File > File name: persistence.xml > Source
##### • JPA persistence schemas sowie persistence-unit und properties einfügen:
        Browser: JPA XML Schemas (Oracle) > Datei: persistence_2_1.xsd öffnen > Code-Teil kopieren und einfügen (siehe unten)  
##### • Properties angeben :  
        JDBC: URL, User, Password
        Show und Format SQL  
##### • konfiguration in persistence.xml sieht so aus :    
 
    <?xml version="1.0" encoding="UTF-8"?>
    <persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence
          http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd"
	version="2.1">
	<persistence-unit name="BookUnit">
		<properties>
			<property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/booksdb?serverTimezone=UTC" />
			<property name="javax.persistence.jdbc.user" value="root" />
			<property name="javax.persistence.jdbc.password" value="******" />
			<property name="javax.persistence.jdbc.driver" value="com.mysql.jdbc.Driver" />
            <property name="hibernate.show_sql" value="true"/>
            <property name="hibernate.format_sql" value="true"/>
		</properties>
	</persistence-unit>
</persistence>

#### VI.	Verwendung der EntityManager and EntityManagerFactory
##### • EntityManager
    - Ein Persistenzkontext ist eine Menge von Entities (Objekt der Modellklassen.
    - EntityManager wird verwendet, um mit dem Persistenzkontext zu interagieren (Datenbank).
    - Verwaltung der Entitätsinstanzen und deren Lebenszyklus.
    - Verwendung der CRUD Methoden (create, update, remove, find, Query Entities). 
    
##### • EntityManagerFactory
     - Die EntityManagerFactory wird einer Persistenzeinheit zugeordnet.
     - Erzeugt eine EntityManager.

#### VII.	Testprogramm


