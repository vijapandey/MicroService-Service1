mvn spring-boot:run -Dagentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000


Delete: 
	http://localhost:9090/employees/?param1=1
	
GET :
http://localhost:9090/employees/1/



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
	
	
	
	