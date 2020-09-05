# Tasklist-java-maven-hibernate


## Notes
```code
JPA o Java Persistence API
EntityManager será la clase que nos permitirá hacer transacciones con la base de datos, es decir, guardar entidades en base de datos, modificarlas, consultarlas, etc
persistence.xml se encarga de conectarnos a la base de datos y define el conjunto de entidades que vamos a gestionar

manager.find(Estados.class, id); //busca el objeto por el primary key

```
## Foregin Keys
<https://thorben-janssen.com/hibernate-tips-same-primary-key-one-to-one-association/>

## Generate project with Maven

```code
mvn archetype:generate -DarchetypeArtifactId=maven-archetype-quickstart -DgroupId=com.bootcamp.seatcode.mike -DartifactId=tasklist-java-maven-hibernate -DarchetypeVersion=1.4 -Dversion=1.0.0
```

|       Paramater      |    Description | Reference |
|----------------------|--------------------------------------------|-------------------------------------------------------------------|
| archetypeVersion=1.4 | last version of maven-archetype-quickstart | <https://maven.apache.org/archetypes/maven-archetype-quickstart/> |
| version=1.0.0        | Project version | |

## Edit pom.xml and add the following dependencies

JPA Hibernate dependencies:
```html
<!-- https://mvnrepository.com/artifact/org.hibernate/hibernate-entitymanager -->
        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-entitymanager</artifactId>
            <version>5.4.20.Final</version>
        </dependency>

        <dependency>
            <!-- API para el JPA -->
            <groupId>org.hibernate.javax.persistence</groupId>
            <artifactId>hibernate-jpa-2.1-api</artifactId>
            <version>1.0.0.Final</version>
        </dependency>

        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-core</artifactId>
            <version>5.2.12.Final</version>
            <scope>runtime</scope>
        </dependency>

        <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
        <dependency>
          <groupId>mysql</groupId>
          <artifactId>mysql-connector-java</artifactId>
          <version>8.0.21</version>
        </dependency>

        <dependency>
            <groupId>javax.xml.bind</groupId>
            <artifactId>jaxb-api</artifactId>
            <version>2.3.1</version>
        </dependency>
```


## Other Dependencies

Hibernate dependency:

```html
<!-- https://mvnrepository.com/artifact/org.hibernate/hibernate-core -->
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-core</artifactId>
    <version>5.4.20.Final</version>
</dependency>

```

MySQl dependency:

```html
<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.21</version>
</dependency>
```

Lanterna dependency for GUI

```html
<!-- https://mvnrepository.com/artifact/com.googlecode.lanterna/lanterna -->
<!-- https://github.com/mabe02/lanterna -->
<dependency>
    <groupId>com.googlecode.lanterna</groupId>
    <artifactId>lanterna</artifactId>
    <version>3.0.3</version>
</dependency>
```

