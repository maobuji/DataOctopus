SET home=%cd%

cd  %home%/..\kafka\bin\windows
start zookeeper-server-start.bat ../../config/zookeeper.properties
ping 127.0.0.1 -n 10 >nul

cd  %home%/..\kafka\bin\windows
start kafka-server-start.bat ../../config/server.properties
ping 127.0.0.1 -n 10 >nul