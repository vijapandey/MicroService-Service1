mvn spring-boot:run -Dagentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000
#java -jar -Dserver.port=9797 target/XXXX.jar

Oauth
  Post:
    http://localhost:8083/service1/oauth/token
    
    For get new token using refresh token :
    	    grant_type: refresh_token
    	    refresh_token: .....

Delete: 
	http://localhost:8083/service1/api/employees/?param1=1

ADMIN	
	GET :
		http://localhost:8083/service1/api/employees/1/
		http://localhost:8083/service1/api/employees/

USER:
	GET:
		http://localhost:8083/service1/api/note/


#management and monitoring info
http://localhost:9090/env
http://localhost:9090/beans
http://localhost:9090/dump
http://localhost:9090/health
http://localhost:9090/metrics

management.context-path=/manage
	/manage/health
	/manage/metrics
management.port=8081	



#Database  : MYSQL
	net start mysql
	 mysqld --console
	 mysql -u root -p 
	 			password: pDRsZl?hi6f?
	 ALTER USER 'root'@'localhost' IDENTIFIED BY 'password';	
	 SHOW VARIABLES WHERE Variable_name = 'port';
	 SHOW VARIABLES WHERE Variable_name = 'hostname';
	 select user();
	 
mysql> create database db_example; -- Create the new database
mysql> create user 'springuser'@'%' identified by 'ThePassword'; -- Creates the user
mysql> grant all on db_example.* to 'springuser'@'%'; -- Gives all the privileges to the new user on the newly created database	 
mysql> revoke all on db_example.* from 'springuser'@'localhost';

{start cmd in admin mode
type in "net start mysql"
close current cmd window and open new cmd window
type in "mysql"
The mysqld service should now be available.}


	Derby
			https://db.apache.org/derby/docs/10.9/getstart/tgssetupenvironment.html
			http://db.apache.org/derby/papers/DerbyTut/install_software.html
			
	Setting
		C:\> set DERBY_INSTALL=C:\Apache\db-derby-10.15.1.3-bin
		C:\> set CLASSPATH=%DERBY_INSTALL%\lib\derby.jar;%DERBY_INSTALL%\lib\derbytools.jar;.
		C:\> cd %DERBY_INSTALL%\bin
		C:\Apache\db-derby-10.15.1.3-bin\bin> setEmbeddedCP.bat	
	=>	java org.apache.derby.tools.sysinfo

	Local to start : startNetworkServer -h 0.0.0.0
	
	Others : jdbc:derby://localhost:1527/dbname;create=true
				jdbc:derby://localhost:1527/c:\temp\mydatabase
				
	connect to the Derby database in embedded mode
			connect 'jdbc:derby://localhost:1527/c:\temp\db\FAQ\db;create=true';
	Disconnect
		disconnect;
	
	
	
	-- --------------------------------------------------------

-- Dumping structure for table springbootdb.userinfo
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE IF NOT EXISTS `userinfo` (
  `id` int(2) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `role` varchar(50) DEFAULT NULL,
  `enabled` char(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(800) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table springbootdb.userinfo: ~5 rows (approximately)
/*!40000 ALTER TABLE `userinfo` DISABLE KEYS */;
INSERT INTO `userinfo` (`id`, `role`, `enabled`, `username`, `password`) VALUES
	(01, 'ROLE_ADMIN', '1', 'admin_user3', '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu'),
	(02, 'ROLE_USER', '1', 'user1', '$2a$10$7/wqZq31iMl1dETBhlSGZOrtK3FtOf8dJrzW3cdSCB2j0K/q7f1C2'),
	(03, 'ROLE_ADMIN', '1', 'admin_user2', '{noop}password'),
	(04, 'ROLE_USER', '1', 'user2', '$2a$10$7/wqZq31iMl1dETBhlSGZOrtK3FtOf8dJrzW3cdSCB2j0K/q7f1C2'),
	(05, 'ROLE_ADMIN', '1', 'hendi', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG');

"id"	"role"	"enabled"	"username"	"password"
"05"	"ROLE_ADMIN"	"1"	"hendi"	"$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG"    (password)
"03"	"ROLE_ADMIN"	"1"	"admin_user2"	"{noop}password"
"01"	"ROLE_ADMIN"	"1"	"admin_user3"	"$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu"   (password)
"02"	"ROLE_USER"	"1"	"user1"	"$2a$10$7/wqZq31iMl1dETBhlSGZOrtK3FtOf8dJrzW3cdSCB2j0K/q7f1C2"      (publicuser)
"04"	"ROLE_USER"	"1"	"user2"	"$2a$10$7/wqZq31iMl1dETBhlSGZOrtK3FtOf8dJrzW3cdSCB2j0K/q7f1C2"       (publicuser)

->TESTING:
Authorization:	Basic dmlqYXktY2xpZW50LWlkOnZpamF5LXNlY3JldC1pbmZv


	