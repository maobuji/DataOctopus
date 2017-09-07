SET home=%cd%

cd  %home%\mysql\bin
start mysqld --console

ping 127.0.0.1 -n 10 >nul
cd  %home%\canal\bin
start startup.bat

ping 127.0.0.1 -n 5 >nul
cd  %home%\kafka\bin\windows
start zookeeper-server-start.bat ../../config/zookeeper.properties
ping 127.0.0.1 -n 10 >nul

cd  %home%\kafka\bin\windows
start kafka-server-start.bat ../../config/server.properties
ping 127.0.0.1 -n 10 >nul

cd  %home%\elasticsearch\bin
start elasticsearch.bat
ping 127.0.0.1 -n 10 >nul

cd  %home%\logstash\bin
start logstash.bat -f ../ser-config/K2E.json
ping 127.0.0.1 -n 10 >nul

cd %home%\kibana\bin
start kibana.bat



