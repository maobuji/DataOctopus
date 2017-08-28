SET home=%cd%

cd  %home%\ADXP-Agent1\kafka\bin\windows
start zookeeper-server-start.bat ../../config/zookeeper.properties
ping 127.0.0.1 -n 10 >nul

cd  %home%\ADXP-Agent1\kafka\bin\windows
start kafka-server-start.bat ../../config/server.properties
ping 127.0.0.1 -n 5 >nul

cd  %home%\ADXP-Agent1\AETL\store
start  h2.bat
cd  %home%\ADXP-Agent1\bin
start startAgent.cmd
cd  %home%\ADXP-Agent1\AETL
start startserver.bat


cd  %home%\ADXP-Agent2\kafka\bin\windows
start zookeeper-server-start.bat ../../config/zookeeper.properties
ping 127.0.0.1 -n 10 >nul

cd  %home%\ADXP-Agent2\kafka\bin\windows
start kafka-server-start.bat ../../config/server.properties
ping 127.0.0.1 -n 5 >nul

cd  %home%\ADXP-Agent2\AETL\store
start  h2.bat
cd  %home%\ADXP-Agent2\bin
start startAgent.cmd
cd  %home%\ADXP-Agent2\AETL
start startserver.bat

