SET home=%cd%

cd  %home%
start activate python36
echo jupyter notebook


jupyter notebook --generate-config
c.NotebookApp.iopub_data_rate_limit = 10000000