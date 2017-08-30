SET home=%cd%

cd  %home%\mysql\bin
start mysqld --console

ping 127.0.0.1 -n 5 >nul
cd  %home%\canal\bin
start startup.bat



