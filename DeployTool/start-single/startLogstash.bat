SET home=%cd%

cd  %home%/..\logstash\bin
start logstash.bat -f ../ser-config/K2E.json
ping 127.0.0.1 -n 10 >nul