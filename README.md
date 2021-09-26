# LoginService
Login and Registration Microservice POC

## Application Diagram

![Application Diagram](Diagrama.png "Application Diagram")

## Mikado Problem Solving Approach

![Application Diagram](Mikado.png "Application Diagram")

## Application Execution



## Database Setup

To install the database, download MariaDB and create a database using:

```
mysql_install_db.exe --datadir=E:\Development\LoginService\DB --service=ServiceDB --password=secret
```

Create the Schema:

```
CREATE DATABASE `servicedb` /*!40100 DEFAULT CHARACTER SET latin1 */;

create table user (id integer not null auto_increment, email varchar(255), password varchar(255), username varchar(255), primary key (id)) engine=MyISAM


```

Username/Password are root/secret (make sure to change this)

## TODO:

* Change Path parameters to POST parameters

* Change REST call to POST

* Refactor copied User model classes

* Run in containers