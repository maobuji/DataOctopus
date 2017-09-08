SET home=%cd%

cd  %home%/..\elasticsearch\bin
start elasticsearch.bat
ping 127.0.0.1 -n 10 >nul

cd  %home%/..\kibana\bin
start kibana.bat

