# Projektarbeit_Persistenz

## Einleitung
 In diesem Projekt werden drei Hauptanforderungen behandelt, nämlich Java Persistence API, Maven und Hibernate. Der Projektprozess wird mit Maven 
 eingerichtet, in dem Abhängigkeiten (dependencies) hinzugefügt werden.
 
 Anschließend wird anhand einer Reihe von Klassen und Methoden gezeigt, wie Hibernate das Hinzufügen, Ändern, Entfernen und Abrufen von Objektdaten 
 aus einer MySQL-Datenbank behandelt.
 
## Verwendete Softwareprogramme und Technologien 
- Git Bash - GitHub
- Java SE 8
- Eclipse IDE: 2018-12
- MySQL: 8.0.16 (MySQL Community Server - GLP)
- HIBERNATE : ORM 5.2
- JPA: Java Persistence API 2.1
- (JUnit 5)
- (Swing oder Javafx)
  
## Projekt - Aufgaben
### 1.	Überblick über JPA und Hibernate
- JPA ist eine Java-API-Spezifikation für die Verwaltung relationaler Daten in Anwendungen, die Java SE und Java EE verwenden.
- Verwendete Version: JPA 2.1
- API Package: javax.persistence
- Java Persistence Query Language (JPQL)
- Implementierungen: Hibernate, EclipseLink, OpenJPA…
- Hibernate ist ein Object-Relational Mapping Framework (ORM), ein Anbieter von  JPA.
- Hibernate verwendet Hibernate Query Language (HQL) zum Abfragen von Daten.

### 2.	Eine MySQL Datenbank erstellen
- Datenbank erstellen: create database kundenverwaltungdb
     - Tabelle erzeugen: SELECT * FROM kundenverwaltungdb.kunde;

      Beispiel: 
                CREATE TABLE `kunde` (
                 `kunde_id` int(11) NOT NULL,
                 `vorname` varchar(64) NOT NULL,
                 `nachname` varchar(128) NOT NULL,
                 `geburtsdatum` varchar(64) NOT NULL,
                 `adresse` varchar(128) NOT NULL,
                 `telNr` varchar(64) NOT NULL,
                 PRIMARY KEY (`kunde_id`)
                )

### 3. Java Maven Project in Eclipse einrichten 
- Maven Projekt erstellen: In Eclipse File -> new -> Maven Project
     - Projekt Name: KundenVerwaltung_Maven_JPA_MySQL (Beispiel)
     - Group Id: org.development
     - Artifact Id: KundenVerwaltung_Maven_JPA_MySQL
          - JRE System Library anpassen (Properties in pom.xml Datei einfügen):
	  
	             <properties>
		             <maven.compiler.source>1.8</maven.compiler.source>
		             <maven.compiler.target>1.8</maven.compiler.target>
	              </properties>
	         
           -> Tips: nach Import projects vom GitHub Respository -> Maus Rechtsklick > Maven > Update Project
	   
 - Dependencies konfigurieren für :
   - Hibernate (ORM)
    
              Browser: https://search.maven.org/ -> search -> hibernate-core
              org.hibernate:hibernate-core:5.2.12.Final
	           pmo.xml -> Dependencies
	           Artifact Id: hibernate-core
		
   - MySQL Connector Java (JDBC Driver)
    
         Artifact Id: mysql-connector-java
	      mysql:mysql-connector-java:8.0.16
     
   - Dependencies konfiguration in pom.xml sieht so aus :
   
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

### 4. Java Model Class +  JPA Annotations erstellen
- Verwendung der JPA Annotations

	   @Entity
	   @Id
	   @GeneratedValue
	   @Table
	   @Column

### 5.	JPA Configuration File erstellen
- persistence.xml erstellen
  
          META-INF Datei in src/main/resources erstellen
	  
- META-INF -> new -> XML File -> File name: persistence.xml -> Source
- JPA persistence schemas sowie persistence-unit und properties einfügen:
  
        Browser: JPA XML Schemas (Oracle) 
	    -> Datei: persistence_2_1.xsd öffnen -> Code-Teil kopieren und einfügen (siehe unten) 
	     
- Properties angeben :  
  
        JDBC: URL, User, Password
        Show und Format SQL  
	
- konfiguration in persistence.xml sieht so aus :    
 
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

### 6. Verwendung der EntityManager and EntityManagerFactory
- EntityManager
     - Ein Persistenzkontext ist eine Menge von Entities (Objekt der Modellklassen.
     - EntityManager wird verwendet, um mit dem Persistenzkontext zu interagieren (Datenbank).
     - Verwaltung der Entitätsinstanzen und deren Lebenszyklus (begin und end).
     - Verwendung der CRUD Methoden (create, update, remove, find, Query Entities). 
    
- EntityManagerFactory
     - Die EntityManagerFactory wird einer Persistenzeinheit zugeordnet.
     - Erzeugt eine EntityManager.

### 7. Testprogramm
- Schritte:
     - EntityManagerFactory erzeugen
     - EntityManager erzeugen 
     - Transaction beginnen 
     - Persist ein Objekt (Bsp. Book)
     - Commit die Transaction
     - Close die EntityManager
     - Close die EntityManagerFactory
   
### 8. Hibernate Lebenszyklus und CRUD Methoden  
   - Ein Objekt in Hibernate befindet sich in einem von vier Zuständen: Transient, Persistent, Removed oder Detached.
     -  Session Klasse von Hibernate verfügt über einige wichtige Methoden, die in Gruppen eingeteilt sind (siehe
        Diagramm daunten).	
	
   
						
   - Transient:
    
        Wenn ein neues Java-Objekt aus einer Entität erstellt wird, hat dieses Objekt den Status "Transient".
        Es wird nicht von Hibernate verwaltet.
        
   - Persistent:
    
        Wenn die Entity-Objekte mit den folgenden Methoden:(get, load, find, getSingleResult...) abgerufen werden, 
        wird ein Objekt erhalten, das einem Datensatz in der Datenbank entspricht. Dieses Objekt hat einen permanenten
        Status. Es wird von Hibernate verwaltet.
        
   - Removed:
   
        Das Session-Objekt ruft die Methode evict() oder clear() auf, um Objekte mit dem Status Persistent aus der
        Hibernate-Verwaltung zu entfernen. Diese Objekte haben nun einen neuen Status mit dem Namen Detached. Wenn
        es nicht erneut angehängt wird, wird es vom Java Garbage Gollector gemäß dem normalen Mechanismus entfernt.
        
        Mit einer der folgenden Methoden: update(), saveOrUpdate(), merge() nimmt ein Objekt mit dem Status Detached in
        die Verwaltung von Hibernate auf. Dies entspricht der Aktion "Aktualisieren" oder "Einfügen" unter "Datenbank".
        Das Objekt wechselt in den Persistent Zustand.
         
   - Detached:
   
        Das Session-Objekt ruft die Methoden: remove(), delete() um ein Objekt zu löschen. Persistent Objekt wird auf 
        den Removed Zustand wechseln.
   
         
   
### 9. Programmausführung
##### - Java-Projekt: KundenVerwaltung_Maven_JPA_MySQL

   - Kunde in Datenbank einfügen, aktualisieren, löschen, finden ..
   - Erstelle Kopie der Original-Kunden-Liste zur Bearbeitung.
   - Lifeziklus der Java-Objekte überprüfen.
   
         \src\main\java\development\project\KundenTest.java     
   - Programm in JUnit testen   
   
         \src\test\java\development\project\dao\model\KundeTest.java         
   - Kundeninformation mit Javafx anlegen
    
         \src\main\java\development\project\app_javafx\Start.java        
### 10. Tips
         - compiler error sign
         -> In Eclipse > window > Show View > Other > Problems

         - Java compiler level does not match the version of the installed Java project facet
         -> Right-click on your project - Click Properties - Click the "Project Facets" option on the left
            menu - Under Facets section "Java" on the right, change it to  "1.8" or your version... - Click Ok
                   




