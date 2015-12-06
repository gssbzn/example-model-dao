Modelos de Acreencias
=====================

Proyecto independiente de Modelos y DAOs de acreencias que permite reutilizarlos en cliente y servidor.
Cuenta con dos implementaciones de DAO, una en memoria (servidor) y una en RESTful(cliente).

## Instalacion y uso

###Dependencia Maven:

Puedes usar la dependencia maven con:
```xml
<dependency>
	<groupId>com.example</groupId>
	<artifactId>acreenciasModel</artifactId>
	<version>0.0.1-SNAPSHOT</version>
</dependency>
```

### Implementacion en memoria

```java
DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOTYPE.MEMORYFACTORY);
```

### Implementacion RESTful

```java
DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOTYPE.RESTFULFACTORY);
```

## License

MIT License. Copyright 2014 Gustavo Bazan. http://gustavobazan.com
