title RUN_Spring_Boot_service1
@echo off
SET DEVELOPMENT_HOME=C:\Temp\Spring_Boot\service1

cd %DEVELOPMENT_HOME%
echo "################################### Building ..................................................................."

call mvn -X clean install

echo "##################################  Running ..................................................................."

call mvn -X spring-boot:run

cd %DEVELOPMENT_HOME%