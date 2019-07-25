title RUN_Spring_Boot
@echo off
SET DEVELOPMENT_HOME=C:\project\Adapter_Development\temp-space\service1

cd %DEVELOPMENT_HOME%
echo "################################### Building ..................................................................."

call mvn -X clean install

echo "##################################  Running ..................................................................."

call mvn spring-boot:run

cd %DEVELOPMENT_HOME%