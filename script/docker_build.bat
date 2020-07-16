@echo off
pushd "%~dp0"

cd ../
call ./gradlew.bat clean build
cd ./docker
docker build -t melon-api-server:latest .
docker container stop api-server
docker container rm api-server
docker run -d --net mynet --ip 172.19.0.90 -p 8099:8099 --name api-server melon-api-server:latest
cd ../

:exit
popd
@echo on