# localstack
A simple guide for working with AWS (S3), on your laptop

# Install [localstack](https://github.com/localstack/localstack)
First of all, you should check that if you have python or not:
check your python:
```shell
python3 --version
```
If you don't have it on your local, you can install it by this [link](https://www.python.org/downloads/macos/)

Install [localstack](https://docs.localstack.cloud/getting-started/installation/):
```shell
brew install localstack/tap/localstack-cli
```
Check the version:
```shell
localstack --version
```
And now you can run it:
```shell
localstack start
```

For the web interface
1. Go to web [interface](https://app.localstack.cloud/inst/default/status)
2. change the endpoint to http://localhost:4566.
3. Now you can use every service that you want s3.

For working with localstack on your local from command line, you can install [awscli-local](https://github.com/localstack/awscli-local)
```shell
pip3 install awscli-local
```

Now you can download or upload your files to s3 from the command line:
```shell
awslocal s3api get-object --bucket document --key test.xlsx ~/Desktop/test.xlsx --debug
```
